package pl.spdb.app.external.api.foursquare.places;

import pl.spdb.app.model.poi.PoiResponse;

import java.util.List;
import java.util.Map;

public interface PlacesService {
    PoiResponse getRecommendations(Double lat, Double lng, Integer radius, List<String> categoryIds, Integer limit,
                                   List<String> prices, Integer localDay, String localTime);

    PoiResponse getRecommendations(Double lat, Double lng, Integer radius, List<String> categoryIds, Integer limit);

    Map<String, String> getCategories();
}
