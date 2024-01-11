package org.diary.api.domain.diary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.diary.api.common.DiaryCommon;
import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.common.UserCommon;
import org.diary.api.domain.bookmark.controller.model.BookmarkUpdateRequest;
import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.controller.model.DiaryUpdateRequest;
import org.diary.api.domain.bookmark.fixture.BookmarkFixture;
import org.diary.api.domain.diary.fixture.DiaryFixture;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.db.diary.DiaryRepository;
import org.diary.db.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class DiaryApiControllerTest extends TestMvcConfig {

    private static final String BASE_URL = "/api/diary";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private TokenBusiness tokenBusiness;

    @Test
    void viewDiaryList() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness); // - User 정보

        // when(실행)
        String result = doGetWithToken(BASE_URL, userCommon.token);

        // then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "일기 조회 실패");
    }

    @Test
    void viewDiary() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness); // - User 정보
        DiaryCommon diaryCommon = new DiaryCommon(diaryRepository, userCommon.userId);

        // when(실행)
        String result = doGetWithToken(BASE_URL + "/" +diaryCommon.diaryId, userCommon.token);

        // then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "일기 조회 실패");
    }

    @Test
    void addDiary() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness); // - User 정보
        DiaryRegisterRequest diaryRegisterRequest = DiaryFixture.anDiaryFixture().diaryRegisterRequest(); // - 다이어리 작성 값

        // when(실행)
        String result = doPostWithToken(BASE_URL, diaryRegisterRequest, userCommon.token);

        // then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "일기 추가 실패");
    }

    @Test
    void updateDiary() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);  // - 유저 생성
        DiaryCommon diaryCommon = new DiaryCommon(diaryRepository, userCommon.userId); // - 다이어리 생성
        Long diaryId = diaryCommon.diaryId; // - 다이어리 정보
        DiaryUpdateRequest diaryUpdateRequest = DiaryFixture.anDiaryFixture().diaryUpdateRequest(); // - 수정될 데이터

        // when(실행)
        String diaryUpdateResult = doPatchWithToken(BASE_URL + "/" + diaryId, diaryUpdateRequest, userCommon.token);

        // then(검증)
        JsonNode updateResultJson = objectMapper.readTree(diaryUpdateResult);
        Assert.notNull(updateResultJson.get("body").get("id"), "일기 업데이트 실패");
    }

    @Test
    void deleteDiary() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);  // - 유저 생성
        DiaryCommon diaryCommon = new DiaryCommon(diaryRepository, userCommon.userId); // - 다이어리 생성
        Long diaryId = diaryCommon.diaryId; // - 다이어리 정보

        // when(실행)
        String diaryUpdateResult = doDeleteWithToken(BASE_URL + "/" + diaryId, userCommon.token);

        // then(검증)
        JsonNode updateResultJson = objectMapper.readTree(diaryUpdateResult);
        Assert.notNull(updateResultJson.get("body").get("id"), "일기 삭제 실패");
    }

    @Test
    void getUpdatedQuestion() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);

        // when(실행)
        String result = doGetWithToken(BASE_URL + "/question",userCommon.token);

        // then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "질문 조회 실패");
    }

    @Test
    void updateBookmark() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);  // - 유저 생성
        DiaryCommon diaryCommon = new DiaryCommon(diaryRepository, userCommon.userId); // - 다이어리 생성

        BookmarkUpdateRequest bookmarkUpdateRequest = BookmarkFixture.anBookmarkFixture().bookmarkUpdateRequest(userCommon.userId, diaryCommon.diaryId);

        // when(실행)
        String result = doPutWithToken(BASE_URL + "/bookmark", bookmarkUpdateRequest, userCommon.token);

        // then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "북마크 업데이트 실패");
    }
}
