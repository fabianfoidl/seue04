package com.se.ue04.service;

import com.se.ue04.model.BookedDrive;
import com.se.ue04.model.Vehicle;
import com.se.ue04.repository.BookedDrivesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookedDrivesService {

    private Logger LOG = LoggerFactory.getLogger(BookedDrivesService.class);

    private BookedDrivesRepository bookedDrivesRepository;

    private VehicleService vehicleService;

    @Autowired
    public void setBookedDrivesRepository(BookedDrivesRepository bookedDrivesRepository) {
        this.bookedDrivesRepository = bookedDrivesRepository;
    }

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public List<BookedDrive> getAllBookedDrives() {
        return bookedDrivesRepository.findAll();
    }

    public List<Vehicle> getAllAvailableDrives() {
        return vehicleService.getAllVehicles();
    }

    public List<BookedDrive> getAllByRouteAndTime(Date time, String route) {
        return bookedDrivesRepository.getByTimeAndRoute(time, route);
    }

    public List<BookedDrive> getAllByRoute(String route) {
        return bookedDrivesRepository.getByRoute(route);
    }

    public List<BookedDrive> getAllByRouteAndDateBetween(String route, Date start, Date end) {
        return bookedDrivesRepository.getByRouteAndTimeBetween(route, start, end);
    }

    public BookedDrive saveBookedDrive(BookedDrive bookedDrive) {
        BookedDrive driveToSave;
        try {
            driveToSave = bookedDrivesRepository.save(bookedDrive);
            return driveToSave;
        } catch (Exception e) {
            LOG.error("An error occurred during drive booking saving: " + e.getMessage());
        }
        return new BookedDrive();
    }

    public List<BookedDrive> getBookedDriveByUser(String id) {
        return bookedDrivesRepository.getByUser(id);
    }

    public void deleteBookedDrive(String id) {
        bookedDrivesRepository.deleteById(id);
    }
}
