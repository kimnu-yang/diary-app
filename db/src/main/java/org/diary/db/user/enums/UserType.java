package org.diary.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {
    Kakao("카카오"),
    Google("구글"),
    ;

    private final String description;
}
