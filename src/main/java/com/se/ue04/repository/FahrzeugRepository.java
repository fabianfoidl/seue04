package com.se.ue04.repository;

import com.se.ue04.model.Fahrzeug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FahrzeugRepository extends JpaRepository<Fahrzeug, String> {

    // find By bleibt immer gleich; Marke MUSS genau so heissen wie das Feld
    Fahrzeug findByMarke(String marke);
    List<Fahrzeug> findByMarkeAndBezeichnung(String marke, String bezeichnung);
    List<Fahrzeug> findByMarkeAndBezeichnungIn(String marke, List<String> bezeichnung);
}
