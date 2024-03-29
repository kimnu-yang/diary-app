package org.diary.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.api.Api;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.domain.user.controller.model.UserGoogleLoginRequest;
import org.diary.api.domain.user.controller.model.UserGoogleRegisterRequest;
import org.diary.api.domain.user.controller.model.UserKakaoLoginRequest;
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
    private final TokenBusiness tokenBusiness;

    /**
     * 회원가입 (미사용 - 로그인에서 함께 처리하기로 결정)
     *
     * @param request - 카카오 ID
     * @return Api<UserResponse>
     */
//    @PostMapping("/kakao/register")
//    public Api<UserResponse> register(
//            @Valid
//            @RequestBody
//            UserKakaoRegisterRequest request
//    ) {
//        var response = userBusiness.kakaoRegister(request);
//        return Api.OK(response);
//    }

    /**
     * 로그인
     *
     * @param request - 로그인 정보
     * @return Api<TokenResponse>
     */
    @PostMapping("/kakao/login")
    public Api<UserResponse> login(
            @Valid
            @RequestBody
            UserKakaoLoginRequest request
    ) {
        var response = userBusiness.kakaoLogin(request);
        return Api.OK(response);
    }


    /**
     * 회원가입
     *
     * @param request - 카카오 ID
     * @return Api<UserResponse>
     */
    @PostMapping("/google/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody
            UserGoogleRegisterRequest request
    ) {
        var response = userBusiness.googleRegister(request);
        return Api.OK(response);
    }

    /**
     * 로그인
     *
     * @param request - 로그인 정보
     * @return Api<TokenResponse>
     */
    @PostMapping("/google/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody
            UserGoogleLoginRequest request
    ) {
        var response = userBusiness.googleLogin(request);
        return Api.OK(response);
    }
}
