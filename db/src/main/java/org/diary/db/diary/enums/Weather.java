package org.diary.db.diary.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Weather {
    SUNNY("맑음"),
    CLOUDY("구름이 많음"),
    OVERCAST("흐림"),
    LITTLE_RAIN("적은 비"),
    RAINY("비 오는 날"),
    SNOW_RAIN("눈비"),
    SNOWY("눈 오는 날");

    private final String description;
}
