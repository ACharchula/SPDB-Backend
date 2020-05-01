package pl.spdb.app.external.api.tomtom.routing;

import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RoutingServiceImpl implements RoutingService {

    private static final String url = "https://api.tomtom.com/routing/1/calculateRoute" +
            "/{waypoints}/json?computeBestOrder=true&departAt={departure}&avoid=unpavedRoads&key={key}";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getRoute(String waypointsCoordinates, LocalDateTime departureDateTime) {
        Map<String, String> params = new HashMap<>();
        params.put("waypoints", waypointsCoordinates); //lat and lon separated by comma and waypoints separated by ':'
        params.put("departure", departureDateTime.toString());
        params.put("key", ApiKeyProvider.tomtom_api_key);

        return restTemplate.getForObject(url, String.class, params);
    }
}
