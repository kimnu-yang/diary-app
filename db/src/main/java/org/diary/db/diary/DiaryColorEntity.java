package org.diary.db.diary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.diary.db.BaseEntity;
import org.diary.db.diary.enums.Color;

@Entity
@Table(name = "diary_color")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DiaryColorEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long diaryId;

    private Integer position;

    @Enumerated(EnumType.STRING)
    private Color color;

    private Integer ratio;
}