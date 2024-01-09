package org.diary.api.domain.user.controller;

import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.user.controller.model.KakaoLoginRequest;
import org.diary.api.domain.user.controller.model.KakaoRegisterRequest;
import org.diary.api.domain.user.fixture.KakaoTokenFixture;
import org.diary.api.domain.user.fixture.UserEntityFixture;
import org.diary.db.token.KakaoTokenEntity;
import org.diary.db.token.KakaoTokenRepository;
import org.diary.db.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class KakaoApiControllerTest extends TestMvcConfig {
    @Autowired
    private KakaoTokenRepository kakaoTokenRepository;

    private static final String BASE_URL = "/open-api/user/kakao";

    @Test
    void register() {
        // given
        // 가입 데이터
        KakaoRegisterRequest request = KakaoTokenFixture.anUserFixture().RegisterRequestBuild();

        // when
        String result = doPost(BASE_URL + "/register", request);

        // then
        System.out.println(result);
    }

    @Test
    void login() {
        // given
        // 회원가입
        UserEntity user = UserEntityFixture.anUserFixture().userEntityBuild();
        KakaoTokenEntity kakao = KakaoTokenFixture.anUserFixture().kakaoTokenEntityBuild();
        kakao.setUser(user);

        kakaoTokenRepository.save(kakao);

        KakaoLoginRequest request = KakaoTokenFixture.anUserFixture().loginRequestBuild();

        request.setUserId(kakao.getUser().getId());

        // when
        String result = doPost(BASE_URL + "/login", request);

        // then
        System.out.println(result);
    }

    @Test
    void find() {
        System.out.println(kakaoTokenRepository.findAll());
    }
}