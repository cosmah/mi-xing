package com.videos.mixing.backend.service;


import com.videos.mixing.backend.model.Review;
import com.videos.mixing.backend.model.Videos;
import com.videos.mixing.backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ReviewService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId){
        Review review = new Review(reviewBody);

        // update reviews array for a movie
        mongoTemplate.updateFirst(query(where("imdbId").is(imdbId)), new Update().push("reviews", review), Videos.class);

        return review;
    }
}
