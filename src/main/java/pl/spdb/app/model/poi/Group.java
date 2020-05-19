package pl.spdb.app.model.poi;

import java.util.List;

public class Group {
    private List<Result> results;
    private int totalResults;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
