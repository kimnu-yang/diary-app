package org.diary.api.domain.bookmark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.diary.api.common.DiaryCommon;
import org.diary.api.common.UserCommon;
import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.bookmark.fixture.BookmarkFixture;
import org.diary.api.domain.bookmark.controller.model.BookmarkUpdateRequest;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.db.diary.DiaryRepository;
import org.diary.db.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class BookmarkApiControllerTest extends TestMvcConfig {

    private static final String BASE_URL = "/api/bookmark";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private TokenBusiness tokenBusiness;

    @Test
    void updateBookmark() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);  // - 유저 생성
        DiaryCommon diaryCommon = new DiaryCommon(diaryRepository, userCommon.userId); // - 다이어리 생성

        BookmarkUpdateRequest bookmarkUpdateRequest = BookmarkFixture.anBookmarkFixture().bookmarkUpdateRequest(userCommon.userId, diaryCommon.diaryId);

        // when(실행)
        String result = doPatchWithToken(BASE_URL, bookmarkUpdateRequest, userCommon.token);

        // then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "북마크 업데이트 실패");
    }
}
