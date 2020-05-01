package pl.spdb.app.external.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.spdb.app.external.api.google.directions.DirectionsService;
import pl.spdb.app.external.api.google.directions.DirectionsServiceImpl;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixServiceImpl;
import pl.spdb.app.external.api.google.places.PlacesService;
import pl.spdb.app.external.api.google.places.PlacesServiceImpl;
import pl.spdb.app.external.api.tomtom.routing.RoutingService;
import pl.spdb.app.external.api.tomtom.routing.RoutingServiceImpl;
import pl.spdb.app.external.api.tomtom.search.SearchService;
import pl.spdb.app.external.api.tomtom.search.SearchServiceImpl;

@Configuration
public class ExternalApiConfig {

    @Bean
    public DistanceMatrixService distanceMatrixService() {
        return new DistanceMatrixServiceImpl();
    }

    @Bean
    public SearchService searchService() {
        return new SearchServiceImpl();
    }

    @Bean
    public RoutingService routingService() {
        return new RoutingServiceImpl();
    }

    @Bean
    public PlacesService placesService() {
        return new PlacesServiceImpl();
    }

    @Bean
    public DirectionsService directionsService() {
        return new DirectionsServiceImpl();
    }
}
