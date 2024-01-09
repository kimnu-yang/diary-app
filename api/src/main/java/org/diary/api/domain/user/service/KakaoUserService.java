package org.diary.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.error.UserErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.db.token.KakaoTokenEntity;
import org.diary.db.token.KakaoTokenRepository;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.diary.db.user.enums.UserStatus;
import org.diary.db.user.enums.UserType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoUserService {

    private final UserRepository userRepository;
    private final KakaoTokenRepository kakaoTokenRepository;

    /**
     * 카카오 로그인 할 때 가입
     *
     * @param kakoEntity - 카카오 토큰 객체
     * @return UserEntity
     */
    @Transactional
    public UserEntity register(KakaoTokenEntity kakoEntity) {
        return Optional.ofNullable(kakoEntity)
                .map(it -> {
                    kakoEntity.setUser(UserEntity.builder()
                            .type(UserType.Kakao)
                            .status(UserStatus.REGISTERED)
                            .registeredAt(LocalDateTime.now())
                            .build());

                    kakaoTokenRepository.save(kakoEntity);

                    return kakoEntity.getUser();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Register Fail"));
    }

    /**
     * 카카오 로그인 함수
     * TODO 만약 카카오 엑세스 토큰 만료를 체크하려면 로직 변경 필요
     * kakaoTokenRepository로 변경 후 해당 엑세스 토큰 만료 확인
     * 혹은 만료된 토큰으로도 로그인은 가능해야할지 정할 필요 있음.
     * --> 만료됐더라도 이미 인증된 유저이므로 로그인은 가능해야하지 않을까
     * --> 현재는 쿼리에서도 카카오 토큰 만료 체크 X
     *
     * @param userId      - 유저 ID
     * @param accessToken - 카카오 엑세스 토큰
     * @return UserEntity
     */
    public UserEntity login(
            Long userId,
            String accessToken
    ) {
        return getUserWithThrow(userId, accessToken);
    }

    /**
     * 로그인할 때 userId와 카카오 엑세스 토큰으로 유저 찾는 용도
     *
     * @param userId      - 유저 ID
     * @param accessToken - 카카오 엑세스 토큰
     * @return UserEntity
     * @throws ApiException 해당 유저 없는 경우 USER_NOT_FOUND 오류 발생
     */
    public UserEntity getUserWithThrow(
            Long userId,
            String accessToken
    ) {
        return userRepository.findUserInKakao(
                userId,
                accessToken,
                UserStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
