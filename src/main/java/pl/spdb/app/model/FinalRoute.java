package pl.spdb.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FinalRoute {

    private Place origin;
    private Place destination;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private List<PointOfInterest> waypoints = new ArrayList<>();

    public Place getOrigin() {
        return origin;
    }

    public void setOrigin(Place origin) {
        this.origin = origin;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public List<PointOfInterest> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<PointOfInterest> waypoints) {
        this.waypoints = waypoints;
    }

    public void addPointOfInterest(PointOfInterest pointOfInterest) {
        waypoints.add(pointOfInterest);
    }
}
