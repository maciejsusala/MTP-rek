package com.MTP.rek.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Setter
public class Star {
    private String name;
    private long distance;

    public Star(String name, long distance) {
        this.name = name;
        this.distance = distance;
    }
}
