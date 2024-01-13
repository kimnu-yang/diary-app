package org.diary.api.domain.bookmark.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.TokenUser;
import org.diary.api.common.api.Api;
import org.diary.api.domain.bookmark.business.BookmarkBusiness;
import org.diary.api.domain.bookmark.controller.model.BookmarkRegisterRequest;
import org.diary.api.domain.bookmark.controller.model.BookmarkResponse;
import org.diary.api.domain.bookmark.controller.model.BookmarkUpdateRequest;
import org.diary.db.user.UserEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
public class BookmarkApiController {

    private final BookmarkBusiness bookmarkBusiness;

    /**
     * 북마크 정보를 추가
     *
     * @param user - 유저 정보
     * @param request - 등록할 북마크 정보
     * @return Api<BookmarkResponse>
     */
    @PostMapping("")
    public Api<BookmarkResponse> register(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @Valid
            @RequestBody
            BookmarkRegisterRequest request
    ){
        return Api.OK(bookmarkBusiness.register(user, request.getDiaryId()));
    }

    /**
     * 북마크 정보를 수정
     *
     * @param user - 유저정보
     * @param diaryId - 수정할 일기 ID 값
     * @param request - 북마크 위치 정보
     * @return Api<BookmarkResponse>
     */
    @PutMapping("/{diaryId}")
    public Api<BookmarkResponse> update(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @PathVariable(value = "diaryId")
            Long diaryId,
            @Valid
            @RequestBody
            BookmarkUpdateRequest request
    ){
        return Api.OK(bookmarkBusiness.update(user,diaryId,request.getPosition()));
    }

    /**
     * 북마크 정보를 삭제
     *
     * @param user - 유저 정보
     * @param diaryId - 삭제할 일기 ID 값
     * @return Api<BookmarkResponse>
     */
    @DeleteMapping("/{diaryId}")
    public Api<BookmarkResponse> delete(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @PathVariable(value = "diaryId")
            Long diaryId
    ){
        return Api.OK(bookmarkBusiness.delete(user,diaryId));
    }
}
