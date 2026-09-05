package org.jsp.service;

import java.util.List;

import org.jsp.dto.Booking;
import org.jsp.dto.ParkingSlot;
import org.jsp.repository.BookingRepo;
import org.jsp.repository.ParkingSlotRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingDao;

    @Autowired
    private ParkingSlotRepo parkingSlotDao;


    // =====================================================
    // CREATE BOOKING
    // =====================================================

    @Transactional
    public Booking bookParking(Booking booking) {

        // ---------------------------------------------
        // Find selected slot
        // ---------------------------------------------

        ParkingSlot slot =
                parkingSlotDao.findById(
                        booking.getParkingSlotId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Parking Slot Not Found"
                        )
                );


        // ---------------------------------------------
        // CHECK SLOT STATUS
        // ---------------------------------------------

        if (!"EMPTY".equalsIgnoreCase(
                slot.getStatus()
        )) {

            throw new RuntimeException(
                    "Parking Slot is already booked"
            );
        }


        // ---------------------------------------------
        // Validate duration
        // ---------------------------------------------

        if (booking.getDuration() <= 0) {

            throw new RuntimeException(
                    "Duration must be greater than 0"
            );
        }


        // ---------------------------------------------
        // BOOK SLOT
        // ---------------------------------------------

        slot.setStatus("BOOKED");

        parkingSlotDao.save(slot);


        // ---------------------------------------------
        // SAVE BOOKING
        // ---------------------------------------------

        return bookingDao.save(booking);
    }


    // =====================================================
    // GET ALL BOOKINGS
    // =====================================================

    public List<Booking> getAllBookings() {

        return bookingDao.findAll();
    }


    // =====================================================
    // GET BOOKING BY ID
    // =====================================================

    public Booking getById(int id) {

        return bookingDao.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Booking Not Found"
                        )
                );
    }


    // =====================================================
    // GET USER BOOKINGS
    // =====================================================

    public List<Booking> getByUserId(
            String userId) {

        return bookingDao.findByUserId(
                userId
        );
    }


    // =====================================================
    // GET PARKING BOOKINGS
    // =====================================================

    public List<Booking> getByParkingId(
            int parkingId) {

        return bookingDao.findByParkingId(
                parkingId
        );
    }


    // =====================================================
    // GET SLOT BOOKINGS
    // =====================================================

    public List<Booking> getByParkingSlotId(
            int parkingSlotId) {

        return bookingDao.findByParkingSlotId(
                parkingSlotId
        );
    }
}