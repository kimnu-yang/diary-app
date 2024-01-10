package org.diary.api.domain.setting.service;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.db.setting.SettingEntity;
import org.diary.db.setting.SettingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Setting 도메인 로직을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;

    /**
     * 설정 등록 처리
     * 해당 유저에게 동일한 설정이 있는 경우 업데이트 처리
     *
     * @param settings - 설정 Entity 리스트
     */
    @Transactional
    public List<SettingEntity> setSettings(List<SettingEntity> settings) {
        return Optional.ofNullable(settings)
                .map(settingEntities -> {
                    // 기존 설정값 체크
                    for (SettingEntity entity : settingEntities) {
                        Optional<SettingEntity> check = settingRepository.findFirstByUserIdAndName(entity.getUserId(), entity.getName());
                        check.ifPresent(e -> entity.setId(e.getId()));

                        settingRepository.save(entity);
                    }

                    return settingEntities;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "setting data null"));
    }

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
