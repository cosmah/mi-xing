package com.videos.mixing.backend.service;

import com.videos.mixing.backend.model.Review;
import com.videos.mixing.backend.model.Videos;
import com.videos.mixing.backend.repository.VideoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.videos.mixing.backend.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Videos> allVideos(){
        return videoRepository.findAll();
    }

    // VideoService.java
    public Optional<Videos> singleVideo(String imdbId){
        Optional<Videos> video = videoRepository.findVideoByImdbId(imdbId);
        if (video.isPresent()) {
            List<ObjectId> reviewIds = video.get().getReviews().stream()
                    .map(Review::getId)
                    .collect(Collectors.toList());
            List<Review> reviews = reviewRepository.findAllById(reviewIds);
            video.get().setReviews(reviews);
        }
        return video;
    }
}