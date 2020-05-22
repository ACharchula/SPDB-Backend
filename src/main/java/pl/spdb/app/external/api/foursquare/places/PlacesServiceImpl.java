package pl.spdb.app.external.api.foursquare.places;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;
import pl.spdb.app.model.poi.PoiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlacesServiceImpl implements PlacesService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final static String find_poi_url = "https://api.foursquare.com/v2/search/recommendations?" +
            "client_id={client_id}&client_secret={client_secret}&v={version}";

    // https://developer.foursquare.com/docs/api-reference/venues/recommendations/
    // ll           - required unless near placeName provided. Latitude and longitude of the user’s location.
    // near         - required unless ll is provided. A string naming a place in the world. If the near string is
    //                not geocodable, returns a failed_geocode error. Otherwise, searches within the bounds of the
    //                eocode and adds a geocode object to the response.
    // radius       - Radius to search within, in meters. If radius is not specified, a suggested radius will be
    //                used based on the density of venues in the area. The maximum supported radius is currently
    //                100,000 meters.
    // categoryId   - Specify a categoryId (or include a list of comma-separated IDs if you want to select multiple
    //                categories) to limit the results to the specified category. For the complete category tree,
    //                see categories endpoint.
    // limit        - Number of results to return, up to 50.
    // prices       - One of: 1, 2, 3, 4. Only return venues that match the specified price(s), 1 being “$” and 4
    //                being “”. Supports multiple values.
    // localDay     - 1–7 for Monday–Sunday. Only return results that are open on this day.
    // localTime    - Only return results that are open at this time. HH in 24-hr format. (np. 20:00)

    @Override
    public PoiResponse getRecommendations(Double lat, Double lng, Integer radius, List<String> categoryIds, Integer limit) {
        return getRecommendations(lat, lng, radius, categoryIds, limit, null, null, null);
    }

    @Override
    public PoiResponse getRecommendations(Double lat, Double lng, Integer radius, List<String> categoryIds, Integer limit,
                                          List<String> prices, Integer localDay, String localTime) {

        if (lat == null || lng == null)
            throw new IllegalArgumentException("Latitude and longitude can't be null!");

        String url = find_poi_url;
        Map<String, String> params = getParams();

        url = addLL(lat, lng, url, params);
        if (radius != null && radius > 0 && radius < 100000) url = addRadius(radius, url, params);
        if (categoryIds != null && categoryIds.size() > 0) url = addCategories(categoryIds, url, params);
        if (limit != null && limit > 0 && limit <= 50) url = addLimit(limit, url, params);
        if (prices != null && prices.size() > 0 & prices.size() <= 4) url = addPrices(prices, url, params);
        if (localDay != null && localDay > 0 && localDay <= 7) url = addLocalDay(localDay, url, params);
        if (localTime != null) url = addLocalTime(localTime, url, params);

        return restTemplate.getForObject(url, PoiResponse.class, params);
    }

    @Override
    public Map<String, String> getCategories() {
        return AllCategories.CATEGORIES;
    }

    private String addLocalTime(String localTime, String url, Map<String, String> params) {
        url += "&localTime={localTime}";
        params.put("localTime", localTime);
        return url;
    }

    private String addLocalDay(Integer localDay, String url, Map<String, String> params) {
        url += "&localDay={localDay}";
        params.put("localDay", localDay.toString());
        return url;
    }

    private String addPrices(List<String> prices, String url, Map<String, String> params) {
        url += "&prices={prices}";
        params.put("prices", String.join(",", prices));
        return url;
    }

    private String addLimit(Integer limit, String url, Map<String, String> params) {
        url += "&limit={limit}";
        params.put("limit", limit.toString());
        return url;
    }

    private String addCategories(List<String> categoryIds, String url, Map<String, String> params) {
        url += "&categoryId={categories}";
        params.put("categories", String.join(",", categoryIds));
        return url;
    }

    private String addRadius(Integer radius, String url, Map<String, String> params) {
        url += "&radius={radius}";
        params.put("radius", radius.toString());
        return url;
    }

    private String addLL(Double lat, Double lng, String url, Map<String, String> params) {
        url += "&ll={ll}";
        params.put("ll", lat + "," + lng);
        return url;
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", ApiKeyProvider.foursquare_client_id);
        params.put("client_secret", ApiKeyProvider.foursquare_client_secret);
        params.put("version", ApiKeyProvider.foursquare_api_version);
        return params;
    }
}
