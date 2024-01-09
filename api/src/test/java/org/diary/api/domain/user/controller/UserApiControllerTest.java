package org.diary.api.domain.user.controller;

import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.domain.user.controller.model.UserKakaoLoginRequest;
import org.diary.api.domain.user.controller.model.UserKakaoRegisterRequest;
import org.diary.api.domain.user.fixture.UserEntityFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserApiControllerTest extends TestMvcConfig {
    @Autowired
    UserBusiness userBusiness;

    private static final String BASE_URL = "/api/user";

    @Test
    void me() {
        // given
        // 유저 생성
        UserKakaoRegisterRequest req = UserEntityFixture.anUserFixture().kakaoRegisterRequestBuild();
        UserKakaoLoginRequest loginReq = UserEntityFixture.anUserFixture().kakaoLoginRequestBuild();
        loginReq.setId(userBusiness.kakaoRegister(req).getId());

        // 토큰 가져오기
        TokenResponse tokenResponse = userBusiness.kakaoLogin(loginReq);

        //when
        // 발급한 토큰으로 /api/user/me api 실행
        String result = doGetWithToken(BASE_URL + "/me", "", tokenResponse.getAccessToken());

        // then
        System.out.println(result);
    }
}