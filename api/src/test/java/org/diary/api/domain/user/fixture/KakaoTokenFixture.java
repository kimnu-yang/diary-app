package org.diary.api.domain.user.fixture;

import org.diary.api.domain.user.controller.model.KakaoLoginRequest;
import org.diary.api.domain.user.controller.model.KakaoRegisterRequest;
import org.diary.db.token.KakaoTokenEntity;
import org.diary.db.user.UserEntity;

import java.time.LocalDateTime;

public class KakaoTokenFixture {

    private Long userId;

    private String accessToken = "teqeqgheahahharhv214125ea";
    private LocalDateTime accessTokenExpireAt = LocalDateTime.of(2025, 1, 9, 12, 0, 0);
    private String idToken = "qr13uiniufbneafadf";
    private String refreshToken = "ewkjrlewrelkr12415advidajrk13oi";
    private String scopes = "{asd: asD:as d:Asd :as d:as d:asd :asd}";

    private UserEntity user = new UserEntity();


    public static KakaoTokenFixture anUserFixture() {
        return new KakaoTokenFixture();
    }

    public KakaoTokenFixture kakaoTokenFixtureId(final Long userId) {
        this.userId = userId;
        return this;
    }

    public KakaoTokenFixture kakaoTokenFixtureUser(final UserEntity user) {
        this.user = user;
        return this;
    }

    public KakaoTokenEntity kakaoTokenEntityBuild() {
        KakaoTokenEntity tokenEntity = new KakaoTokenEntity();

        tokenEntity.setAccessToken(accessToken);
        tokenEntity.setAccessTokenExpireAt(accessTokenExpireAt);
        tokenEntity.setIdToken(idToken);
        tokenEntity.setRefreshToken(refreshToken);
        tokenEntity.setScopes(scopes);

        return tokenEntity;
    }

    public KakaoLoginRequest loginRequestBuild() {
        return new KakaoLoginRequest(
                userId,
                accessToken
        );
    }

    public KakaoRegisterRequest RegisterRequestBuild() {
        return new KakaoRegisterRequest(
                accessToken,
                accessTokenExpireAt,
                idToken,
                refreshToken,
                scopes
        );
    }
}
