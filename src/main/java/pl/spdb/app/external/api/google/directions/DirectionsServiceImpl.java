package pl.spdb.app.external.api.google.directions;

import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;

import java.util.HashMap;
import java.util.Map;

public class DirectionsServiceImpl implements DirectionsService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getRoute(String origin, String destination, int departureTime) {
        Map<String, String> params = new HashMap<>();
        params.put("origin", origin); //multiple origins and dest divided by '|'
        params.put("destination", destination);
        params.put("dep_time", String.valueOf(departureTime));
        params.put("key", ApiKeyProvider.google_api_key);

        return restTemplate.getForObject(getRouteUrl(false), String.class, params);
    }

    @Override
    public String getRoute(String origin, String destination, int departureTime, String waypoints) {
        Map<String, String> params = new HashMap<>();
        params.put("origin", origin); //multiple origins and dest divided by '|'
        params.put("destination", destination);
        params.put("waypoints", waypoints);
        params.put("dep_time", String.valueOf(departureTime));
        params.put("key", ApiKeyProvider.google_api_key);

        return restTemplate.getForObject(getRouteUrl(true), String.class, params);
    }

    private String getRouteUrl(boolean includeWaypoints) {
        return "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin={origin}&destination={destination}" +
                (includeWaypoints ? "&waypoints={waypoints}" : "") +
                "&departure_time={dep_time}" +
                "&key={key}";
    }
}
