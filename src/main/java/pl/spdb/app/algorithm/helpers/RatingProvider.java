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
                float avgRating = generateAvgRating();
                Rating rating = new Rating(venue.getId(), avgRating);
                venue.setRating(rating);
                ratingRepository.save(rating);
            } else {
                venue.setRating(optionalRating.get());
            }
        }
    }

    private float generateAvgRating() {
        return 1 + random.nextFloat() * (6 - 1);
    }
}
