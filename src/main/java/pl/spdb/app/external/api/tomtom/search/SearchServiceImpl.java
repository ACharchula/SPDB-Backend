package pl.spdb.app.external.api.tomtom.search;

import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;

import java.util.HashMap;
import java.util.Map;

public class SearchServiceImpl implements SearchService {

    private static final String poi_search_url = "https://api.tomtom.com/search/2/nearbySearch/.json?" +
            "lat={lat}&lon={lon}&limit={limit}&radius={radius}&" +
            "categorySet={categories}&openingHours=nextSevenDays&key={key}";

    private static final String categories_url = "https://api.tomtom.com/search/2/poiCategories.json?key={key}";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getCategories() {
        Map<String, String> params = new HashMap<>();
        params.put("key", ApiKeyProvider.tomtom_api_key);
        return restTemplate.getForObject(categories_url, String.class, params);
    }

    @Override
    public String getPointsOfInterest(String latitude, String longitude, int limit, int radius, String categories) {
        Map<String, String> params = new HashMap<>();
        params.put("lat", latitude);
        params.put("lon", longitude);
        params.put("limit", String.valueOf(limit));
        params.put("radius", String.valueOf(radius)); //in meters
        params.put("categories", categories); //multiple categories must be separated by comma
        params.put("key", ApiKeyProvider.tomtom_api_key);

        return restTemplate.getForObject(poi_search_url, String.class, params);
    }
}
