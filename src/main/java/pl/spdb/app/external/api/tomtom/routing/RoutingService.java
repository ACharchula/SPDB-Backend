package pl.spdb.app.external.api.tomtom.routing;

import java.time.LocalDateTime;

public interface RoutingService {
    String getRoute(String waypointsCoordinates, LocalDateTime departureDateTime);
}
