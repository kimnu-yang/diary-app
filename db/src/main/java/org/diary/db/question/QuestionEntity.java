package org.diary.db.question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.diary.db.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "question")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionEntity extends BaseEntity {

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
