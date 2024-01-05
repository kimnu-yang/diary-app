package org.diary.api.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.lang.Assert;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.domain.user.common.TestConfig;
import org.diary.api.domain.user.controller.model.UserLoginRequest;
import org.diary.api.domain.user.controller.model.UserRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserOpenApiControllerTest extends TestConfig {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    private static final String BASE_URL = "/open-api/user";
    private static final String PASSWORD = "1234";
    private static final String EMAIL = "testEmail";
    private static final String ADDR = "testAddr";
    private static final String NAME = "testName";

    @Test
    void register() throws Exception {
        // given
        // 가입 데이터
        UserRegisterRequest request = new UserRegisterRequest();

        request.setPassword(PASSWORD);
        request.setEmail(EMAIL);
        request.setAddress(ADDR);
        request.setName(NAME);

        // Object를 JSON으로 변환
        String body = mapper.writeValueAsString(request);

        // when
        // then
        mvc.perform(post(BASE_URL + "/register")
                .content(body) //HTTP Body에 데이터를 담는다
                .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

        // 응답 데이터 뽑기
        // String resultText = result.getResponse().getContentAsString();

        // System.out.println(resultText);
    }

    @Test
    void login() throws Exception {
        // given
        // 회원가입
        try {
            register();
        } catch (Exception e) {
            Assert.notNull(null, "파싱 오류");
        }

        // 로그인 데이터
        UserLoginRequest request = new UserLoginRequest();

        request.setPassword(PASSWORD);
        request.setEmail(EMAIL);

        String body = mapper.writeValueAsString(request);

        // when
        // then
        mvc.perform(post(BASE_URL + "/login")
                .content(body) //HTTP Body에 데이터를 담는다
                .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}