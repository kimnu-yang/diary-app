package org.diary.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Business;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.api.domain.token.controller.model.TokenResponse;
import org.diary.api.domain.user.controller.model.KakaoLoginRequest;
import org.diary.api.domain.user.controller.model.KakaoRegisterRequest;
import org.diary.api.domain.user.controller.model.UserResponse;
import org.diary.api.domain.user.converter.UserConverter;
import org.diary.api.domain.user.service.KakaoUserService;
import org.diary.api.domain.user.service.UserService;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 사용자에 대한 가입 처리 로직
     * 발급받은 카카오 엑세스 토큰으로 User 생성 및 토큰 정보 저장
     *
     * @param request - 발급받은 카카오 엑세스 토큰 정보
     * @return UserResponse
     */
    public UserResponse register(KakaoRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(userConverter::toKakaoEntity)
                .map(kakaoUserService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));
    }

    /**
     * 1. userId, 카카오 엑세스 토큰을 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. API token 생성
     * 4. API token response
     *
     * @param request - 카카오 엑세스 토큰 정보
     * @return TokenResponse
     */
    public TokenResponse login(KakaoLoginRequest request) {
        var userEntity = kakaoUserService.login(request.getUserId(), request.getAccessToken());
        return tokenBusiness.issueToken(userEntity);
    }


    // 구글 로그인
    // public UserResponse register(GoogleRegisterRequest request) { }
    // public TokenResponse login(GoogleLoginRequest request) { }

    /**
     * 세션에 있는 userId로 해당 유저 정보 반환
     *
     * @param userId - 유저 ID
     * @return UserResponse
     */
    public UserResponse me(Long userId) {
        var userEntity = userService.getUserWithThrow(userId);
        return userConverter.toResponse(userEntity);
    }
}
