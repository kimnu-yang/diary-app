package org.diary.api.domain.setting.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.UserSession;
import org.diary.api.common.api.Api;
import org.diary.api.domain.setting.business.SettingBusiness;
import org.diary.db.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return Api<Map < String, String>>
     */
    @GetMapping
    public Api<Map<String, String>> register(
            @Parameter(hidden = true)
            @UserSession UserEntity user
    ) {
        var response = settingBusiness.getSettings(user.getId());
        return Api.OK(response);
    }
}
