package org.diary.db.diary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.diary.db.BaseEntity;
import org.diary.db.diary.enums.Emotion;
import org.diary.db.user.enums.DiaryStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "diary")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DiaryEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 100, nullable = false)
    private String location;

    @Column(length = 100, nullable = false)
    private String weather;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private DiaryStatus status;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
