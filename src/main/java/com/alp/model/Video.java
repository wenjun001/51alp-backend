package com.alp.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "video")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Video {

    @Id
    private String videoId;

    @Column()
    private String videoFileName;
    @Column()
    private String videoTitle;
    @Column()
    private String videoDescription;
    @Column()
    private String videoDuration;
    @Column()
    private String videoCoverURL;
    @Column()
    private String videoStatus;
    @Column()
    private String videoCateId;




    public Video() {
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoCoverURL() {
        return videoCoverURL;
    }

    public void setVideoCoverURL(String videoCoverURL) {
        this.videoCoverURL = videoCoverURL;
    }

    public String getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(String videoStatus) {
        this.videoStatus = videoStatus;
    }

    public String getVideoCateId() {
        return videoCateId;
    }

    public void setVideoCateId(String videoCateId) {
        this.videoCateId = videoCateId;
    }



    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                ", videoDuration='" + videoDuration + '\'' +
                ", videoCoverURL='" + videoCoverURL + '\'' +
                ", videoStatus='" + videoStatus + '\'' +
                ", videoCateId='" + videoCateId + '\'' +
                '}';
    }


}
