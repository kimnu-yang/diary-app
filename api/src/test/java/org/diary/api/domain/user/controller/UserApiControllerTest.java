package org.diary.api.domain.user.controller;

import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.domain.user.common.TestConfig;
import org.diary.api.domain.user.controller.model.UserLoginRequest;
import org.diary.api.domain.user.controller.model.UserRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserApiControllerTest extends TestConfig {
    @Autowired
    UserBusiness userBusiness;

    @Autowired
    MockMvc mvc;

    private static final String BASE_URL = "/api/user";

    @Test
    void me() throws Exception {
        // given
        // 유저 생성
        UserRegisterRequest req = new UserRegisterRequest();

        req.setAddress("addr");
        req.setEmail("email");
        req.setName("name");
        req.setPassword("1234");

        userBusiness.register(req);

        UserLoginRequest loginReq = new UserLoginRequest();
        loginReq.setEmail("email");
        loginReq.setPassword("1234");

        // 토큰 가져오기
        TokenResponse tokenResponse = userBusiness.login(loginReq);

        //when
        // 발급한 토큰으로 /api/user/me api 실행
        MvcResult result = mvc.perform(get(BASE_URL + "/me")
                .content("") //HTTP Body에 데이터를 담는다
                .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                .header("authorization-token", tokenResponse.getAccessToken())
        ).andExpect(status().isOk()).andReturn();

        // then
        String resultText = result.getResponse().getContentAsString();

        System.out.println(resultText);
    }
}