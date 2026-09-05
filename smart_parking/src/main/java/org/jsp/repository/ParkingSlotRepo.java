package org.jsp.repository;

import java.util.List;

import org.jsp.dto.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Integer>{
	 List<ParkingSlot> findByParkingId(int parkingId);

	    List<ParkingSlot> findByParkingVehicleId(int parkingVehicleId);

	    List<ParkingSlot> findByParkingVehicleIdAndStatus(
	            int parkingVehicleId,
	            String status
	    );
}
