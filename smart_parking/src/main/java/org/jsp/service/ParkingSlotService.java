package org.jsp.service;

import java.util.List;

import org.jsp.dto.ParkingSlot;
import org.jsp.repository.ParkingSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepo dao;


    // =====================================================
    // CREATE SLOT
    // =====================================================

    public ParkingSlot saveSlot(ParkingSlot slot) {

        if (slot.getStatus() == null ||
            slot.getStatus().isEmpty()) {

            slot.setStatus("EMPTY");
        }

        return dao.save(slot);
    }


    // =====================================================
    // GET ALL SLOTS OF PARKING
    // =====================================================

    public List<ParkingSlot> getByParkingId(int parkingId) {

        return dao.findByParkingId(parkingId);
    }


    // =====================================================
    // GET ALL SLOTS OF VEHICLE
    // =====================================================

    public List<ParkingSlot> getByParkingVehicleId(
            int parkingVehicleId) {

        return dao.findByParkingVehicleId(
                parkingVehicleId
        );
    }


    // =====================================================
    // GET ONLY EMPTY SLOTS
    // =====================================================

    public List<ParkingSlot> getEmptySlots(
            int parkingVehicleId) {

        return dao.findByParkingVehicleIdAndStatus(
                parkingVehicleId,
                "EMPTY"
        );
    }


    // =====================================================
    // GET SLOT BY ID
    // =====================================================

    public ParkingSlot getById(int id) {

        return dao.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Parking Slot Not Found"
                    )
                );
    }


    // =====================================================
    // UPDATE SLOT
    // =====================================================

    public ParkingSlot updateSlot(
            int id,
            ParkingSlot slot) {

        ParkingSlot dbSlot = getById(id);

        dbSlot.setSlotNumber(
                slot.getSlotNumber()
        );

        dbSlot.setVehicleType(
                slot.getVehicleType()
        );

        dbSlot.setStatus(
                slot.getStatus()
        );

        return dao.save(dbSlot);
    }


    // =====================================================
    // DELETE SLOT
    // =====================================================

    public void deleteSlot(int id) {

        ParkingSlot slot = getById(id);

        dao.delete(slot);
    }
}