package com.alp.api.rest;

import com.alp.domain.Video;
import com.alp.domain.Video;
import com.alp.domain.Video;
import com.alp.exception.DataFormatException;
import com.alp.service.VideoService;
import com.alp.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wma on 11/12/17.
 */

@RestController
@RequestMapping(value = "/v1/videos")
@Api(tags = {"videos"})
public class VideoController extends AbstractRestHandler {
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a video resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createVideo(@RequestBody Video video,
                            HttpServletRequest request, HttpServletResponse response) {
        Video createdVideo = this.videoService.createVideo(video);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdVideo.getId()).toString());
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single video.", notes = "You have to provide a valid video ID.")
    public
    @ResponseBody
    Video getVideo(@ApiParam(value = "The ID of the video.", required = true)
                   @PathVariable("id") Long id,
                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        Video video = this.videoService.getVideo(id);
        checkResourceFound(video);
        return video;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a video resource.", notes = "You have to provide a valid video ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateVideo(@ApiParam(value = "The ID of the existing video resource.", required = true)
                            @PathVariable("id") Long id, @RequestBody Video video,
                            HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.videoService.getVideo(id));
        if (id != video.getId()) throw new DataFormatException("ID doesn't match!");
        this.videoService.updateVideo(video);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a video resource.", notes = "You have to provide a valid video ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteVideo(@ApiParam(value = "The ID of the existing video resource.", required = true)
                            @PathVariable("id") Long id, HttpServletRequest request,
                            HttpServletResponse response) {
        checkResourceFound(this.videoService.getVideo(id));
        this.videoService.deleteVideo(id);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all Videos.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Video> getAllVideo(@ApiParam(value = "The page number (zero-based)", required = true)
                            @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                            @ApiParam(value = "Tha page size", required = true)
                            @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                            HttpServletRequest request, HttpServletResponse response) {
        return this.videoService.getAllVideos(page, size);
    }
}
