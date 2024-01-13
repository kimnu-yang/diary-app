package org.diary.api.common;

import org.diary.db.bookmark.BookmarkEntity;
import org.diary.db.bookmark.BookmarkRepository;

public class BookmarkCommon {

    public Long bookmarkId;

    public BookmarkCommon(BookmarkRepository bookmarkRepository, Long diaryId, Long userId){
        bookmarkId = bookmarkRepository.save(BookmarkEntity.builder()
                .diaryId(diaryId)
                .userId(userId)
                .position(0)
                .build()
        ).getId();
    }
}
