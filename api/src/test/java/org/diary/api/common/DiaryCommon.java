package org.diary.api.common;

import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.fixture.DiaryFixture;
import org.diary.db.diary.DiaryEntity;
import org.diary.db.diary.DiaryRepository;
import org.diary.db.user.enums.DiaryStatus;

public class DiaryCommon {

    public Long diaryId;

    DiaryRegisterRequest diaryRegisterRequest = DiaryFixture.anDiaryFixture().diaryRegisterRequest();

    public DiaryCommon(DiaryRepository diaryRepository, Long userId) {
        diaryId = diaryRepository.save(DiaryEntity.builder()
                .userId(userId)
                .title(diaryRegisterRequest.getTitle())
                .content(diaryRegisterRequest.getContent())
//                .location(diaryRegisterRequest.getLocation())
//                .weather(diaryRegisterRequest.getWeather())
//                .emotion(diaryRegisterRequest.getEmotion())
//                .status(DiaryStatus.Registered)
                .build()
        ).getId();
    }
}
