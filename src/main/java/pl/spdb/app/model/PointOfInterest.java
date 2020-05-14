package pl.spdb.app.model;

import pl.spdb.app.mongodb.model.Rating;

import java.util.List;

public class PointOfInterest extends Place {

    private String id;
    List<Category> categories;
    private Rating rating;

    public PointOfInterest(String name, String latitude, String longitude, String id, List<Category> categories, Rating rating) {
        super(name, latitude, longitude);
        this.id = id;
        this.categories = categories;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
