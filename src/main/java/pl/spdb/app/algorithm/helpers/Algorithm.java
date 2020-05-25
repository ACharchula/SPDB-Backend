package pl.spdb.app.algorithm.helpers;

import pl.spdb.app.model.api.FinalResult;
import pl.spdb.app.model.graph.Graph;
import pl.spdb.app.model.graph.PointOfInterest;
import pl.spdb.app.model.graph.Road;
import pl.spdb.app.model.poi.Venue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Algorithm {

    private static Graph graph;
    private static String endId;
    private static long timeInPoi;
    private static long timeLimit;
    private static long distanceLimit;

    private static List<String> bestPath;
    private static double bestPathReward;
    private static long time;
    private static long distance;

    //TODO ADD MAXIMAL NuMBER OF POIS IN FINAL , and other restrictions like hour of looking for poi, max distance , minimal rating,
    //TODO maybe use number of ratings also
    public static FinalResult run(Graph graph, String startId, String endId, long timeInPoiInSeconds, long timeLimitInSeconds, long distanceLimitInMeters) {
        Algorithm.graph = graph;
        Algorithm.endId = endId;
        Algorithm.timeInPoi = timeInPoiInSeconds;

        bestPath = new ArrayList<>(Arrays.asList(startId, endId));
        bestPathReward = 0;
        timeLimit = timeLimitInSeconds;
        distanceLimit = distanceLimitInMeters;

        findBestPath(timeLimitInSeconds, distanceLimitInMeters, graph.getPointOfInterest(startId),
                new ArrayList<>(Collections.singletonList(startId)), 0.0);

        List<PointOfInterest> result = bestPath.stream().map(graph::getPointOfInterest).collect(Collectors.toList());

        if (result.size() <= 2) {
            return null;
        } else {
            List<Venue> waypoints = result
                    .subList(1, result.size() - 1).stream()
                    .map(PointOfInterest::getVenue)
                    .collect(Collectors.toList());

            return new FinalResult(waypoints, distance, time);
        }
    }

    private static void findBestPath(long remainingTime, long remainingDistance, PointOfInterest poi, List<String> path, double reward) {
        for (String poiId : poi.getRoads().keySet()) {
            if (!path.contains(poiId)) {
                Road road = poi.getRoads().get(poiId);
                long newRemainingTime = remainingTime - road.getDuration().getValue();
                long newRemainingDistance = remainingDistance - road.getDistance().getValue();
                if (newRemainingTime > 0 && newRemainingDistance > 0) { //TODO == 0
                    List<String> newPath = clonePath(path);
                    newPath.add(poiId);
                    if (poiId.equals(endId)) {
                        if (reward > bestPathReward) {
                            bestPath = newPath;
                            bestPathReward = reward;
                            time = timeLimit - remainingTime;
                            distance = distanceLimit - remainingDistance;
                        }
                    } else {
                        newRemainingTime = newRemainingTime - timeInPoi;
                        if (newRemainingTime > 0) {
                            findBestPath(newRemainingTime, newRemainingDistance, graph.getPointOfInterest(poiId), clonePath(newPath),
                                    reward + graph.getPointOfInterest(poiId).getVenue().getRating().getAvgRating());
                        }
                    }
                }
            }
        }
    }

    private static List<String> clonePath(List<String> path) {
        return new ArrayList<>(path);
    }
}
