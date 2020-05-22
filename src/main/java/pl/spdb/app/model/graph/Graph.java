package pl.spdb.app.model.graph;

import pl.spdb.app.model.poi.Venue;

import java.util.HashMap;
import java.util.Map;

//poi with id START and END is required to run the algorithm
public class Graph {
    private Map<String, PointOfInterest> pointsOfInterest = new HashMap<>();

    public void addPointOfInterest(Venue venue) {
        pointsOfInterest.put(venue.getId(), new PointOfInterest(venue));
    }

    public PointOfInterest getPointOfInterest(String id) {
        return pointsOfInterest.get(id);
    }

    public Map<String, PointOfInterest> getPointsOfInterest() {
        return pointsOfInterest;
    }

    public void setPointsOfInterest(Map<String, PointOfInterest> pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }
}
