package pl.spdb.app.algorithm;

import org.springframework.stereotype.Service;
import pl.spdb.app.algorithm.helpers.PoiFinder;
import pl.spdb.app.algorithm.helpers.RatingProvider;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.model.poi.Venue;
import pl.spdb.app.model.route.Routes;
import pl.spdb.app.mongodb.RatingRepository;

import java.util.List;

@Service
public class WaypointFinder {

    private static final int POI_LOOKOUT_RADIUS = 50000; //50 km

    private final PoiFinder poiFinder;
    private final RatingProvider ratingProvider;

    public WaypointFinder(PlacesService placesService, RatingRepository ratingRepository) {
        this.poiFinder = new PoiFinder(placesService, POI_LOOKOUT_RADIUS);
        this.ratingProvider = new RatingProvider(ratingRepository);
    }

    //TODO add restrictions
    public List<Venue> findWaypoints(Routes routes) {
        //when no waypoints are specified there is only one route and leg
        List<Venue> venues = poiFinder.findPointsOfInterest(routes.getRoutes().get(0).getLegs().get(0));
        ratingProvider.provideRatings(venues);
        // TODO make a distance matrix
        // TODO create a graph
        // TODO run algorithm
        return venues;
    }
}
