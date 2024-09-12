package com.compass.service.impl;


import com.compass.dto.MatchLevel;
import com.compass.dto.Regist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultAccuracyServiceTest {

    @InjectMocks
    DefaultAccuracyService defaultAccuracyService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAccuracyLevelByRegistsHighAccuracy() {
        List<Regist> highRegists = getHighRegists();
        var matchLevel = defaultAccuracyService.getAccuracyLevelByRegists(highRegists.get(0), highRegists.get(1));
        assertEquals(MatchLevel.HIGH, matchLevel);
    }

    @Test
    void getAccuracyLevelByRegistsMediumAccuracy() {
        List<Regist> highRegists = getMediumRegists();
        var matchLevel = defaultAccuracyService.getAccuracyLevelByRegists(highRegists.get(0), highRegists.get(1));
        assertEquals(MatchLevel.MEDIUM, matchLevel);
    }

    @Test
    void getAccuracyLevelByRegistsLowAccuracy() {
        List<Regist> highRegists = getLowRegists();
        var matchLevel = defaultAccuracyService.getAccuracyLevelByRegists(highRegists.get(0), highRegists.get(1));
        assertEquals(MatchLevel.LOW, matchLevel);
    }


    List<Regist> getHighRegists() {
        var regists = new ArrayList<Regist>();
        var regist = new Regist();
        regist.setContactID("1001");
        regist.setFirstName("Chase");
        regist.setLastName("Randall");
        regist.setEmail("a@yahoo.org");
        regist.setZipCode("56674");
        regist.setAddress("1416 Tortor Av.");
        regists.add(regist);
        regist = new Regist();
        regist.setContactID("1002");
        regist.setFirstName("Chase");
        regist.setLastName("Randall");
        regist.setEmail("a@yahoo.org");
        regist.setZipCode("56674");
        regist.setAddress("");
        regists.add(regist);
        return regists;
    }

    List<Regist> getMediumRegists() {
        var regists = new ArrayList<Regist>();
        var regist = new Regist();
        regist.setContactID("1001");
        regist.setFirstName("NoMatch");
        regist.setLastName("NoMath");
        regist.setEmail("a@yahoo.org");
        regist.setZipCode("56674");
        regist.setAddress("1416 Tortor Av.");
        regists.add(regist);
        regist = new Regist();
        regist.setContactID("1002");
        regist.setFirstName("Chase");
        regist.setLastName("Randall");
        regist.setEmail("a@yahoo.org");
        regist.setZipCode("56674");
        regist.setAddress("");
        regists.add(regist);
        return regists;
    }

    List<Regist> getLowRegists() {
        var regists = new ArrayList<Regist>();
        var regist = new Regist();
        regist.setContactID("1001");
        regist.setFirstName("NoMatch");
        regist.setLastName("NoMath");
        regist.setEmail("a@yahoo.org");
        regist.setZipCode("56674");
        regist.setAddress("1416 Tortor Av.");
        regists.add(regist);
        regist = new Regist();
        regist.setContactID("1002");
        regist.setFirstName("Chase");
        regist.setLastName("Randall");
        regist.setEmail("a@yahoo.com");
        regist.setZipCode("56674");
        regist.setAddress("");
        regists.add(regist);
        return regists;
    }

}