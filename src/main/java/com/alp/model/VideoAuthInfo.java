package com.alp.model;

public class VideoAuthInfo {

    private String videoId;
    private String videoPlayAuth;

    public VideoAuthInfo(String videoId, String videoPlayAuth) {
        this.videoId = videoId;
        this.videoPlayAuth = videoPlayAuth;
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
