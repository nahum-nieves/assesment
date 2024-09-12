package com.compass;

import com.compass.service.ProcessTrigger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class Compass implements CommandLineRunner {

    @Autowired
    ProcessTrigger processTrigger;

    public static void main(String[] args) {
        SpringApplication.run(Compass.class, args);
    }

    @Override
    public void run(String... args) {
        processTrigger.startProcess();
    }
}