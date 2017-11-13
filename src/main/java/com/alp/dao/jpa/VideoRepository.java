package com.alp.dao.jpa;

import com.alp.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface VideoRepository extends PagingAndSortingRepository<Video, Long> {
    Video findVideoByVideoId(String videoId);
    Page findAll(Pageable pageable);
}
