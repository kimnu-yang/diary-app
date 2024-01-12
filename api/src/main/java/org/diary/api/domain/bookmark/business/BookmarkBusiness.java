package org.diary.api.domain.bookmark.business;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Business;
import org.diary.api.domain.bookmark.controller.model.BookmarkResponse;
import org.diary.api.domain.bookmark.converter.BookmarkConverter;
import org.diary.api.domain.bookmark.service.BookmarkService;
import org.diary.db.bookmark.BookmarkEntity;
import org.diary.db.user.UserEntity;

@Business
@RequiredArgsConstructor
public class BookmarkBusiness {

    private final BookmarkService bookmarkService;
    private final BookmarkConverter bookmarkConverter;

    /**
     * 북마크 정보를 추가
     *
     * @param user - 유저 정보
     * @param diaryId - 추가할 일기 ID 값
     * @return BookmarkResponse
     */
    public BookmarkResponse register(
            UserEntity user,
            Long diaryId
    ) {
        var entity = new BookmarkEntity(diaryId, user.getId(), 0);
        var newEntity = bookmarkService.register(entity);
        return bookmarkConverter.toResponse(newEntity);
    }

    /**
     * 북마크 정보를 수정
     *
     * @param user - 유저 정보
     * @param diaryId - 수정할 일기 ID 값
     * @param position - 수정할 북마크 위치정보
     * @return BookmarkResponse
     */
    public BookmarkResponse update(
            UserEntity user,
            Long diaryId,
            int position
    ) {
        var entity = bookmarkService.getBookmark(user, diaryId);
        entity.setPosition(position);
        var updatedEntity = bookmarkService.update(entity);
        return bookmarkConverter.toResponse(updatedEntity);
    }

    /**
     * 북마크 정보를 삭제
     *
     * @param user - 유저 정보
     * @param diaryId - 삭제할 일기 ID 값
     * @return BookmarkResponse
     */
    public BookmarkResponse delete(
            UserEntity user,
            Long diaryId
    ) {
        var entity = bookmarkService.getBookmark(user, diaryId);
        var deletedEntity = bookmarkService.delete(entity);
        return bookmarkConverter.toResponse(deletedEntity);
    }
}
