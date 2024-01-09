package org.diary.db.diary.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emotion {
    Red("화남"),
    Orange("신남"),
    Yellow("행복"),
    Green("평온"),
    Blue("우울"),
    Indigo("남색"),
    Purple("혼란")
    ;

    private final String description;
}
