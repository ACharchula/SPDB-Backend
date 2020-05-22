package pl.spdb.app.model.matrix;

import pl.spdb.app.model.route.TextValue;

public class Element {
    private TextValue distance;
    private TextValue duration;

    public TextValue getDistance() {
        return distance;
    }

    public void setDistance(TextValue distance) {
        this.distance = distance;
    }

    public TextValue getDuration() {
        return duration;
    }

    public void setDuration(TextValue duration) {
        this.duration = duration;
    }
}
