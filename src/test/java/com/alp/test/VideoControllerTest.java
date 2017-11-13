package com.alp.test;



import com.alp.api.rest.VideoController;
import com.alp.model.Video;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alp.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class VideoControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/v1/videos/[0-9]+";

    @InjectMocks
    VideoController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    //@Test
    public void shouldHaveEmptyDB() throws Exception {
//        mvc.perform(get("/v1/videos")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldCreateRetrieveDelete() throws Exception {
//        Video r1 = mockVideo("shouldCreateRetrieveDelete");
//        byte[] r1Json = toJson(r1);
//
//        //CREATE
//        MvcResult result = mvc.perform(post("/v1/videos")
//                .content(r1Json)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(redirectedUrlPattern(RESOURCE_LOCATION_PATTERN))
//                .andReturn();
//        String id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());
//
//        //RETRIEVE
//        mvc.perform(get("/v1/videos/" + id)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is((String) id)));
//
//
//        //DELETE
//        mvc.perform(delete("/v1/videos/" + id))
//                .andExpect(status().isNoContent());
//
//        //RETRIEVE should fail
//        mvc.perform(get("/v1/videos/" + id)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());


/*
JSONAssert.assertEquals(
  "{foo: 'bar', baz: 'qux'}",
  JSONObject.fromObject("{foo: 'bar', baz: 'xyzzy'}"));
 */
    }

    @Test
    public void shouldCreateAndUpdateAndDelete() throws Exception {
//        Video r1 = mockVideo("shouldCreateAndUpdate");
//        byte[] r1Json = toJson(r1);
//        //CREATE
//        MvcResult result = mvc.perform(post("/v1/videos")
//                .content(r1Json)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(redirectedUrlPattern(RESOURCE_LOCATION_PATTERN))
//                .andReturn();
//        String id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());
//
//        Video r2 = mockVideo("shouldCreateAndUpdate2");
//        r2.setVideoId(id);
//        byte[] r2Json = toJson(r2);
//
//        //UPDATE
//        result = mvc.perform(put("/v1/videos/" + id)
//                .content(r2Json)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent())
//                .andReturn();
//
//        //RETRIEVE updated
//        mvc.perform(get("/v1/videos/" + id)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is((String) id)));
//
//
//        //DELETE
//        mvc.perform(delete("/v1/videos/" + id))
//                .andExpect(status().isNoContent());
    }


    /*
    ******************************
     */

    private String getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return parts[parts.length - 1];
    }


    private Video mockVideo(String prefix) {
        Video r = new Video();
        r.setVideoId("test0001");
        return r;
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

    // match redirect header URL (aka Location header)
    private static ResultMatcher redirectedUrlPattern(final String expectedUrlPattern) {
        return new ResultMatcher() {
            public void match(MvcResult result) {
                Pattern pattern = Pattern.compile("\\A" + expectedUrlPattern + "\\z");
                assertTrue(pattern.matcher(result.getResponse().getRedirectedUrl()).find());
            }
        };
    }

}
