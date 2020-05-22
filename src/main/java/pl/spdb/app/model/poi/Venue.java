package pl.spdb.app.model.poi;

import pl.spdb.app.model.mongodb.Rating;

public class Venue {
    private String id;
    private String name;
    private VenueLocation location;
    private Rating rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VenueLocation getLocation() {
        return location;
    }

    public void setLocation(VenueLocation location) {
        this.location = location;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getLatitudeAndLongitude() {
        return location.getLat() + "," + location.getLng();
    }
}
