package com.videos.mixing.backend.repository;

import com.videos.mixing.backend.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// ReviewRepository.java
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    List<Review> findAllById(List<ObjectId> ids);
}