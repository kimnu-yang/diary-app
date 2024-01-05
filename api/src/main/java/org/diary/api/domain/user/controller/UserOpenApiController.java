package org.diary.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.api.Api;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.domain.user.controller.model.UserLoginRequest;
import org.diary.api.domain.user.controller.model.UserRegisterRequest;
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

    // 사용자 가입 요청
    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody
            UserRegisterRequest request
    ){
        var response = userBusiness.register(request);
        return Api.OK(response);
    }

    // 로그인
    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody
            UserLoginRequest request
    ){
        var response = userBusiness.login(request);
        return Api.OK(response);
    }
}
