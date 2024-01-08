package org.diary.api.domain.user.controller;

import io.jsonwebtoken.lang.Assert;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.common.mock.TestMock;
import org.diary.api.domain.user.controller.model.UserLoginRequest;
import org.diary.api.domain.user.controller.model.UserRegisterRequest;
import org.diary.api.domain.user.fixture.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserOpenApiControllerTest extends TestMock {

    @Autowired
    private UserBusiness userBusiness;

    private static final String BASE_URL = "/open-api/user";

    @Test
    void register() {
        // given
        // 가입 데이터
        UserRegisterRequest request = UserFixture.anUserFixture().RegisterRequestBuild();

        // when
        String result = doPost(BASE_URL + "/register", request);

        // then
         System.out.println(result);
    }

    @Test
    void login() {
        // given
        // 회원가입
        try {
            register();
        } catch (Exception e) {
            Assert.notNull(null, "오류 발생 [" + e.getMessage() + "]");
        }

        // 로그인 데이터
        UserLoginRequest request = UserFixture.anUserFixture().loginRequestBuild();

        // when
       String result = doPost(BASE_URL + "/login", request);

        // then
       System.out.println(result);
    }
}