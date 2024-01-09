package org.diary.db.bookmark;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.diary.db.BaseEntity;

@Entity
@Table(name = "bookmark")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookmarkEntity extends BaseEntity {

    @Column(nullable = false)
    private Long diaryId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int position;
}
