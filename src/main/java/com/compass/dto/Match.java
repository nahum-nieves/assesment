package com.compass.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Match {
    private MatchLevel accuracy;
    private String contactIDSource;
    private String ContactIDMatch;
}
