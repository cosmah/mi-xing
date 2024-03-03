package com.videos.mixing.backend.repository;

import com.videos.mixing.backend.model.Videos;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VideoRepository extends MongoRepository<Videos, ObjectId> {
    //we hide the Mongodb Id, so that the client is only exposed to imdb id
    Optional<Videos> findVideoByImdbId(String imdbId);
}
