package org.diary.api.common;

import org.diary.api.domain.question.fixture.QuestionFixture;
import org.diary.db.question.QuestionEntity;
import org.diary.db.question.QuestionRepository;

public class QuestionCommon {

    public QuestionEntity question = QuestionFixture.anUserFixture().questionRegister();

    public QuestionCommon(QuestionRepository questionRepository) {
        questionRepository.save(question);
    }
}
