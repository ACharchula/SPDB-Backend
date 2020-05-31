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

    private static final int MAX_NUMBER_OF_POI = 10;

    private static Graph graph;
    private static String endId;
    private static long timeInPoi;
    private static long timeLimit;
    private static long distanceLimit;

    private static List<String> bestPath;
    private static double bestPathReward;
    private static long time;
    private static long distance;

    //TODO maybe use number of ratings also
    public static FinalResult run(Graph graph, String startId, String endId, long timeInPoiInSeconds, long searchingStart,
                                  long timeLimitInSeconds, long distanceLimitInMeters) {
        Algorithm.graph = graph;
        Algorithm.endId = endId;
        Algorithm.timeInPoi = timeInPoiInSeconds;
        Algorithm.timeLimit = timeLimitInSeconds;
        Algorithm.distanceLimit = distanceLimitInMeters;

        Algorithm.bestPath = new ArrayList<>(Arrays.asList(startId, endId));
        Algorithm.bestPathReward = 0;
        Algorithm.time = 0;
        Algorithm.distance = 0;

        findBestPath(timeLimitInSeconds, distanceLimitInMeters, graph.getPointOfInterest(startId),
                new ArrayList<>(Collections.singletonList(startId)), 0.0, searchingStart);

        return prepareResult();
    }

    private static void findBestPath(long remainingTime, long remainingDistance, PointOfInterest poi, List<String> path,
                                     double reward, long searchingStart) {
        for (String poiId : poi.getRoads().keySet()) {
            if (!path.contains(poiId)) {
                //don't move back to poi which are earlier in trip
                if (!isProperGroupId(poiId, poi)) continue;

                Road road = poi.getRoads().get(poiId);
                //If there is a search time set, check if road is long enough
                if (searchingStart != 0 && searchingStart > road.getDuration().getValue()) continue;
                else if (searchingStart != 0) searchingStart = 0;

                long newRemainingTime = remainingTime - road.getDuration().getValue();
                long newRemainingDistance = remainingDistance - road.getDistance().getValue();
                if (newRemainingTime >= 0 && newRemainingDistance >= 0) {
                    List<String> newPath = clonePath(path);
                    newPath.add(poiId);
                    if (poiId.equals(endId)) {
                        if (reward > bestPathReward) {
                            bestPath = newPath;
                            bestPathReward = reward;
                            time = timeLimit - newRemainingTime;
                            distance = distanceLimit - newRemainingDistance;
                        }
                    } else {
                        //if no time or distance to spent and place is not end -> return
                        if (newRemainingTime == 0 || newRemainingDistance == 0) continue;
                        // if too many poi -> return
                        if (newPath.size() == MAX_NUMBER_OF_POI + 2) continue;

                        newRemainingTime = newRemainingTime - timeInPoi;
                        if (newRemainingTime > 0) {
                            findBestPath(newRemainingTime, newRemainingDistance, graph.getPointOfInterest(poiId), clonePath(newPath),
                                    reward + graph.getPointOfInterest(poiId).getVenue().getRating().getAvgRating(),
                                    searchingStart);
                        }
                    }
                }
            }
        }
    }

    private static List<String> clonePath(List<String> path) {
        return new ArrayList<>(path);
    }

    private static FinalResult prepareResult() {
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

    private static boolean isProperGroupId(String poiId, PointOfInterest lastPoint) {
        int lastGroupId = lastPoint.getVenue().getGroupId();
        int nextGroupId = graph.getPointOfInterest(poiId).getVenue().getGroupId();
        return nextGroupId >= lastGroupId;
    }
}
