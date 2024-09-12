package com.compass.service.impl;

import com.compass.dto.Regist;
import com.compass.service.RegistLoader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Service
public class DefaultRegistLoader implements RegistLoader {
    @Override
    public List<Regist> loadRegists() {
        log.debug("Loading regists from file");
        var resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("input.csv");
        return new CsvToBeanBuilder(new InputStreamReader(resourceAsStream))
                .withType(Regist.class)
                .withSkipLines(1) // Used to skip 1st line. Because columns headers are in 1st line
                .build()
                .parse();
    }
}
