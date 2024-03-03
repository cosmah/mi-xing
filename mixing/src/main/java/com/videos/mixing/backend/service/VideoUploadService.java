package com.videos.mixing.backend.service;

import com.videos.mixing.backend.model.Videos;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.videos.mixing.backend.model.VideoMetadata;
import com.videos.mixing.backend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class VideoUploadService {

    @Autowired
    private VideoRepository videoRepository;

    private final Storage storage;

    public VideoUploadService() {
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public Videos uploadVideo(MultipartFile file, VideoMetadata metadata) throws IOException {
        // Get a reference to the bucket
        Bucket bucket = storage.get("mixing"); // Replace "mixing" with your bucket name

        // Upload video file to Google Cloud Storage
        String blobName = "videos/" + UUID.randomUUID() + "/" + file.getOriginalFilename();
        bucket.create(blobName, file.getBytes(), file.getContentType());

        // Save video metadata to MongoDB
        Videos video = new Videos();
        video.setTitle(metadata.getTitle());
        video.setDescription(metadata.getDescription());
        video.setUrl("gs://" + bucket.getName() + "/" + blobName);
        return videoRepository.save(video);
    }
}
