package pl.spdb.app.algorithm;

import org.springframework.stereotype.Service;
import pl.spdb.app.algorithm.helpers.Algorithm;
import pl.spdb.app.algorithm.helpers.GraphCreator;
import pl.spdb.app.algorithm.helpers.PoiFinder;
import pl.spdb.app.algorithm.helpers.RatingProvider;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.model.api.FinalResult;
import pl.spdb.app.model.graph.Graph;
import pl.spdb.app.model.poi.Venue;
import pl.spdb.app.model.poi.VenueLocation;
import pl.spdb.app.model.route.Leg;
import pl.spdb.app.model.route.Location;
import pl.spdb.app.model.route.Routes;
import pl.spdb.app.mongodb.RatingRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaypointFinder {

    private static final int POI_LOOKOUT_RADIUS = 50000; //50 km

    private final PoiFinder poiFinder;
    private final RatingProvider ratingProvider;
    private final GraphCreator graphCreator;

    public WaypointFinder(PlacesService placesService, RatingRepository ratingRepository, DistanceMatrixService distanceMatrixService) {
        this.poiFinder = new PoiFinder(placesService, POI_LOOKOUT_RADIUS);
        this.ratingProvider = new RatingProvider(ratingRepository);
        this.graphCreator = new GraphCreator(distanceMatrixService);
    }

    public FinalResult findWaypoints(Routes routes, long timeInPoi, int minimalRating, String categories,
                                     long additionalTime, long additionalDistance, long searchingStart) {
        //when no waypoints are specified there is only one route and leg
        Leg leg = routes.getRoutes().get(0).getLegs().get(0);
        List<Venue> venues = poiFinder.findPointsOfInterest(leg,10, categories);
        ratingProvider.provideRatings(venues);

        venues = venues.stream()
                .filter(v -> v.getRating().getAvgRating() >= minimalRating)
                .collect(Collectors.toList());

        if (venues.size() == 0)
            return null;

        int maxGroupId = venues.stream()
                .mapToInt(Venue::getGroupId)
                .max()
                .getAsInt();

        venues.add(createVenue("START", leg.getStart_location(), leg.getStart_address(), 0));
        venues.add(createVenue("END", leg.getEnd_location(), leg.getEnd_address(), maxGroupId));

        Graph graph = graphCreator.create(venues);
        FinalResult finalResult = Algorithm.run(graph, "START", "END", timeInPoi, searchingStart,
                leg.getDuration().getValue() + additionalTime,
                leg.getDistance().getValue() + additionalDistance);

        if (finalResult != null) {
            finalResult.setOriginalDistance(leg.getDistance().getValue());
            finalResult.setOriginalDuration(leg.getDuration().getValue());
        }
        return finalResult;
    }

    private Venue createVenue(String id, Location location, String address, int groupId) {
        Venue venue = new Venue();
        venue.setLocation(new VenueLocation(location, Collections.singletonList(address)));
        venue.setId(id);
        venue.setGroupId(groupId);
        return venue;
    }
}
