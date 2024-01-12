package org.diary.api.domain.bookmark.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.TokenUser;
import org.diary.api.common.api.Api;
import org.diary.api.domain.bookmark.business.BookmarkBusiness;
import org.diary.api.domain.bookmark.controller.model.BookmarkResponse;
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
     * @param diaryId - 등록할 일기 ID 값
     * @return Api<BookmarkResponse>
     */
    @PostMapping("/")
    public Api<BookmarkResponse> register(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @Valid
            Long diaryId
    ){
        return Api.OK(bookmarkBusiness.register(user,diaryId));
    }

    /**
     * 북마크 정보를 수정
     *
     * @param user - 유저정보
     * @param diaryId - 수정할 일기 ID 값
     * @param position - 북마크 위치 정보
     * @return Api<BookmarkResponse>
     */
    @PatchMapping("/")
    public Api<BookmarkResponse> update(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @Valid
            Long diaryId,
            @Valid
            int position
    ){
        return Api.OK(bookmarkBusiness.update(user,diaryId,position));
    }

    /**
     * 북마크 정보를 삭제
     *
     * @param user - 유저 정보
     * @param diaryId - 삭제할 일기 ID 값
     * @return Api<BookmarkResponse>
     */
    @DeleteMapping("/")
    public Api<BookmarkResponse> delete(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user,
            @Valid
            Long diaryId
    ){
        return Api.OK(bookmarkBusiness.delete(user,diaryId));
    }
}
