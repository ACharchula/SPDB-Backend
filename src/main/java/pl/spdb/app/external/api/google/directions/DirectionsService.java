package pl.spdb.app.external.api.google.directions;

import pl.spdb.app.model.route.Routes;

public interface DirectionsService {
    Routes getRoute(String origin, String destination);
}
