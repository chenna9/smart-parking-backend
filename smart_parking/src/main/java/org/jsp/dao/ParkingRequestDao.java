package org.jsp.dao;

import org.jsp.dto.ParkingRequest;
import org.jsp.repository.ParkingRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingRequestDao {
	@Autowired
	private ParkingRequestRepo repo;

	public ParkingRequest saveParkingRequest(ParkingRequest request) {

		request.setStatus("PENDING");

		return repo.save(request);
	}
}
