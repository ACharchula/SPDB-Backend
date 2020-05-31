package pl.spdb.app.external.api.google.directions;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;
import pl.spdb.app.model.route.Routes;

import java.util.HashMap;
import java.util.Map;

@Service
public class DirectionsServiceImpl implements DirectionsService {

    private final static String url = "https://maps.googleapis.com/maps/api/directions/json?" +
            "origin={origin}&destination={destination}&key={key}&avoid=highways";

    private final RestTemplate restTemplate = new RestTemplate();

    //https://developers.google.com/maps/documentation/directions/intro
    //origin        — The address, textual latitude/longitude value, or place ID from which you wish to calculate directions
    //                  origin=24+Sussex+Drive+Ottawa+ON
    //                  origin=41.43206,-81.38992
    //destination   — The address, textual latitude/longitude value, or place ID to which you wish to calculate directions.
    //                The options for the destination parameter are the same as for the origin parameter, described above.


    @Override
    public Routes getRoute(String origin, String destination) {
        Map<String, String> params = new HashMap<>();
        params.put("origin", origin);
        params.put("destination", destination);
        params.put("key", ApiKeyProvider.google_api_key);

        return restTemplate.getForObject(url, Routes.class, params);
    }
}
