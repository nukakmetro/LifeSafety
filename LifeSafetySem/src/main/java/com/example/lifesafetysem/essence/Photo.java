package com.example.lifesafetysem.essence;

import java.time.LocalDateTime;

public class Photo {
    private long photoId;
    private String fileName;
    private String description;
    private LocalDateTime uploadDate;

    public Photo(long photoId, String fileName, String description, LocalDateTime uploadDate) {
        this.photoId = photoId;
        this.fileName = fileName;
        this.description = description;
        this.uploadDate = uploadDate;
    }

    public Photo() {
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
