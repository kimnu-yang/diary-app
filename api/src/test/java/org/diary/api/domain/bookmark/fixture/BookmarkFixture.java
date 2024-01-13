package org.diary.api.domain.bookmark.fixture;

import org.diary.api.domain.bookmark.controller.model.BookmarkRegisterRequest;
import org.diary.api.domain.bookmark.controller.model.BookmarkUpdateRequest;

public class BookmarkFixture {

    private int position = 0;

    public static BookmarkFixture anBookmarkFixture() { return new BookmarkFixture(); }

    public BookmarkRegisterRequest bookmarkRegisterRequest(Long diaryId, Long userId) {

        return new BookmarkRegisterRequest(
                diaryId,
                userId,
                position
        );
    }

    public BookmarkUpdateRequest bookmarkUpdateRequest() {

        position = 1;
        return new BookmarkUpdateRequest(
                position
        );
    }
}
