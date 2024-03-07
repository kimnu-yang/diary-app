package org.diary.api.domain.setting.converter;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Converter;
import org.diary.api.domain.setting.controller.model.SettingRegisterRequest;
import org.diary.db.setting.SettingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Converter
@RequiredArgsConstructor
public class SettingConverter {

    public List<SettingEntity> toSettingEntitryList(Long userId, List<SettingRegisterRequest> request) {
        List<SettingEntity> entityList = new ArrayList<>();
        // to Entity List
        for (SettingRegisterRequest settingReq : request) {
            entityList.add(SettingEntity.builder()
                    .userId(userId)
                    .name(settingReq.getName())
                    .value(settingReq.getValue())
                    .build()
            );
        }

        return entityList;
    }

    public Map<String, String> toResponse(List<SettingEntity> settings) {
        //to response
        Map<String, String> response = new HashMap<>();

        for (SettingEntity setting : settings) {
            response.put(setting.getName(), setting.getValue());
        }

        return response;
    }
}
