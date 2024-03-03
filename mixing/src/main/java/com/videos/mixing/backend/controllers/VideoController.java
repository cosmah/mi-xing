package com.videos.mixing.backend.controllers;

import com.videos.mixing.backend.model.VideoMetadata;
import com.videos.mixing.backend.model.Videos;
import com.videos.mixing.backend.service.VideoService;
import com.videos.mixing.backend.service.VideoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoUploadService videoUploadService;

    // Endpoint for fetching all videos
    @GetMapping
    public ResponseEntity<List<Videos>> getAllVideos() {
        return new ResponseEntity<>(videoService.allVideos(), HttpStatus.OK);
    }

    // Endpoint for fetching a single video by IMDb ID
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Videos>> getSingleVideo(@PathVariable String imdbId) {
        return new ResponseEntity<>(videoService.singleVideo(imdbId), HttpStatus.OK);
    }

    // Endpoint for uploading a video
    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file,
                                         @RequestParam("title") String title,
                                         @RequestParam("description") String description) {
        try {
            // Create video metadata from title and description
            VideoMetadata metadata = new VideoMetadata(title, description);

            // Upload video to Google Cloud Storage and save metadata to MongoDB
            Videos video = videoUploadService.uploadVideo(file, metadata);

            // Return the uploaded video
            return ResponseEntity.ok(video);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload video.");
        }
    }
}
