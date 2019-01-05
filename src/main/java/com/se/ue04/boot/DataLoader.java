package com.se.ue04.boot;

import com.se.ue04.model.Fahrzeug;
import com.se.ue04.repository.FahrzeugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// class for importing data into database on startup
@Component
public class DataLoader implements CommandLineRunner {

    private FahrzeugRepository fahrzeugRepository;

    @Autowired
    public void setFahrzeugRepository(FahrzeugRepository fahrzeugRepository) {
        this.fahrzeugRepository = fahrzeugRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Fahrzeug fahrzeug1 = new Fahrzeug();
        fahrzeug1.setBezeichnung("Testfahrzeug");
        fahrzeug1.setFahrer("Max Musterfahrer");
        fahrzeug1.setMarke("Mercedes");
        fahrzeug1.setSitzplaetze(5);
        fahrzeug1.setVerfuegbar(true);

        fahrzeugRepository.save(fahrzeug1);

        Fahrzeug fahrzeug2 = new Fahrzeug();
        fahrzeug2.setBezeichnung("Testfahrzeug2");
        fahrzeug2.setFahrer("Anna Musterfahrerin");
        fahrzeug2.setMarke("Fiat");
        fahrzeug2.setSitzplaetze(5);
        fahrzeug2.setVerfuegbar(true);

        fahrzeugRepository.save(fahrzeug2);

        Fahrzeug fahrzeug3 = new Fahrzeug();
        fahrzeug3.setBezeichnung("Testfahrzeug3");
        fahrzeug3.setFahrer("Anna Musterfahrerin");
        fahrzeug3.setMarke("BMW");
        fahrzeug3.setSitzplaetze(5);
        fahrzeug3.setVerfuegbar(true);

        fahrzeugRepository.save(fahrzeug3);

    }
}
