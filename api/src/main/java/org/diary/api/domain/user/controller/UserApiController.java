package org.diary.api.domain.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.TokenUser;
import org.diary.api.common.api.Api;
import org.diary.api.domain.user.business.UserBusiness;
import org.diary.api.domain.user.controller.model.UserResponse;
import org.diary.db.user.UserEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    /**
     * 로그인 후 세션에 있는 정보를 통한 사용자 인증
     *
     * @param user - 세션 회원 정보
     * @return Api<UserResponse>
     */
    @GetMapping("/me")
    public Api<UserResponse> me(
            @Parameter(hidden = true)
            @TokenUser UserEntity user
    ) {

        var response = userBusiness.me(user.getId());
        return Api.OK(response);
    }

    @DeleteMapping
    public Api unregistUser(
            @Parameter(hidden = true)
            @TokenUser UserEntity user
    ) {
        userBusiness.unregistUser(user.getId());
        return Api.OK(null);
    }
}
