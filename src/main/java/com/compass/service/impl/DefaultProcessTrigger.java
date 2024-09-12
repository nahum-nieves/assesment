package com.compass.service.impl;

import com.compass.dto.Match;
import com.compass.service.MatchesFinder;
import com.compass.service.ProcessTrigger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultProcessTrigger implements ProcessTrigger {

    private final MatchesFinder matchesFinder;
    @Override
    public void startProcess() {
        var matches = matchesFinder.findMatches();
        log.info("Found {} matches", matches.size());
        matches.sort(Comparator.comparing(Match::getAccuracy));
        log.debug("Matches sorted by accuracy");
        System.out.format("%-25s %-25s %-25s","Contact Id Source", "Contact Id Match", "Accuracy");
        matches.forEach(match -> {
            System.out.println();
            System.out.format("%-25s %-25s %-25s", match.getContactIDSource(), match.getContactIDMatch(), match.getAccuracy());
        });
    }
}
