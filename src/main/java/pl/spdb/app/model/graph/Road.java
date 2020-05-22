package pl.spdb.app.model.graph;

import pl.spdb.app.model.matrix.Element;
import pl.spdb.app.model.route.TextValue;

public class Road {
    TextValue distance;
    TextValue duration;

    public Road(Element element) {
        this.distance = element.getDistance();
        this.duration = element.getDuration();
    }

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
