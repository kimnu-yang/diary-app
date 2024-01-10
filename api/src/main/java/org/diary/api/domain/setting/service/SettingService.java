package org.diary.api.domain.setting.service;

import lombok.RequiredArgsConstructor;
import org.diary.db.setting.SettingEntity;
import org.diary.db.setting.SettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Setting 도메인 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;

    /**
     * 설정 등록 처리
     *
     * @param userEntity - 카카오 ID만 있는 객체
     * @return UserEntity
     */
//    public UserEntity register(UserEntity userEntity) {
//        return Optional.ofNullable(userEntity)
//                .map(it -> {
//                    userEntity.setStatus(UserStatus.REGISTERED);
//                    userEntity.setRegisteredAt(LocalDateTime.now());
//                    return userRepository.save(userEntity);
//                })
//                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Kakao User Register Null"));
//    }

    /**
     * 설정 조회
     *
     * @param userId - 유저 ID
     * @return UserEntity
     */
    public List<SettingEntity> getSetting(Long userId) {
        return settingRepository.findByUserId(userId);
    }
}
