package pl.spdb.app.model.api;

import pl.spdb.app.model.poi.Venue;

import java.util.List;

public class FinalResult {

    private List<Venue> waypoints;

    private long original_distance;
    private long final_distance;
    private long original_duration;
    private long final_duration;

    public FinalResult(List<Venue> waypoints, long distance, long duration) {
        this.waypoints = waypoints;
        this.final_distance = distance;
        this.final_duration = duration;
    }

    public List<Venue> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Venue> waypoints) {
        this.waypoints = waypoints;
    }

    public long getFinal_distance() {
        return final_distance;
    }

    public void setFinal_distance(long final_distance) {
        this.final_distance = final_distance;
    }

    public long getFinal_duration() {
        return final_duration;
    }

    public void setFinal_duration(long final_duration) {
        this.final_duration = final_duration;
    }

    public long getOriginal_distance() {
        return original_distance;
    }

    public void setOriginal_distance(long original_distance) {
        this.original_distance = original_distance;
    }

    public long getOriginal_duration() {
        return original_duration;
    }

    public void setOriginal_duration(long original_duration) {
        this.original_duration = original_duration;
    }
}
