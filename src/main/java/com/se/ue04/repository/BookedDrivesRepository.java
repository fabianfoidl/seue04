package com.se.ue04.repository;

import com.se.ue04.model.BookedDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookedDrivesRepository extends JpaRepository<BookedDrive, String> {

    BookedDrive getById(String id);
    List<BookedDrive> getByRoute (String route);
    List<BookedDrive> getByTimeAndRoute(Date time, String route);
    List<BookedDrive> getByRouteAndTimeBetween(String route, Date startTime, Date endTime);
}
