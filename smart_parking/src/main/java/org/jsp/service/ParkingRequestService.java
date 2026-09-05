package org.jsp.service;

import java.util.List;

import org.jsp.dao.ParkingRequestDao;
import org.jsp.dto.ParkingRequest;
import org.jsp.repository.ParkingRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingRequestService {
	@Autowired
	private ParkingRequestDao dao;
	@Autowired
	private ParkingRequestRepo repo;
	
	public ParkingRequest saveParkingRequest(ParkingRequest parkingRequest) {
		return dao.saveParkingRequest(parkingRequest);
	}
	
	  // Admin gets all parking requests
	 public List<ParkingRequest> getAllRequests() {

	        return repo.findAll();
	   }
	// Admin approves request
	 public ParkingRequest approveRequest(int id) {

	        ParkingRequest request = repo.findById(id)
	                .orElseThrow(() ->
	                    new RuntimeException("Parking Request Not Found")
	                );

	        request.setStatus("APPROVED");

	        return repo.save(request);
	    }
	 // Admin rejects request
	    public ParkingRequest rejectRequest(int id) {

	        ParkingRequest request = repo.findById(id)
	                .orElseThrow(() ->
	                    new RuntimeException("Parking Request Not Found")
	                );

	        request.setStatus("REJECTED");

	        return repo.save(request);
	    }
	    
	    public List<ParkingRequest> getApprovedParkingAreas() {

	        return repo.findByStatus("APPROVED");
	    }
	    public List<ParkingRequest> getOwnerParkingAreas(String ownerId) {

	        return repo.findByOwnerId(ownerId);
	    }
	    public ParkingRequest getParkingById(int id) {

	        return repo.findById(id)
	                .orElseThrow(() ->
	                    new RuntimeException(
	                        "Parking Request Not Found"
	                    )
	                );
	    }
	}

