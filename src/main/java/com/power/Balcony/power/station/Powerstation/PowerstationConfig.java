package com.power.Balcony.power.station.Powerstation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class PowerstationConfig {

@Bean
    CommandLineRunner commandLineRunner(PowerstationRepository repo) {
        return args -> {
            Powerstation B1 = new Powerstation(1L,
                    "Solarcompany1",
                    800,
                    600,
                    LocalDate.of(2022, DECEMBER, 1));

            Powerstation B2 = new Powerstation(2L,
                    "Solarcompany2",
                    800,
                    600,
                    LocalDate.of(1999, DECEMBER, 1));

            Powerstation B3 = new Powerstation(3L,
                    "Solarcompany3",
                    1200,
                    800,
                    LocalDate.of(2023, AUGUST, 1));

            Powerstation B4 = new Powerstation(4L,
                    "Solarcompany4",
                    600,
                    300,
                    LocalDate.of(2021, MARCH, 7));

            repo.saveAll(List.of(B1, B2, B3, B4));
        };
    }
}

