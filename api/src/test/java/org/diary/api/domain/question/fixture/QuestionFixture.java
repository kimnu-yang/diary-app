package org.diary.api.domain.question.fixture;


import org.diary.db.question.QuestionEntity;

import java.time.LocalDateTime;

public class QuestionFixture {

    private Long id;
    private String question;
    private LocalDateTime createdAt;

    public static QuestionFixture anUserFixture() { return new QuestionFixture(); }

    public QuestionEntity questionRegister(){
        return QuestionEntity.builder().question("질문1").createdAt(LocalDateTime.now()).build();
    }
}
