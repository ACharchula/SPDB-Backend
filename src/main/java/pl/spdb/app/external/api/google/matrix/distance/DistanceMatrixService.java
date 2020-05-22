package pl.spdb.app.external.api.google.matrix.distance;

import pl.spdb.app.model.matrix.Rows;

import java.util.List;

public interface DistanceMatrixService {
     Rows getDistanceMatrix(List<String> origins, List<String> destinations);
}
