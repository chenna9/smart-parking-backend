	package org.jsp.controller;


import java.util.List;

import org.jsp.dto.ParkingRequest;
import org.jsp.service.ParkingRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-requests")
@CrossOrigin(origins = "http://localhost:5173")
public class ParkingRequestController {

    @Autowired
    private ParkingRequestService service;


    @PostMapping
    public ResponseEntity<ParkingRequest> saveParkingRequest(
            @RequestBody ParkingRequest request) {

        ParkingRequest savedRequest =
                service.saveParkingRequest(request);

        return new ResponseEntity<>(
                savedRequest,
                HttpStatus.CREATED
        );
    }
    @GetMapping
    public ResponseEntity<List<ParkingRequest>> getAllRequests() {

        return new ResponseEntity<>(
                service.getAllRequests(),
                HttpStatus.OK
        );
    }
    @PutMapping("/{id}/approve")
    public ResponseEntity<ParkingRequest> approveRequest(
            @PathVariable int id) {

        return new ResponseEntity<>(
                service.approveRequest(id),
                HttpStatus.OK
        );
    }
    @PutMapping("/{id}/reject")
    public ResponseEntity<ParkingRequest> rejectRequest(
            @PathVariable int id) {

        return new ResponseEntity<>(
                service.rejectRequest(id),
                HttpStatus.OK
		);
	}
    @GetMapping("/approved")
    public ResponseEntity<List<ParkingRequest>> getApprovedParkingAreas() {

        return new ResponseEntity<>(
            service.getApprovedParkingAreas(),
            HttpStatus.OK
        );
    }
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ParkingRequest>> getOwnerParkingAreas(
            @PathVariable String ownerId) {

        return new ResponseEntity<>(
                service.getOwnerParkingAreas(ownerId),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParkingRequest> getParkingById(
            @PathVariable int id) {

        ParkingRequest parking =
                service.getParkingById(id);

        return new ResponseEntity<>(
                parking,
                HttpStatus.OK
        );
    }
}