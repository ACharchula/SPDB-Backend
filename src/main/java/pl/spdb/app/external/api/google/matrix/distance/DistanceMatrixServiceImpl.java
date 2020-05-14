package pl.spdb.app.external.api.google.matrix.distance;

import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private static final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?" +
            "origins={origins}&destinations={destinations}&key={key}";

    private final RestTemplate restTemplate = new RestTemplate();

    //https://developers.google.com/maps/documentation/distance-matrix/intro
    //origins      — The starting point for calculating travel distance and time. You can supply one or more locations
    //               separated by the pipe character (|), in the form of an address, latitude/longitude coordinates,
    //               or a google place ID:
    //               origins=Bobcaygeon+ON|24+Sussex+Drive+Ottawa+ON
    //               origins=41.43206,-81.38992|-33.86748,151.20699
    //destinations — One or more locations to use as the finishing point for calculating travel distance and time.
    //               The options for the destinations parameter are the same as for the origins parameter, described above.

    @Override
    public String getDistanceMatrix(List<String> origins, List<String> destinations) {
        Map<String, String> params = new HashMap<>();
        params.put("origins", String.join("|", origins)); //multiple origins and dest divided by '|'
        params.put("destinations", String.join("|", destinations));
        params.put("key", ApiKeyProvider.google_api_key);

        return restTemplate.getForObject(url, String.class, params);
    }
}
