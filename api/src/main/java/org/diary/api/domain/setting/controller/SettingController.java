package org.diary.api.domain.setting.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.UserSession;
import org.diary.api.common.api.Api;
import org.diary.api.domain.setting.business.SettingBusiness;
import org.diary.api.domain.setting.controller.model.SettingRegisterRequest;
import org.diary.db.setting.SettingEntity;
import org.diary.db.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/setting")
public class SettingController {

    @Autowired
    private SettingBusiness settingBusiness;

    /**
     * 설정 가져오기
     *
     * @param user - 세션 user 정보
     * @return 해당 유저의 설정값
     */
    @GetMapping
    public Api<Map<String, String>> getSetting(
            @Parameter(hidden = true)
            @UserSession UserEntity user
    ) {
        var response = settingBusiness.getSettings(user.getId());
        return Api.OK(response);
    }


    /**
     * 설정 저장하기
     *
     * @param request - 저장할 설정 리스트
     * @return 저장 후 반환된 SettingEntity
     */
    @PostMapping
    public Api<List<SettingEntity>> setSetting(
            @Valid
            @RequestBody
            SettingRegisterRequest request
    ) {
        var response = settingBusiness.setSettings(request);
        return Api.OK(response);
    }
}
