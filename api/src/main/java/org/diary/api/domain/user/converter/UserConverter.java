package org.diary.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Converter;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.user.controller.model.UserGoogleRegisterRequest;
import org.diary.api.domain.user.controller.model.UserKakaoRegisterRequest;
import org.diary.api.domain.user.controller.model.UserResponse;
import org.diary.db.user.UserEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toUserEntityFromKakao(UserKakaoRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> UserEntity.builder()
                        .kakaoUserId(request.getKakaoUserId())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public UserEntity toUserEntityFromGoogle(UserGoogleRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> UserEntity.builder()
                        .googleUserId(request.getGoogleUserId())
                        .build())
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
                            .kakaoUserId(userEntity.getKakaoUserId())
                            .googleUserId(userEntity.getGoogleUserId())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
