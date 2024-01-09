package org.diary.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Converter;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.user.controller.model.KakaoRegisterRequest;
import org.diary.api.domain.user.controller.model.UserResponse;
import org.diary.db.token.KakaoTokenEntity;
import org.diary.db.user.UserEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public KakaoTokenEntity toKakaoEntity(KakaoRegisterRequest request) {

        return Optional.ofNullable(request)
                .map(it -> {
                    return KakaoTokenEntity.builder()
                            .accessToken(request.getAccessToken())
                            .accessTokenExpireAt(request.getAccessTokenExpireAt())
                            .idToken(request.getIdToken())
                            .refreshToken(request.getRefreshToken())
                            .scopes(request.getScopes())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    // 구글 컨버터
    // public GoogleTokenEntity toGoogleEntity(GoogleRegisterRequest request) {}

    public UserResponse toResponse(UserEntity userEntity) {

        return Optional.ofNullable(userEntity)
                .map(it -> {
                    //to response
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .status(userEntity.getStatus())
                            .type(userEntity.getType())
//                            .registeredAt(userEntity.getRegisteredAt())
//                            .unregisteredAt(userEntity.getUnregisteredAt())
//                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
