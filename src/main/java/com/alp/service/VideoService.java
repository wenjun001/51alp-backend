package com.alp.service;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import com.alp.dao.jpa.VideoRepository;
import com.alp.model.Video;
import com.alp.model.VideoAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by wma on 11/12/17.
 */
@Service
public class VideoService {
    private static String accessKeyId = "LTAIDLQ49JsUXHTM";
    private static String accessKeySecret = "4ibUm26TSF1KCo1HgPls6ZbB5X2Ymd";
    DefaultAcsClient client;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public VideoService() {
        client =  new DefaultAcsClient(
                DefaultProfile.getProfile("cn-shanghai",accessKeyId,accessKeySecret));
    }

    public VideoAuthInfo refreshUploadVideo(String videoId){

        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        RefreshUploadVideoResponse response = null;
        try {
            request.setVideoId(videoId);
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            System.out.println("RefreshUploadVideoRequest Server Exception:");
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("RefreshUploadVideoRequest Client Exception:");
            e.printStackTrace();
        }
        VideoAuthInfo videoAuthInfo = new VideoAuthInfo(videoId,"",response.getUploadAuth());
        return videoAuthInfo;
    }


    public VideoAuthInfo createUploadVideo(Video video){
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        CreateUploadVideoResponse response = null;
        try {
              /*必选，视频源文件名称（必须带后缀, 支持 ".3gp", ".asf", ".avi", ".dat", ".dv", ".flv", ".f4v", ".gif", ".m2t", ".m3u8", ".m4v", ".mj2", ".mjpeg", ".mkv", ".mov", ".mp4", ".mpe", ".mpg", ".mpeg", ".mts", ".ogg", ".qt", ".rm", ".rmvb", ".swf", ".ts", ".vob", ".wmv", ".webm"".aac", ".ac3", ".acm", ".amr", ".ape", ".caf", ".flac", ".m4a", ".mp3", ".ra", ".wav", ".wma"）*/
            request.setFileName(video.getVideoFileName());
            //必选，视频标题
            request.setTitle(video.getVideoTitle());
            //可选，分类ID
            request.setCateId(0);
            ////可选，视频标签，多个用逗号分隔
            // request.setTags("标签1,标签2");
            //可选，视频描述
             request.setDescription(video.getVideoDescription());
            //可选，视频源文件字节数
            //request.setFileSize(0);
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            System.out.println("CreateUploadVideoRequest Server Exception:");
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("CreateUploadVideoRequest Client Exception:");
            e.printStackTrace();
        }

        VideoAuthInfo videoAuthInfo = new VideoAuthInfo(response.getVideoId(),response.getUploadAddress(),response.getUploadAuth());
        return videoAuthInfo;


    }

    public VideoAuthInfo getVideoAuthInfo(String vidoId){
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(vidoId);
        GetVideoPlayAuthResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
            throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
        } catch (ClientException e) {
            e.printStackTrace();
            throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
        }
        VideoAuthInfo videoAuthInfo = new VideoAuthInfo(vidoId,response.getPlayAuth());
        return videoAuthInfo;
    }

    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video getVideo(String videoId) {
        return videoRepository.findVideoByVideoId(videoId);
    }

    public void updateVideo(Video video) {
        videoRepository.save(video);
    }

    public void deleteVideo(Long id) {
        //videoRepository.delete(id);
    }

    public Page<Video> getAllVideos(Integer page, Integer size) {
        Page pageOfVideos = videoRepository.findAll(new PageRequest(page, size));
        if (size > 50) {
            counterService.increment("51alp.videoservice.getAll.largePayload");
        }
        return pageOfVideos;
    }
}
