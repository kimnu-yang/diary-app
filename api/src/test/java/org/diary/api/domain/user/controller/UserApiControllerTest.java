package org.diary.api.domain.user.controller;

import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.user.fixture.UserEntityFixture;
import org.diary.api.domain.user.service.UserService;
import org.diary.db.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserApiControllerTest extends TestMvcConfig {
    @Autowired
    UserService userService;
    @Autowired
    TokenBusiness tokenBusiness;

    private static final String BASE_URL = "/api/user";

    @Test
    void me() {
        // given
        // 유저 생성
        UserEntity user = UserEntityFixture.anUserFixture().userKakaoEntityBuild();
        user = userService.register(user);

        // 토큰 가져오기
        TokenResponse token = tokenBusiness.issueToken(user);

        //when
        // 발급한 토큰으로 /api/user/me api 실행
        String result = doGetWithToken(BASE_URL + "/me", "", token.getAccessToken());

        // then
        System.out.println(result);
    }
}