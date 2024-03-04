package org.diary.db.diary.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Color {
    RED("화남"),
    ORANGE("신남"),
    YELLOW("행복"),
    GREEN("평온"),
    BLUE("우울"),
    NAVY("남색"),
    VIOLET("혼란"),
    NONE("없음");

    private final String description;
}
