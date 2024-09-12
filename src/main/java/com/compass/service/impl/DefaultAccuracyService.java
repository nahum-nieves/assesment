package com.compass.service.impl;

import com.compass.dto.MatchLevel;
import com.compass.dto.Regist;
import com.compass.service.AccuracyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DefaultAccuracyService implements AccuracyService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    public MatchLevel getAccuracyLevelByRegists(Regist regist1, Regist regist2) {
        var matchLevel = MatchLevel.NONE;
        int accuracyMatch =  addressScore(regist1, regist2)
                + nameScore(regist1, regist2)+
                emailMatches(regist1.getEmail(), regist2.getEmail());
        if (accuracyMatch > 5) {
            matchLevel = MatchLevel.LOW;
        }
        if (accuracyMatch > 8) {
            matchLevel = MatchLevel.MEDIUM;
        }
        if (accuracyMatch > 11) {
            matchLevel = MatchLevel.HIGH;
        }
        return matchLevel;
    }

    //MAX SCORE BY NAME IS 9
    private int nameScore(Regist regist1, Regist regist2) {
        int nameMatch = 0;
        if (StringUtils.equalsIgnoreCase(regist1.getFirstName().trim(), regist2.getFirstName().trim())) {
            nameMatch += regist1.getFirstName().length() > 2 ? 3 : 1;
        }
        if (StringUtils.equalsIgnoreCase(regist1.getLastName().trim(), regist2.getLastName().trim())) {
            if (nameMatch > 0) {
                if (regist1.getLastName().length() > 2) {
                    nameMatch *= 3;
                }
            } else {
                nameMatch += regist1.getLastName().length() > 1 ? 3 : 1;
            }
        }
        return nameMatch;
    }

    //MAX SCORE BY ADDRESS IS 6
    private int addressScore(Regist regist1, Regist regist2) {
        if (StringUtils.isBlank(regist1.getAddress().trim()) || StringUtils.isBlank(regist2.getAddress().trim()))
            return 0;
        int addressMatch = 0;
        if (StringUtils.equalsIgnoreCase(regist1.getZipCode().trim(), regist2.getZipCode().trim())) {
            addressMatch += regist1.getZipCode().length() > 2 ? 2 : 1;
        }
        if (StringUtils.equalsIgnoreCase(regist1.getAddress().trim(), regist2.getAddress().trim())) {
            if (addressMatch > 0) {
                addressMatch *= 3;
            } else {
                addressMatch += 4;
            }
        }
        return addressMatch;
    }

    //EMAIL IS AN IMPORTANT FIELD IN SEVERAL WAYS THAT'S WHY
    //MAX SCORE FOR EMAIL IS 12
    private int emailMatches(String email1, String email2) {
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email1).matches() ||
                !VALID_EMAIL_ADDRESS_REGEX.matcher(email2).matches()) {
            return 0;
        }
        int emailMatch = 0;
        if (StringUtils.equalsIgnoreCase(email1, email2)) {
            emailMatch = 12;
        } else if (email1.contains("@") && email2.contains("@")) {
            String email1WithoutAt = email1.substring(0, email1.indexOf("@"));
            String email2WithoutAt = email2.substring(0, email2.indexOf("@"));
            if (email2WithoutAt.length() > 1 && StringUtils.equalsIgnoreCase(email1WithoutAt, email2WithoutAt)) {
                emailMatch = 5;
            }
        }
        return emailMatch;
    }
}
