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

/**
 * User 도메인 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
