package pl.spdb.app.external.api.google.matrix.distance;

import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;

import java.util.HashMap;
import java.util.Map;

public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private static final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?" +
            "origins={origins}&destinations={destinations}&key={key}";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getDistanceMatrix(String origins, String destinations) {
        Map<String, String> params = new HashMap<>();
        params.put("origins", origins); //multiple origins and dest divided by '|'
        params.put("destinations", destinations);
        params.put("key", ApiKeyProvider.google_api_key);

        return restTemplate.getForObject(url, String.class, params);
    }
}
