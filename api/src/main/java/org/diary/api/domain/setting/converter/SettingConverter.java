package org.diary.api.domain.setting.converter;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Converter;
import org.diary.db.setting.SettingEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Converter
@RequiredArgsConstructor
public class SettingConverter {

    public Map<String, String> toResponse(List<SettingEntity> settings) {
        //to response
        Map<String, String> response = new HashMap<>();

        for (SettingEntity setting : settings) {
            response.put(setting.getName(), setting.getValue());
        }

        return response;
    }

}
