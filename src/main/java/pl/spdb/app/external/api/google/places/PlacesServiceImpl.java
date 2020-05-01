package pl.spdb.app.external.api.google.places;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.springframework.web.client.RestTemplate;
import pl.spdb.app.external.api.ApiKeyProvider;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PlacesServiceImpl implements PlacesService {

    //rating??
    private static final String place_id_url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?" +
            "input={input}&inputtype=textquery" +
            "&locationbias=circle:10@{lat},{lon}&key={key}";

    private static final String info_url = "https://maps.googleapis.com/maps/api/place/details/json?" +
            "place_id={place_id}&fields=place_id,formatted_address,name,photo,type,url,opening_hours&key={key}";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getAdditionalInformation(String name, String lat, String lon) {
        Map<String, String> params = new HashMap<>();
        params.put("input", name);
        params.put("lat", lat);
        params.put("lon", lon);
        params.put("key", ApiKeyProvider.google_api_key);

        String json = restTemplate.getForObject(place_id_url, String.class, params);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray arr = jsonObject.getJSONArray("candidates");
        String placeId = arr.getJSONObject(0).getString("place_id");

        params = new HashMap<>();
        params.put("place_id", placeId);
        params.put("key", ApiKeyProvider.google_api_key);

        return restTemplate.getForObject(info_url, String.class, params);
    }
}
