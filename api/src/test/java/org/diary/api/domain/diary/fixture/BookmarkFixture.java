package org.diary.api.domain.diary.fixture;

import org.diary.api.domain.diary.controller.model.BookmarkUpdateRequest;

public class BookmarkFixture {

    public static BookmarkFixture anUserFixture() { return new BookmarkFixture(); }

    public BookmarkUpdateRequest bookmarkUpdateRequest(Long userId,Long diaryId) {

        int position = 0;
        return new BookmarkUpdateRequest(
                diaryId,
                userId,
                position
        );
    }
}
