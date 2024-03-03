package com.videos.mixing.backend.controllers;

import com.videos.mixing.backend.model.Review;
import com.videos.mixing.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){
        try {
            // Assuming validateAndSanitize() is a method you implement to validate and sanitize the input
            validateAndSanitize(payload);

            Review createdReview = reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId"));
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateAndSanitize(Map<String, String> payload) {
        // Implement validation and sanitization logic here
    }
}
