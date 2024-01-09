package org.diary.api.common;

import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.controller.model.DiaryUpdateRequest;
import org.diary.api.domain.diary.fixture.DiaryFixture;
import org.diary.db.diary.DiaryEntity;
import org.diary.db.diary.DiaryRepository;
import org.diary.db.user.enums.DiaryStatus;

public class DiaryCommon {

    public Long diary_id;

    DiaryRegisterRequest diaryRegisterRequest = DiaryFixture.anUserFixture().diaryRegisterRequest();

    public DiaryCommon(DiaryRepository diaryRepository, Long user_id){
        diary_id = diaryRepository.save(DiaryEntity.builder()
                .userId(user_id)
                .title(diaryRegisterRequest.getTitle())
                .content(diaryRegisterRequest.getContent())
                .location(diaryRegisterRequest.getLocation())
                .weather(diaryRegisterRequest.getWeather())
                .emotion(diaryRegisterRequest.getEmotion())
                .status(DiaryStatus.Registered)
                .build()
        ).getId();
    }
}
