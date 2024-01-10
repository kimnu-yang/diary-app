package org.diary.api.domain.diary.fixture;

import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.controller.model.DiaryUpdateRequest;
import org.diary.db.diary.enums.Emotion;

public class DiaryFixture {

    private final String title = "diary title";
    private final String content = "diary content";
    private final String location = "서울시 강남구";
    private final String weather = "맑음";
    private final Emotion emotion = Emotion.Green;

    public static DiaryFixture anDiaryFixture() {
        return new DiaryFixture();
    }

    public DiaryRegisterRequest diaryRegisterRequest() {
        return new DiaryRegisterRequest(
                title,
                content,
                location,
                weather,
                emotion
        );
    }

    private final String title_update = "updated title";
    private final String content_update = "updated content";
    private final String location_update = "제주도 서귀포시";
    private final String weather_update = "흐림";
    private final Emotion emotion_update = Emotion.Orange;

    public DiaryUpdateRequest diaryUpdateRequest() {
        return new DiaryUpdateRequest(
                title,
                content,
                location,
                weather,
                emotion
        );
    }
}
