package org.diary.api.domain.token.controller;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.api.Api;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.api.domain.token.controller.model.RefreshTokenRequest;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.db.user.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/")
public class TokenController {
    private final TokenBusiness tokenBusiness;

    @PostMapping("refresh_token")
    public Api<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
        Long userId = tokenBusiness.validationAccessToken(refreshToken.getRefreshToken());

        return Api.OK(tokenBusiness.issueToken(UserEntity.builder().id(userId).build()));
    }
}
