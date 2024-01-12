package org.diary.api.domain.bookmark.service;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.db.bookmark.BookmarkEntity;
import org.diary.db.bookmark.BookmarkRepository;
import org.diary.db.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    /**
     * 북마크 정보 조회
     *
     * @param user - 유저 정보
     * @param diaryId - 조회할 일기 ID
     * @return BookmarkEntity
     */
    public BookmarkEntity getBookmark(UserEntity user, Long diaryId) {
        return bookmarkRepository.findFirstByUserIdAndDiaryId(user.getId(),diaryId);
    }

    /**
     * 북마크 정보 추가
     *
     * @param bookmarkEntity - 추가할 북마크 데이터
     * @return bookmarkEntity - 추가된 북마크 데이터
     */
    public BookmarkEntity register(BookmarkEntity bookmarkEntity) {
        return Optional.ofNullable(bookmarkEntity)
                .map(it -> {
                    return bookmarkRepository.save(bookmarkEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 북마크 정보 수정
     *
     * @param bookmarkEntity - 수정할 북마크 데이터
     * @return bookmarkEntity - 수정된 북마크 데이터
     */
    public BookmarkEntity update(BookmarkEntity bookmarkEntity){
        return Optional.ofNullable(bookmarkEntity)
                .map(it -> {
                    return bookmarkRepository.save(bookmarkEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 북마크 정보 삭제
     *
     * @param bookmarkEntity - 삭제할 북마크 데이터
     * @return bookmarkEntity - 삭제된 북마크 데이터
     */
    public BookmarkEntity delete(BookmarkEntity bookmarkEntity){
        return Optional.ofNullable(bookmarkEntity)
                .map(it -> {
                    bookmarkRepository.delete(bookmarkEntity);
                    return bookmarkEntity;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
