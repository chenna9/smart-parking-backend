package org.jsp.controller;

import java.util.List;

import org.jsp.dto.ParkingSlot;
import org.jsp.service.ParkingSlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-slots")

public class ParkingSlotController {

    @Autowired
    private ParkingSlotService service;


    // =====================================================
    // CREATE SLOT
    // =====================================================

    @PostMapping
    public ResponseEntity<ParkingSlot> saveSlot(
            @RequestBody ParkingSlot slot) {

        ParkingSlot savedSlot =
                service.saveSlot(slot);

        return new ResponseEntity<>(
                savedSlot,
                HttpStatus.CREATED
        );
    }


    // =====================================================
    // GET ALL SLOTS OF PARKING
    // =====================================================

    @GetMapping("/parking/{parkingId}")
    public ResponseEntity<List<ParkingSlot>>
    getByParkingId(
            @PathVariable int parkingId) {

        return new ResponseEntity<>(
                service.getByParkingId(parkingId),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET ALL SLOTS OF VEHICLE
    // =====================================================

    @GetMapping("/vehicle/{parkingVehicleId}")
    public ResponseEntity<List<ParkingSlot>>
    getByParkingVehicleId(
            @PathVariable int parkingVehicleId) {

        return new ResponseEntity<>(
                service.getByParkingVehicleId(
                        parkingVehicleId
                ),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET ONLY EMPTY SLOTS
    // =====================================================

    @GetMapping("/vehicle/{parkingVehicleId}/empty")
    public ResponseEntity<List<ParkingSlot>>
    getEmptySlots(
            @PathVariable int parkingVehicleId) {

        return new ResponseEntity<>(
                service.getEmptySlots(
                        parkingVehicleId
                ),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET SLOT BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSlot>
    getById(@PathVariable int id) {

        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }


    // =====================================================
    // UPDATE SLOT
    // =====================================================

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSlot>
    updateSlot(
            @PathVariable int id,
            @RequestBody ParkingSlot slot) {

        return new ResponseEntity<>(
                service.updateSlot(id, slot),
                HttpStatus.OK
        );
    }


    // =====================================================
    // DELETE SLOT
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteSlot(@PathVariable int id) {

        service.deleteSlot(id);

        return new ResponseEntity<>(
                "Parking Slot Deleted Successfully",
                HttpStatus.OK
        );
    }
}