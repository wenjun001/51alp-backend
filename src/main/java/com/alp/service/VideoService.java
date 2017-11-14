package com.alp.service;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
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


    DefaultProfile profile;
    DefaultAcsClient client;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public VideoService() {
        profile = DefaultProfile.getProfile("cn-shanghai", "LTAIDLQ49JsUXHTM", "4ibUm26TSF1KCo1HgPls6ZbB5X2Ymd");
        client = new DefaultAcsClient(profile);
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
