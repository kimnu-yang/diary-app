package org.diary.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.api.Api;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.domain.user.controller.model.KakaoLoginRequest;
import org.diary.api.domain.user.controller.model.KakaoRegisterRequest;
import org.diary.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    /**
     * 회원가입
     *
     * @param request - 회원가입 정보 (카카오 엑세스 토큰 정보)
     * @return Api<UserResponse>
     */
    @PostMapping("/kakao/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody
            KakaoRegisterRequest request
    ) {
        var response = userBusiness.register(request);
        return Api.OK(response);
    }

    /**
     * 로그인
     *
     * @param request - 로그인 정보
     * @return Api<TokenResponse>
     */
    @PostMapping("/kakao/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody
            KakaoLoginRequest request
    ) {
        var response = userBusiness.login(request);
        return Api.OK(response);
    }



}
