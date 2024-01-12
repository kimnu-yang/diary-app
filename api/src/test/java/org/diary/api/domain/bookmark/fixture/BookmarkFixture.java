package org.diary.api.domain.bookmark.fixture;

import org.diary.api.domain.bookmark.controller.model.BookmarkUpdateRequest;

public class BookmarkFixture {

    public static BookmarkFixture anBookmarkFixture() { return new BookmarkFixture(); }

    public BookmarkUpdateRequest bookmarkUpdateRequest(Long userId,Long diaryId) {

        int position = 0;
        return new BookmarkUpdateRequest(
                diaryId,
                userId,
                position
        );
    }
}
