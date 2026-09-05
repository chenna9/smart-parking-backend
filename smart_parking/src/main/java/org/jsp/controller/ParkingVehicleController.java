package org.jsp.controller;

import java.util.List;

import org.jsp.dto.ParkingVehicle;
import org.jsp.service.ParkingVehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-vehicles")

public class ParkingVehicleController {

    @Autowired
    private ParkingVehicleService service;


    // =====================================================
    // ADD OR UPDATE VEHICLE
    // =====================================================

    @PostMapping
    public ResponseEntity<ParkingVehicle> addOrUpdateParkingVehicle(
            @RequestBody ParkingVehicle parkingVehicle) {

        ParkingVehicle result =
                service.addOrUpdateParkingVehicle(
                        parkingVehicle
                );

        return new ResponseEntity<>(
                result,
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParkingVehicle> getById(
            @PathVariable int id) {

        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET ALL VEHICLES
    // =====================================================

    @GetMapping
    public ResponseEntity<List<ParkingVehicle>> getAllParkingVehicles() {

        List<ParkingVehicle> vehicles =
                service.getAllParkingVehicles();

        return new ResponseEntity<>(
                vehicles,
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET VEHICLES BY PARKING ID
    // =====================================================

    @GetMapping("/parking/{parkingId}")
    public ResponseEntity<List<ParkingVehicle>> getByParkingId(
            @PathVariable int parkingId) {

        List<ParkingVehicle> vehicles =
                service.getByParkingId(parkingId);

        return new ResponseEntity<>(
                vehicles,
                HttpStatus.OK
        );
    }


    // =====================================================
    // UPDATE VEHICLE BY ID
    // =====================================================

    @PutMapping("/{id}")
    public ResponseEntity<ParkingVehicle> updateParkingVehicle(
            @PathVariable int id,
            @RequestBody ParkingVehicle parkingVehicle) {

        ParkingVehicle updated =
                service.updateParkingVehicle(
                        id,
                        parkingVehicle
                );

        return new ResponseEntity<>(
                updated,
                HttpStatus.OK
        );
    }


    // =====================================================
    // DELETE VEHICLE
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParkingVehicle(
            @PathVariable int id) {

        service.deleteVehicle(id);

        return new ResponseEntity<>(
                "Parking vehicle deleted successfully",
                HttpStatus.OK
        );
    }
//    
}