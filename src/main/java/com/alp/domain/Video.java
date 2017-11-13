package com.alp.domain;

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
    @GeneratedValue()
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "videoId='" + id + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                ", videoDuration='" + videoDuration + '\'' +
                ", videoCoverURL='" + videoCoverURL + '\'' +
                ", videoStatus='" + videoStatus + '\'' +
                ", videoCateId='" + videoCateId + '\'' +
                '}';
    }


}
