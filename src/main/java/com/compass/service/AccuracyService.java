package com.compass.service;

import com.compass.dto.MatchLevel;
import com.compass.dto.Regist;

public interface AccuracyService {
    MatchLevel getAccuracyLevelByRegists(Regist regist1, Regist regist2);
}
