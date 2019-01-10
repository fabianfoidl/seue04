package com.se.ue04.repository;

import com.se.ue04.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {

    Route findByRouteId(String routeId);
    Route findByDepart(String depart);

}
