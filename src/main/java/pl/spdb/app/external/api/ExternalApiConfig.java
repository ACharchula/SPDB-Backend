package pl.spdb.app.external.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.external.api.foursquare.places.PlacesServiceImpl;
import pl.spdb.app.external.api.google.directions.DirectionsService;
import pl.spdb.app.external.api.google.directions.DirectionsServiceImpl;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixServiceImpl;

@Configuration
public class ExternalApiConfig {

    @Bean
    public DistanceMatrixService distanceMatrixService() {
        return new DistanceMatrixServiceImpl();
    }

    @Bean
    public DirectionsService directionsService() {
        return new DirectionsServiceImpl();
    }

    @Bean
    public PlacesService placesService() { return new PlacesServiceImpl(); }
}
