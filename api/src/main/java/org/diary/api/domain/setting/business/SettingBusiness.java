package org.diary.api.domain.setting.business;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Business;
import org.diary.api.domain.setting.converter.SettingConverter;
import org.diary.api.domain.setting.service.SettingService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Business
@RequiredArgsConstructor
public class SettingBusiness {

    private final SettingService settingService;
    private final SettingConverter settingConverter;

    /**
     * 설정된 값 조회
     *
     * @param userId - 회원 ID
     * @return SettingResponse
     */
    public Map<String, String> getSettings(Long userId) {
        return Optional
                .of(settingService.getSetting(userId))
                .map(settingConverter::toResponse)
                .orElse(new HashMap<String, String>());
    }
}
