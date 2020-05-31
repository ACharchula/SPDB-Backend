package pl.spdb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spdb.app.algorithm.WaypointFinder;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.external.api.google.directions.DirectionsService;
import pl.spdb.app.model.api.FinalResult;
import pl.spdb.app.model.api.Parameters;
import pl.spdb.app.model.route.Routes;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AppRestController {

    @Autowired
    PlacesService placesService;

    @Autowired
    DirectionsService directionsService;

    @Autowired
    WaypointFinder waypointFinder;

    @GetMapping("/api/waypoints")
    public ResponseEntity<Object> waypoints1(@RequestParam(value = "origin") String origin,
                                                  @RequestParam(value = "destination") String destination,
                                                  @RequestParam(value = "timeInPoi", defaultValue = "1800") long timeInPoi, //in seconds
                                                  @RequestParam(value = "minimalRating", defaultValue = "0") int minimalRating,
                                                  @RequestParam(value = "categories", defaultValue = "") String categories, //divided by comma
                                                  @RequestParam(value = "additionalTime", defaultValue = "9000") long additionalTime, //in seconds
                                                  @RequestParam(value = "additionalDistance", defaultValue = "50000") long additionalDistance,
                                                  @RequestParam(value = "searchingStart", defaultValue = "0") long searchingStart) //in seconds
    {
        Routes routes = directionsService.getRoute(origin, destination);
        FinalResult finalResult = waypointFinder.findWaypoints(routes, timeInPoi, minimalRating, categories,
                additionalTime, additionalDistance, searchingStart);

        if (finalResult != null) {
            finalResult.setParameters(new Parameters(timeInPoi, minimalRating, categories, additionalTime, additionalDistance, searchingStart));
            return ResponseEntity.ok(finalResult);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No waypoints have been found for given parameters!");
        }
    }

    @GetMapping("/api/categories")
    public ResponseEntity<Map<String, String>> categories() {
        return ResponseEntity.ok(placesService.getCategories());
    }
}
