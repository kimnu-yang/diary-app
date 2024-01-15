package org.diary.api.domain.user.fixture;

import org.diary.api.domain.user.controller.model.UserGoogleLoginRequest;
import org.diary.api.domain.user.controller.model.UserGoogleRegisterRequest;
import org.diary.api.domain.user.controller.model.UserKakaoLoginRequest;
import org.diary.api.domain.user.controller.model.UserKakaoRegisterRequest;
import org.diary.db.user.UserEntity;
import org.diary.db.user.enums.UserStatus;

import java.time.LocalDateTime;

public class UserEntityFixture {

    private Long id;

    private UserStatus status = UserStatus.REGISTERED;

    private Long kakaoUserId = 134616161L;

    private String kakaoToken = "kakaoToken";

    private Long googleUserId = 535135412123L;

    private LocalDateTime registeredAt = LocalDateTime.now();

    private LocalDateTime unregisteredAt = null;

    private LocalDateTime lastLoginAt = LocalDateTime.now();

    public static UserEntityFixture anUserFixture() {
        return new UserEntityFixture();
    }

    public UserEntityFixture userFixtureId(final Long id) {
        this.id = id;
        return this;
    }

    public UserEntity userKakaoEntityBuild() {
        return UserEntity.builder().kakaoUserId(kakaoUserId).status(status).registeredAt(registeredAt).lastLoginAt(lastLoginAt).unregisteredAt(unregisteredAt).build();
    }

    public UserEntity userGoogleEntityBuild() {
        return UserEntity.builder().googleUserId(googleUserId).status(status).registeredAt(registeredAt).lastLoginAt(lastLoginAt).unregisteredAt(unregisteredAt).build();
    }

    public UserKakaoLoginRequest kakaoLoginRequestBuild() {
        return UserKakaoLoginRequest.builder().kakaoToken(kakaoToken).build();
    }

    public UserKakaoRegisterRequest kakaoRegisterRequestBuild() {
        return UserKakaoRegisterRequest.builder().kakaoUserId(kakaoUserId).build();
    }

    public UserGoogleLoginRequest googleLoginRequestBuild() {
        return UserGoogleLoginRequest.builder().id(id).googleUserId(googleUserId).build();
    }

    public UserGoogleRegisterRequest googleRegisterRequestBuild() {
        return UserGoogleRegisterRequest.builder().googleUserId(googleUserId).build();
    }


}
