package org.diary.api.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.lang.Assert;
import org.diary.api.ApiApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = ApiApplication.class)
@Transactional
@AutoConfigureMockMvc
public class TestMvcConfig {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private String result;

    public <T> String doGetWithToken(String url, T body, String token) {
        try {
            result = mvc.perform(get(url)
                    .content(mapper.writeValueAsBytes(body)) //HTTP Body에 데이터를 담는다
                    .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                    .header("authorization-token", token)
            ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
            .getResponse().getContentAsString();
        } catch (Exception e) {
            // Exception 발생 유도
            Assert.isTrue(false, ">> Error [" + e.getMessage() + "]");
        }

        return result;
    }

    public <T> String doGet(String url, T obj) {
        try {
            result = mvc.perform(get(url)
                            .content(mapper.writeValueAsBytes(obj)) //HTTP Body에 데이터를 담는다
                            .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                    ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
                    .getResponse().getContentAsString();
        } catch (Exception e) {
            // Exception 발생 유도
            Assert.isTrue(false, ">> Error [" + e.getMessage() + "]");
        }

        return result;
    }

    public <T> String doPostWithToken(String url, T obj, String token) {
        try {
            result = mvc.perform(post(url)
                    .content(mapper.writeValueAsBytes(obj)) //HTTP Body에 데이터를 담는다
                    .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                    .header("authorization-token", token)
            ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
            .getResponse().getContentAsString();
        } catch (Exception e) {
            // Exception 발생 유도
            Assert.isTrue(false, ">> Error [" + e.getMessage() + "]");
        }

        return result;
    }

    public <T> String doPost(String url, T obj) {
        try {
            result = mvc.perform(post(url)
                    .content(mapper.writeValueAsBytes(obj)) //HTTP Body에 데이터를 담는다
                    .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
            ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn()
            .getResponse().getContentAsString();
        } catch (Exception e) {
            // Exception 발생 유도
            Assert.isTrue(false, ">> Error [" + e.getMessage() + "]");
        }

        return result;
    }
}
