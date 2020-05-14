package pl.spdb.app.external.api.google.directions;

public interface DirectionsService {
    String getRoute(String origin, String destination);
}
