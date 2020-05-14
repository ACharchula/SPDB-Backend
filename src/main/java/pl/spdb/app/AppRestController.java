package pl.spdb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.external.api.google.directions.DirectionsService;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.model.*;
import pl.spdb.app.mongodb.model.Rating;
import pl.spdb.app.mongodb.RatingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class AppRestController {

    @Autowired
    DistanceMatrixService distanceMatrixService;

    @Autowired
    DirectionsService directionsService;

    @Autowired
    PlacesService placesService;

    @Autowired
    RatingRepository ratingRepository;

    //MONGO TEST
//    @GetMapping("/dev/add")
//    public @ResponseBody String add() {
//        ratingRepository.save(new Rating("2", 1, 2));
//        return "ok";
//    }
//
//    @GetMapping("/disp")
//    public @ResponseBody String disp() {
//        return ratingRepository.findById("2").toString();
//    }

    @GetMapping("/dev/mtx")
    public @ResponseBody String matrix() {
        List<String> origins = new ArrayList<>(Arrays.asList("Boston,MA", "Charlestown,MA"));
        List<String> destinations = new ArrayList<>(Arrays.asList("Lexington,MA", "Concord,MA"));
        return distanceMatrixService.getDistanceMatrix(origins, destinations);
    }

    @GetMapping("/dev/dir")
    public @ResponseBody String direction() {
        return directionsService.getRoute("52.237049,21.017532", "50.049683,19.944544");
    }

    @GetMapping("/dev/poi")
    public @ResponseBody String places() {
        return placesService.getRecommendations("50.974199", "20.651931", null, null, 50,
                null, null, null);
    }

    @GetMapping("/api/categories")
    public ResponseEntity<Map<String, String>> categories() {
        return ResponseEntity.ok(placesService.getCategories());
    }

    @GetMapping("/api/waypoints")
    public ResponseEntity<FinalRoute> generateRoute() { //endpoint will change
        //tmp response data
        FinalRoute finalRoute = new FinalRoute();

        Place origin = new Place("Warsaw", "52.237049", "21.017532");
        Place destination = new Place("Cracow", "50.049683", "19.944544");

        List<Category> categoryList = new ArrayList<Category>();
        categoryList.add(new Category("50aaa49e4b90af0d42d5de11", "Castle"));
        Rating rating = new Rating("dlugieibrzydkieid", 10, 2336);
        PointOfInterest p1 = new PointOfInterest("Zamek Królewski w Chęcinach", "50.797243", "20.459009", "dlugieibrzydkieid", categoryList, rating);

        categoryList = new ArrayList<Category>();
        categoryList.add(new Category("52e81612bcbc57f1066b7a24", "Tree"));
        rating = new Rating("dlugieibrzydkieid2", 2, 3);
        PointOfInterest p2 = new PointOfInterest("Dąb Bartek", "50.987548", "20.649721", "dlugieibrzydkieid2", categoryList, rating);

        List<PointOfInterest> poi = new ArrayList<>();
        poi.add(p1);
        poi.add(p2);

        finalRoute.setOrigin(origin);
        finalRoute.setDestination(destination);
        finalRoute.setWaypoints(poi);
        finalRoute.setDepartureDateTime(LocalDateTime.now());
        finalRoute.setArrivalDateTime(LocalDateTime.now().plusHours(5));

        return ResponseEntity.ok(finalRoute);
    }
}
