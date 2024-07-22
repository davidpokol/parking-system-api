package com.davidpokolol.parkingsystemapi.populator;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Profile("!prod")
@RequiredArgsConstructor
@Service
public class DataBasePopulatorService {

    private final List<DatabasePopulator> databasePopulators;

    @PostConstruct
    public void populateDatabase() {
        log.info("Populates data base...");
        databasePopulators.forEach(DatabasePopulator::populate);
        log.info("Database populate process is finished.");
    }
}
