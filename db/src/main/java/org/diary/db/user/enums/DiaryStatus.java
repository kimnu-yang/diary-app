package org.diary.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiaryStatus {

    Temp("임시"),
    Registered("등록"),
    Deleted("삭제"),
    ;

    private final String description;
}
