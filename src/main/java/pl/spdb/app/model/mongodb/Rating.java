package pl.spdb.app.model.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Ratings")
public class Rating {

    @Id
    private String id;
    private float avg_rating;

    public Rating(String id, float avg_rating) {
        this.id = id;
        this.avg_rating = avg_rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAvgRating() {
        return avg_rating;
    }

    public void setAvgRating(float avg_rating) {
        this.avg_rating = avg_rating;
    }
}
