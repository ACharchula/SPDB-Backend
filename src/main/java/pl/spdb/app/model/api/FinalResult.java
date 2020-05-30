package pl.spdb.app.model.api;

import pl.spdb.app.model.poi.Venue;

import java.util.List;

public class FinalResult {

    private List<Venue> waypoints;

    private long originalDistance;
    private long finalDistance;
    private long originalDuration;
    private long finalDuration;

    private Parameters parameters;

    public FinalResult(List<Venue> waypoints, long distance, long duration) {
        this.waypoints = waypoints;
        this.finalDistance = distance;
        this.finalDuration = duration;
    }

    public List<Venue> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Venue> waypoints) {
        this.waypoints = waypoints;
    }

    public long getFinalDistance() {
        return finalDistance;
    }

    public void setFinalDistance(long finalDistance) {
        this.finalDistance = finalDistance;
    }

    public long getFinalDuration() {
        return finalDuration;
    }

    public void setFinalDuration(long finalDuration) {
        this.finalDuration = finalDuration;
    }

    public long getOriginalDistance() {
        return originalDistance;
    }

    public void setOriginalDistance(long originalDistance) {
        this.originalDistance = originalDistance;
    }

    public long getOriginalDuration() {
        return originalDuration;
    }

    public void setOriginalDuration(long originalDuration) {
        this.originalDuration = originalDuration;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
