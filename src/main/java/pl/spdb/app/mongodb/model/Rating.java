package pl.spdb.app.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Ratings")
public class Rating {

    @Id
    private String id;
    private int avg_rating;
    private int number_of_ratings;

    public Rating(String id, int avg_rating, int number_of_ratings) {
        this.id = id;
        this.avg_rating = avg_rating;
        this.number_of_ratings = number_of_ratings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvgRating() {
        return avg_rating;
    }

    public void setAvgRating(int avg_rating) {
        this.avg_rating = avg_rating;
    }

    public int getNumberOfRatings() {
        return number_of_ratings;
    }

    public void setNumberOfRatings(int number_of_ratings) {
        this.number_of_ratings = number_of_ratings;
    }
}
