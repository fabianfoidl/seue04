package com.se.ue04.service;

import com.se.ue04.model.Fahrzeug;
import com.se.ue04.repository.FahrzeugRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FahrzeugService {

    private Logger LOG = LoggerFactory.getLogger(FahrzeugService.class);

    private FahrzeugRepository fahrzeugRepository;

    @Autowired
    public void setFahrzeugRepository(FahrzeugRepository fahrzeugRepository) {
        this.fahrzeugRepository = fahrzeugRepository;
    }

    // no exception handling (LOG), because no null respone
    public Fahrzeug getFahrzeug(String id) {
        LOG.info("Getting the fahrzeug with the given id: " + id);
        return fahrzeugRepository.findById(id).orElse(null);
    }

    public List<Fahrzeug> getAllFahrzeuge() {
        return fahrzeugRepository.findAll();
    }

    public Fahrzeug saveFahrzeug(Fahrzeug fahrzeug) {
        Fahrzeug fahrzeugToSave;
        try {
            LOG.info("Saving fahrzeug...");
            fahrzeugToSave = fahrzeugRepository.save(fahrzeug);
            return fahrzeugToSave;
        } catch (Exception e) {
            LOG.error("An error occurred during product saving: " + e.getMessage());
        }
        return new Fahrzeug();

    }

    public Fahrzeug updateFahrzeug(Fahrzeug fahrzeugToUpdate, String id) {
        Fahrzeug foundFahrzeug = fahrzeugRepository.findById(id).orElse(null);
        try {
            foundFahrzeug.setBezeichnung(fahrzeugToUpdate.getBezeichnung());
            foundFahrzeug.setFahrer(fahrzeugToUpdate.getFahrer());
            foundFahrzeug.setMarke(fahrzeugToUpdate.getMarke());
            foundFahrzeug.setSitzplaetze(fahrzeugToUpdate.getSitzplaetze());
            foundFahrzeug.setVerfuegbar(fahrzeugToUpdate.isVerfuegbar());
            return fahrzeugRepository.save(foundFahrzeug);
        } catch (Exception e) {
            LOG.error("An error occurred during update of fahrzeug " + e.getMessage());
        }
        return fahrzeugToUpdate;

    }

    public void deleteFahrzeug(String id) {
        try {
            fahrzeugRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("An error occurred during deleting of fahrzeug " + e.getMessage());
        }

    }
}
