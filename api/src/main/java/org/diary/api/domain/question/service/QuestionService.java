package org.diary.api.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.diary.db.question.QuestionEntity;
import org.diary.db.question.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * 특정일 이후에 생성된 질문을 조회
     *
     * @param date
     * @return List<QuestionEntity>
     */
    public List<QuestionEntity> getQuestionAfterDay(
        LocalDateTime date
    ){
        return questionRepository.findAllByCreatedAtAfter(date);
    }
}
