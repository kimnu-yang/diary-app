package org.diary.api.domain.diary.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.TokenUser;
import org.diary.api.common.api.Api;
import org.diary.api.domain.diary.business.DiaryBusiness;
import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.controller.model.DiaryResponse;
import org.diary.api.domain.diary.controller.model.DiaryUpdateRequest;
import org.diary.db.user.UserEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diary")
@RequiredArgsConstructor
public class DiaryApiController {

    private final DiaryBusiness diaryBusiness;

    /**
     * 세션에 있는 유저의 모든 일기를 조회한다
     *
     * @param user - 유저 세션 정보
     * @return Api<List<DiaryResponse>> - 일기 리스트
     */
    @GetMapping("")
    public Api<List<DiaryResponse>> diaryList(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user
    ){
        return Api.OK(diaryBusiness.diaryList(user));
    }

    /**
     * 세션에 있는 유저의 특정 일기 내용을 조회한다
     *
     * @param user
     * @param diaryId
     * @return Api<DiaryResponse>
     */
    @GetMapping("/{diaryId}")
    public Api<DiaryResponse> diaryDetail(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @PathVariable(value = "diaryId")
            Long diaryId
    ){
        return Api.OK(diaryBusiness.diaryDetail(user,diaryId));
    }

    /**
     * 세션에 있는 유저의 ID로 일기를 추가한다
     * @param user
     * @param request
     * @return Api<DiaryResponse>
     */
    @PostMapping("")
    public Api<DiaryResponse> register(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @Valid
            @RequestBody
            DiaryRegisterRequest request
    ){
        return Api.OK(diaryBusiness.register(user, request));
    }

    /**
     * 세션에 있는 유저의 일기를 수정한다.
     * @param user - 세션 유저 정보
     * @param diaryId - 수정할 일기 ID
     * @param request - 수정할 일기 데이터
     * @return
     */
    @PutMapping("/{diaryId}")
    public Api<DiaryResponse> update(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @PathVariable(value = "diaryId")
            Long diaryId,
            @Valid
            @RequestBody
            DiaryUpdateRequest request
    ){
        return Api.OK(diaryBusiness.update(user,diaryId,request));
    }

    /**
     * 세션에 있는 유저의 일기를 삭제한다
     * @param user - 세션 유저 정보
     * @param diaryId - 삭제할 일기 ID
     * @return
     */
    @DeleteMapping("/{diaryId}")
    public Api<DiaryResponse> delete(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @PathVariable(value = "diaryId")
            Long diaryId
    ){
        return Api.OK(diaryBusiness.delete(user,diaryId));
    }
}