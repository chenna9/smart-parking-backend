package org.jsp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.dto.ParkingVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingVechileRepo 
	        extends JpaRepository<ParkingVehicle, Integer> {

	    List<ParkingVehicle> findByParkingId(int parkingId);
	    Optional<ParkingVehicle> findByParkingIdAndVehicleType(
	            int parkingId,
	            String vehicleType
	    );

	
}
