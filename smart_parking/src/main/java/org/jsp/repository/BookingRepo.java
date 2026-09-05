package org.jsp.repository;

import java.util.List;

import org.jsp.dto.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

    List<Booking> findByUserId(String userId);

    List<Booking> findByParkingId(int parkingId);

    List<Booking> findByParkingSlotId(int slotId);
}
