package pl.spdb.app.algorithm.helpers;

import pl.spdb.app.model.poi.Venue;
import pl.spdb.app.mongodb.RatingRepository;
import pl.spdb.app.model.mongodb.Rating;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RatingProvider {

    private final RatingRepository ratingRepository;
    private Random random;

    public RatingProvider(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void provideRatings(List<Venue> venues) {
        random = new Random(new Date().getTime()); //set new seed for generating values

        for (Venue venue : venues) {
            Optional<Rating> optionalRating = ratingRepository.findById(venue.getId());

            if (optionalRating.isEmpty()) {
                int numberOfRatings = generateNumberOfRatings();
                int avgRating = (numberOfRatings == 0) ? 0 : generateAvgRating();
                Rating rating = new Rating(venue.getId(), avgRating, numberOfRatings);
                venue.setRating(rating);
                ratingRepository.save(rating);
            } else {
                venue.setRating(optionalRating.get());
            }
        }
    }

    private int generateAvgRating() {
        return random.nextInt(5) + 1; // [1,5]
    }

    private int generateNumberOfRatings() {
        return random.nextInt(1001); // [0,1000]
    }
}
