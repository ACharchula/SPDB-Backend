package pl.spdb.app.model.api;

import pl.spdb.app.external.api.foursquare.places.AllCategories;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
    private long timeInPoi;
    private int minimalRating;
    private final List<String> categories = new ArrayList<>();
    private long additionalTime;
    private long additionalDistance;
    private long searchingStart;

    public Parameters(long timeInPoi, int minimalRating, String categories, long additionalTime, long additionalDistance, long searchingStart) {
        this.timeInPoi = timeInPoi;
        this.minimalRating = minimalRating;
        this.additionalTime = additionalTime;
        this.additionalDistance = additionalDistance;
        this.searchingStart = searchingStart;
        setCategories(categories);
    }

    public void setCategories(String categories) {
        String[] ids = categories.split(",");
        for (String id : ids) {
            this.categories.add(AllCategories.CATEGORIES.get(id));
        }
    }

    public long getTimeInPoi() {
        return timeInPoi;
    }

    public void setTimeInPoi(long timeInPoi) {
        this.timeInPoi = timeInPoi;
    }

    public int getMinimalRating() {
        return minimalRating;
    }

    public void setMinimalRating(int minimalRating) {
        this.minimalRating = minimalRating;
    }

    public List<String> getCategories() {
        return categories;
    }

    public long getAdditionalTime() {
        return additionalTime;
    }

    public void setAdditionalTime(long additionalTime) {
        this.additionalTime = additionalTime;
    }

    public long getAdditionalDistance() {
        return additionalDistance;
    }

    public void setAdditionalDistance(long additionalDistance) {
        this.additionalDistance = additionalDistance;
    }

    public long getSearchingStart() {
        return searchingStart;
    }

    public void setSearchingStart(long searchingStart) {
        this.searchingStart = searchingStart;
    }
}
