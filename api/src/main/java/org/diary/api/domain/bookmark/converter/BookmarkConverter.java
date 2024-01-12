package org.diary.api.domain.bookmark.converter;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Converter;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.bookmark.controller.model.BookmarkResponse;
import org.diary.db.bookmark.BookmarkEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class BookmarkConverter {

    public BookmarkResponse toResponse(BookmarkEntity bookmarkEntity) {
        return Optional.ofNullable(bookmarkEntity)
                .map(it -> {
                    return BookmarkResponse.builder()
                            .id(bookmarkEntity.getId())
                            .diaryId(bookmarkEntity.getDiaryId())
                            .userId(bookmarkEntity.getUserId())
                            .position(bookmarkEntity.getPosition())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
