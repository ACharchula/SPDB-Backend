package pl.spdb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.spdb.app.external.api.google.directions.DirectionsService;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.external.api.google.places.PlacesService;
import pl.spdb.app.external.api.tomtom.routing.RoutingService;
import pl.spdb.app.external.api.tomtom.search.SearchService;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class AppRestController {

    @Autowired
    SearchService searchService;

    @Autowired
    RoutingService routingService;

    @Autowired
    DistanceMatrixService distanceMatrixService;

    @Autowired
    PlacesService placesService;

    @Autowired
    DirectionsService directionsService;

    @GetMapping("/categories")
    public @ResponseBody String categories() {
        return searchService.getCategories();
    }

    @GetMapping("/poi")
    public @ResponseBody String pointsOfInterest() {
        return searchService.getPointsOfInterest("52.237049", "21.017532", 200, 50000, "");
    }

    @GetMapping("/routing")
    public @ResponseBody String routing() {
        return routingService.getRoute("52.237049,21.017532:52.283335,20.913316:52.293579,20.816394", LocalDateTime.now());
    }

    @GetMapping("/distance_matrix")
    public @ResponseBody String distanceMatrix() {
        return distanceMatrixService.getDistanceMatrix("52.237049,21.017532", "52.283335,20.913316|52.293579,20.816394");
    }

    @GetMapping("/poi_info")
    public @ResponseBody String info() {
        return placesService.getAdditionalInformation("Pizzeria", "52.294350", "20.813122");
    }

    @GetMapping("/dir")
    public @ResponseBody String direction() {
        return directionsService.getRoute("52.237049,21.017532", "52.293579,20.816394", (int) (new Date().getTime()/1000));
    }

    @GetMapping("/dir_with_w")
    public @ResponseBody String directionWithWaypoints() {
        return directionsService.getRoute("52.237049,21.017532", "52.293579,20.816394", (int) (new Date().getTime()/1000)
                , "place_id:ChIJRWTS2kq0HkcRQPNGnR2t5Bg");
    }
}
