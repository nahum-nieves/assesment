package com.compass.service.impl;

import com.compass.dto.Match;
import com.compass.dto.MatchLevel;
import com.compass.dto.Regist;
import com.compass.service.AccuracyService;
import com.compass.service.MatchesFinder;
import com.compass.service.RegistLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultMatchesFinder implements MatchesFinder {

    private final RegistLoader registLoader;
    private final AccuracyService accuracyService;

    @Override
    public List<Match> findMatches() {
        var regists = registLoader.loadRegists();
        log.info("Regists found: {}", regists.size());
        var matches = new ArrayList<Match>();
        //Find matches for each regist, last one wouldn't have more regists to compare for.
        for (int i = 0; i < regists.size() - 1; i++) {
            var regist = regists.get(i);
            matches.addAll(findMatchesByRegist(regist, i + 1, regists));
        }
        return matches;
    }

    private List<Match> findMatchesByRegist(Regist source, int startIndex, List<Regist> regists) {
        List<Match> matches = new ArrayList<>();
        for (int i = startIndex; i < regists.size(); i++) {
            var regist = regists.get(i);
            var level = accuracyService.getAccuracyLevelByRegists(source, regist);
            if (level != MatchLevel.NONE) {
                var match = Match.builder()
                        .contactIDSource(source.getContactID())
                        .ContactIDMatch(regist.getContactID())
                        .accuracy(level)
                        .build();
                matches.add(match);
            }
        }
        return matches;
    }
}
