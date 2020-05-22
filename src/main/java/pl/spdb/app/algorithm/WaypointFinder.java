package pl.spdb.app.algorithm;

import org.springframework.stereotype.Service;
import pl.spdb.app.algorithm.helpers.Algorithm;
import pl.spdb.app.algorithm.helpers.GraphCreator;
import pl.spdb.app.algorithm.helpers.PoiFinder;
import pl.spdb.app.algorithm.helpers.RatingProvider;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.model.graph.Graph;
import pl.spdb.app.model.poi.Venue;
import pl.spdb.app.model.poi.VenueLocation;
import pl.spdb.app.model.route.Leg;
import pl.spdb.app.model.route.Routes;
import pl.spdb.app.mongodb.RatingRepository;

import java.util.Collections;
import java.util.List;

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

    //TODO add restrictions
    public List<Venue> findWaypoints(Routes routes) {
        //when no waypoints are specified there is only one route and leg
        Leg leg = routes.getRoutes().get(0).getLegs().get(0);
        List<Venue> venues = poiFinder.findPointsOfInterest(leg);
        ratingProvider.provideRatings(venues);

        Venue start = new Venue();
        start.setLocation(new VenueLocation(leg.getStart_location(), Collections.singletonList(leg.getStart_address())));
        start.setId("START");
        Venue end = new Venue();
        end.setLocation(new VenueLocation(leg.getEnd_location(), Collections.singletonList(leg.getEnd_address())));
        end.setId("END");
        venues.add(start);
        venues.add(end);

        Graph graph = graphCreator.create(venues);
        List<Venue> result = Algorithm.run(graph);
        return venues;
    }
}
