package pl.spdb.app.model.graph;

import pl.spdb.app.model.poi.Venue;

import java.util.HashMap;

public class PointOfInterest {
    private Venue venue;
    private HashMap<String, Road> roads = new HashMap<>();

    public PointOfInterest(Venue venue) {
        this.venue = venue;
    }

    public void addRoad(String venueId, Road road) {
        roads.put(venueId, road);
    }

    public Venue getVenue() {
        return venue;
    }

    public HashMap<String, Road> getRoads() {
        return roads;
    }
}
