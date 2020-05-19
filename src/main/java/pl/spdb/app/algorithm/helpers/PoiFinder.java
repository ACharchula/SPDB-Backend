package pl.spdb.app.algorithm.helpers;

import pl.spdb.app.external.api.foursquare.places.PlacesService;
import pl.spdb.app.model.poi.PoiResponse;
import pl.spdb.app.model.poi.Result;
import pl.spdb.app.model.poi.Venue;
import pl.spdb.app.model.route.Leg;
import pl.spdb.app.model.route.Location;
import pl.spdb.app.model.route.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoiFinder {

    private static final double R = 6371e3; //earth radius

    private final PlacesService placesService;
    private final int radius;

    public PoiFinder(PlacesService placesService, int radius) {
        this.placesService = placesService;
        this.radius = radius;
    }

    //TODO add categories and/or prices and maybe change limit for places
    public List<Venue> findPointsOfInterest(Leg leg) {
        List<Location> locations = getLocationsToLookout(leg.getSteps(), radius);
        Map<String, Venue> venues = new HashMap<>();

        for (Location location : locations) {
            PoiResponse poiResponse = placesService.getRecommendations(location.getLat(), location.getLng(), radius, null, 50);
            List<Result> results = poiResponse.getResponse().getGroup().getResults();
            for (Result result : results) {
                Venue venue = result.getVenue();
                venues.putIfAbsent(venue.getId(), venue); //do not insert duplicates
            }
        }
        return new ArrayList<>(venues.values());
    }

    private List<Location> getLocationsToLookout(List<Step> steps, int radius) {
        List<Location> lookoutLocations = new ArrayList<>();
        double metersFromLastLookout = radius;

        for (Step step : steps) {
            if (metersFromLastLookout - step.getDistance().getValue() > 0) {
                metersFromLastLookout -= step.getDistance().getValue();
            } else {
                Location start = step.getStart_location();
                Location end = step.getEnd_location();

                double bearing = getBearing(start.getLat(), start.getLng(), end.getLat(), end.getLng());
                lookoutLocations.add(getPointFromStartPoint(start.getLat(), start.getLng(), bearing, metersFromLastLookout));

                while ((metersFromLastLookout = distanceFromLastLookoutPointToTheEndOfStep(lookoutLocations, end)) / radius >= 1) {
                    start = lookoutLocations.get(lookoutLocations.size() - 1);
                    lookoutLocations.add(getPointFromStartPoint(start.getLat(), start.getLng(), bearing, radius));
                }
                metersFromLastLookout = radius - metersFromLastLookout;
            }
        }

        return lookoutLocations;
    }

    private double distanceFromLastLookoutPointToTheEndOfStep(List<Location> lookoutLocations, Location end) {
        Location start = lookoutLocations.get(lookoutLocations.size() - 1); //get last lookout location
        return distance(start.getLat(), start.getLng(), end.getLat(), end.getLng());
    }

    //https://www.movable-type.co.uk/scripts/latlong.html
    private double getBearing(double lat1, double lng1, double lat2, double lng2) {
        double lat1R = Math.toRadians(lat1);
        double lat2R = Math.toRadians(lat2);
        double lng1R = Math.toRadians(lng1);
        double lng2R = Math.toRadians(lng2);

        double y = Math.sin(lng2R - lng1R) * Math.cos(lat2R);
        double x = Math.cos(lat1R) * Math.sin(lat2R) - Math.sin(lat1R) * Math.cos(lat2R) * Math.cos(lng2R - lng1R);
        return Math.atan2(y, x); // in radians
    }

    private Location getPointFromStartPoint(double startingLat, double startingLng, double bearing, double distance) {
        double latR = Math.toRadians(startingLat);
        double lngR = Math.toRadians(startingLng);

        double lat = Math.asin(Math.sin(latR) * Math.cos(distance / R) + Math.cos(latR) * Math.sin(distance / R) * Math.cos(bearing));
        double lng = lngR + Math.atan2(Math.sin(bearing) * Math.sin(distance / R) * Math.cos(latR),
                Math.cos(distance / R) - Math.sin(latR) * Math.sin(lat));
        return new Location(Math.toDegrees(lat), Math.toDegrees(lng));
    }

    private double distance(double lat1, double lng1, double lat2, double lng2) {
        double lat1R = Math.toRadians(lat1);
        double lat2R = Math.toRadians(lat2);
        double diffLatR = Math.toRadians(lat2 - lat1);
        double diffLngR = Math.toRadians(lng2 - lng1);

        double a = Math.sin(diffLatR / 2) * Math.sin(diffLatR / 2) +
                Math.cos(lat1R) * Math.cos(lat2R) * Math.sin(diffLngR / 2) * Math.sin(diffLngR / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c; //in meters
    }
}
