package pl.spdb.app.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.spdb.app.model.mongodb.Rating;

import java.util.Optional;

public interface RatingRepository extends MongoRepository<Rating, String> {
    Optional<Rating> findById(String id);
}
