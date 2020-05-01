package pl.spdb.app.external.api.tomtom.search;

public interface SearchService {
    String getCategories();
    String getPointsOfInterest(String latitude, String longitude, int limit, int radius, String categories);
}
