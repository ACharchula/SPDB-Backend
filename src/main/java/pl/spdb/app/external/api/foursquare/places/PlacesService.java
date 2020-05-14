package pl.spdb.app.external.api.foursquare.places;

import java.util.List;
import java.util.Map;

public interface PlacesService {
    String getRecommendations(String lat, String lng, Integer radius, List<String> categoryIds, Integer limit,
                                     List<String> prices, Integer localDay, String localTime);

    Map<String, String> getCategories();
}
