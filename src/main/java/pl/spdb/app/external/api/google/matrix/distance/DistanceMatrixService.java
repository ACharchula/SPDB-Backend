package pl.spdb.app.external.api.google.matrix.distance;

import java.util.List;

public interface DistanceMatrixService {
     String getDistanceMatrix(List<String> origins, List<String> destinations);
}
