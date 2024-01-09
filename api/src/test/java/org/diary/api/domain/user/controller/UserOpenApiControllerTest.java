package org.diary.api.domain.user.controller;

import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.user.controller.model.UserGoogleLoginRequest;
import org.diary.api.domain.user.controller.model.UserGoogleRegisterRequest;
import org.diary.api.domain.user.controller.model.UserKakaoLoginRequest;
import org.diary.api.domain.user.controller.model.UserKakaoRegisterRequest;
import org.diary.api.domain.user.fixture.UserEntityFixture;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserOpenApiControllerTest extends TestMvcConfig {

    @Autowired
    private UserRepository userRepository;

    private static final String BASE_URL = "/open-api/user";

    @Test
    void kakaoRegister() {
        // given
        // 가입 데이터
        UserKakaoRegisterRequest request = UserEntityFixture.anUserFixture().kakaoRegisterRequestBuild();

        // when
        String result = doPost(BASE_URL + "/kakao/register", request);

        // then
        System.out.println(result);
    }

    @Test
    void kakaoLogin() {
        // given
        // 회원가입
        UserEntity user = UserEntityFixture.anUserFixture().userKakaoEntityBuild();
        userRepository.save(user);

        // 로그인 요청 객체 세팅
        UserKakaoLoginRequest request = UserEntityFixture.anUserFixture().kakaoLoginRequestBuild();
        request.setId(user.getId());

        // when
        String result = doPost(BASE_URL + "/kakao/login", request);

        // then
        System.out.println(result);
    }


    @Test
    void googleRegister() {
        // given
        // 가입 데이터
        UserGoogleRegisterRequest request = UserEntityFixture.anUserFixture().googleRegisterRequestBuild();

        // when
        String result = doPost(BASE_URL + "/google/register", request);

        // then
        System.out.println(result);
    }

    @Test
    void googleLogin() {
        // given
        // 회원가입
        UserEntity user = UserEntityFixture.anUserFixture().userGoogleEntityBuild();
        user = userRepository.save(user);

        System.out.println(user.getId());

        // 로그인 요청 객체 세팅
        UserGoogleLoginRequest request = UserEntityFixture.anUserFixture().googleLoginRequestBuild();
        request.setId(user.getId());

        // when
        String result = doPost(BASE_URL + "/google/login", request);

        // then
        System.out.println(result);
    }

    @Test
    void find() {
        System.out.println(userRepository.findAll());
    }
}