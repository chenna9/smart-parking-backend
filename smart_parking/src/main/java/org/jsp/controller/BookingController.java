package org.jsp.controller;

import java.util.List;


import org.jsp.dto.Booking;
import org.jsp.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService service;


    // =====================================================
    // CREATE BOOKING
    // =====================================================

    @PostMapping
    public ResponseEntity<Booking> bookParking(
            @RequestBody Booking booking) {

        Booking savedBooking =
                service.bookParking(booking);

        return new ResponseEntity<>(
                savedBooking,
                HttpStatus.CREATED
        );
    }


    // =====================================================
    // GET ALL BOOKINGS
    // =====================================================

    @GetMapping
    public ResponseEntity<List<Booking>>
    getAllBookings() {

        return new ResponseEntity<>(
                service.getAllBookings(),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET BOOKING BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<Booking>
    getById(@PathVariable int id) {

        return new ResponseEntity<>(
                service.getById(id),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET USER BOOKINGS
    // =====================================================

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>>
    getByUserId(
            @PathVariable String userId) {

        return new ResponseEntity<>(
                service.getByUserId(userId),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET PARKING BOOKINGS
    // =====================================================

    @GetMapping("/parking/{parkingId}")
    public ResponseEntity<List<Booking>>
    getByParkingId(
            @PathVariable int parkingId) {

        return new ResponseEntity<>(
                service.getByParkingId(parkingId),
                HttpStatus.OK
        );
    }


    // =====================================================
    // GET SLOT BOOKINGS
    // =====================================================

    @GetMapping("/slot/{parkingSlotId}")
    public ResponseEntity<List<Booking>>
    getByParkingSlotId(
            @PathVariable int parkingSlotId) {

        return new ResponseEntity<>(
                service.getByParkingSlotId(
                        parkingSlotId
                ),
                HttpStatus.OK
        );
    }
}