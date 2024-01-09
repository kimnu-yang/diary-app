package org.diary.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Business;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.token.converter.TokenConverter;
import org.diary.api.domain.token.service.TokenService;
import org.diary.db.BaseEntity;
import org.diary.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * 1. user entity user id 추출
     * 2. access , refresh token 발행
     * 3. converter -> token response로 변경
     */
    public TokenResponse issueToken(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(BaseEntity::getId)
                .map(userId -> {
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.tokenResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationAccessToken(String accessToken) {
        return tokenService.validationToken(accessToken);
    }
}
