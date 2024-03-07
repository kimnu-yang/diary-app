package org.diary.api.domain.setting.business;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Business;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.setting.controller.model.CheckSyncData;
import org.diary.api.domain.setting.controller.model.DiaryWithColorAndTag;
import org.diary.api.domain.setting.controller.model.SettingRegisterRequest;
import org.diary.api.domain.setting.converter.SettingConverter;
import org.diary.api.domain.setting.service.SettingService;
import org.diary.db.setting.SettingEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
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
                .orElse(new HashMap<>());
    }

    /**
     * 한번에 모든 설정 저장
     *
     * @param userId         - 저장할 회원 번호
     * @param settingRequest - 저장할 설정 리스트
     * @return SettingResponse
     */
    @Transactional
    public List<SettingEntity> setSettings(Long userId, List<SettingRegisterRequest> settingRequest) {
        return Optional
                .of(settingRequest)
                .map((settingReq) -> settingConverter.toSettingEntitryList(userId, settingReq))
                .map(settingService::setSettings)
                .orElseThrow(() -> new ApiException(ErrorCode.SERVER_ERROR, "설정 저장 오류"));
    }

    public Map<String, Object> checkSyncData(Long userId, CheckSyncData request) {
        return Optional
                .of(request)
                .map((req) -> settingService.checkSyncDate(userId, request.getLastSyncTime(), req))
                .orElseThrow(() -> new ApiException(ErrorCode.SERVER_ERROR, "동기화 오류"));
    }

    public void uploadDiaryData(Long userId, List<DiaryWithColorAndTag> request) {
        settingService.saveDiaryWithColorAndTag(userId, request);
    }
}
