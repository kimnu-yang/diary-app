package org.diary.api.domain.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.api.kakao.KakaoApi;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.error.UserErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.diary.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * User 도메인 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 카카오 회원가입 처리
     *
     * @param userEntity - 카카오 ID만 있는 객체
     * @return UserEntity
     */
    public UserEntity register(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Kakao User Register Null"));
    }

    /**
     * 카카오 로그인 함수
     * 해당 토큰으로 가입한 유저가 있다면 login 처리
     * 해당 토큰으로 가입한 유저가 없다면 register 처리
     *
     * @param kakaoToken - 카카오 토큰
     * @return UserEntity
     */
    public UserEntity kakaoLogin(String kakaoToken) {
        Long kakaoUserId = KakaoApi.getUserId(kakaoToken);

        return userRepository.findFirstByKakaoUserIdAndStatusOrderByIdDesc(
                kakaoUserId,
                UserStatus.REGISTERED
        ).orElse(register(UserEntity.builder().kakaoUserId(kakaoUserId).build()));
    }

    /**
     * 구글 로그인 함수
     *
     * @param id           - 유저 ID
     * @param googleUserId - 구글 ID
     * @return UserEntity
     */
    public UserEntity googleLogin(
            Long id,
            Long googleUserId
    ) {
        return userRepository.findFirstByIdAndGoogleUserIdAndStatusOrderByIdDesc(
                id,
                googleUserId,
                UserStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    /**
     * Session에 저장된 userId로 사용자 찾는 용도
     *
     * @param userId - 유저 ID
     * @return UserEntity
     * @throws ApiException 해당 유저 없는 경우 USER_NOT_FOUND 오류 발생
     */
    public UserEntity getUserWithThrow(Long userId) {
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(
                userId,
                UserStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
