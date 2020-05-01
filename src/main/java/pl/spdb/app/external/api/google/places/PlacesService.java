package pl.spdb.app.external.api.google.places;

public interface PlacesService {
    String getAdditionalInformation(String name, String lat, String lon);
}
