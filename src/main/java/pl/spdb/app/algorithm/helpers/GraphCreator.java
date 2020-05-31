package pl.spdb.app.algorithm.helpers;

import pl.spdb.app.external.api.google.matrix.distance.DistanceMatrixService;
import pl.spdb.app.model.graph.Graph;
import pl.spdb.app.model.graph.PointOfInterest;
import pl.spdb.app.model.graph.Road;
import pl.spdb.app.model.matrix.Element;
import pl.spdb.app.model.poi.Venue;

import java.util.ArrayList;
import java.util.List;

public class GraphCreator {

    private final DistanceMatrixService distanceMatrixService;

    public GraphCreator(DistanceMatrixService distanceMatrixService) {
        this.distanceMatrixService = distanceMatrixService;
    }

    public Graph create(List<Venue> venues) {
        Graph graph = new Graph();
        for (Venue venue : venues) {
            graph.addPointOfInterest(venue);
        }

        for (int i = 0; i < venues.size() - 1; ++i) {
            List<String> origins = new ArrayList<>();
            origins.add(venues.get(i).getLatitudeAndLongitude()); //set only one origin

            List<String> destinations = new ArrayList<>();
            for (int y = i+1; y < venues.size(); ++y) {
                destinations.add(venues.get(y).getLatitudeAndLongitude());
            }
            List<Element> elements;
            elements = distanceMatrixService.getDistanceMatrix(origins, destinations).getRows().get(0).getElements();

            PointOfInterest pointOfInterest = graph.getPointOfInterest(venues.get(i).getId());
            for (int y = 0; y < elements.size(); ++y) {
                String destination = venues.get(i+y+1).getId();
                Road road = new Road(elements.get(y));
                pointOfInterest.addRoad(destination, road);
                PointOfInterest destPOI = graph.getPointOfInterest(destination);
                destPOI.addRoad(pointOfInterest.getVenue().getId(), road);
            }
        }
        return graph;
    }
}
