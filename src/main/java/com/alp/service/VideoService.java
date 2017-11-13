package com.alp.service;


import com.alp.dao.jpa.VideoRepository;
import com.alp.domain.Video;
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
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public VideoService() {
    }

    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video getVideo(long id) {
        return videoRepository.findOne(id);
    }

    public void updateVideo(Video video) {
        videoRepository.save(video);
    }

    public void deleteVideo(Long id) {
        videoRepository.delete(id);
    }

    public Page<Video> getAllVideos(Integer page, Integer size) {
        Page pageOfVideos = videoRepository.findAll(new PageRequest(page, size));
        if (size > 50) {
            counterService.increment("51alp.videoservice.getAll.largePayload");
        }
        return pageOfVideos;
    }
}
