package org.diary.api.domain.setting.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.api.Api;
import org.diary.api.domain.setting.business.SettingBusiness;
import org.diary.api.domain.setting.controller.model.SettingRegisterRequest;
import org.diary.db.setting.SettingEntity;
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
     * @param request - 토큰에 저장되어 있는 userID 정보
     * @return 해당 유저의 설정값
     * @see org.diary.api.interceptor.AuthorizationInterceptor
     */
    @GetMapping
    public Api<Map<String, String>> getSetting(HttpServletRequest request) {
        System.out.println(request.getAttribute("userId") + "=================");
        var response = settingBusiness.getSettings(Long.parseLong(request.getAttribute("userId").toString()));
        return Api.OK(response);
    }


    /**
     * 설정 저장하기
     *
     * @param request        - userId 가져올 request
     * @param settingRequest - setting 정보
     * @return 저장 후 반환된 SettingEntity
     */
    @PostMapping
    public Api<List<SettingEntity>> setSetting(
            HttpServletRequest request,
            @RequestBody
            List<SettingRegisterRequest> settingRequest
    ) {
        var response = settingBusiness.setSettings(Long.parseLong(request.getAttribute("userId").toString()), settingRequest);
        return Api.OK(response);
    }
}
