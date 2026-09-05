package org.jsp.repository;

import java.util.List;

import org.jsp.dto.ParkingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRequestRepo extends JpaRepository<ParkingRequest, Integer>{
	  List<ParkingRequest> findByStatus(String status);
	  List<ParkingRequest> findByOwnerId(String ownerId);
}
