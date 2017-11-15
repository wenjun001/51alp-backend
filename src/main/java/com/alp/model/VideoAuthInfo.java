package com.alp.model;

public class VideoAuthInfo {

    private String videoId;
    private String videoPlayAuth;
    private String videoUploadAddress;
    private String videoUploadAuth;

    public VideoAuthInfo(String videoId, String videoPlayAuth) {
        this.videoId = videoId;
        this.videoPlayAuth = videoPlayAuth;
    }

    public VideoAuthInfo(String videoId, String videoUploadAddress, String videoUploadAuth) {
        this.videoId = videoId;
        this.videoUploadAddress = videoUploadAddress;
        this.videoUploadAuth = videoUploadAuth;
    }

    public String getVideoUploadAddress() {
        return videoUploadAddress;
    }

    public void setVideoUploadAddress(String videoUploadAddress) {
        this.videoUploadAddress = videoUploadAddress;
    }

    public String getVideoUploadAuth() {
        return videoUploadAuth;
    }

    public void setVideoUploadAuth(String videoUploadAuth) {
        this.videoUploadAuth = videoUploadAuth;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoPlayAuth() {
        return videoPlayAuth;
    }

    public void setVideoPlayAuth(String videoPlayAuth) {
        this.videoPlayAuth = videoPlayAuth;
    }
}
