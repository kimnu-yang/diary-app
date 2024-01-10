package org.diary.api.domain.setting.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.diary.db.setting.SettingEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingRegisterRequest {

    @Data
    @Builder
    public static class SettingObj {
        private String name;
        private String value;
    }

    @NotBlank
    private Long userId;

    @NotEmpty
    private List<SettingObj> settings;
}
