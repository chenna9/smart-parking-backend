package org.jsp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.dto.ParkingSlot;
import org.jsp.dto.ParkingVehicle;
import org.jsp.repository.ParkingSlotRepo;
import org.jsp.repository.ParkingVechileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ParkingVehicleService {

    @Autowired
    private ParkingVechileRepo dao;

    @Autowired
    private ParkingSlotRepo parkingSlotDao;


    // =====================================================
    // ADD OR UPDATE VEHICLE
    // =====================================================

    @Transactional
    public ParkingVehicle addOrUpdateParkingVehicle(
            ParkingVehicle parkingVehicle) {

        Optional<ParkingVehicle> existing =
                dao.findByParkingIdAndVehicleType(
                        parkingVehicle.getParkingId(),
                        parkingVehicle.getVehicleType()
                );

        ParkingVehicle vehicle;

        if (existing.isPresent()) {

            vehicle = existing.get();

            vehicle.setTotalSlots(
                    parkingVehicle.getTotalSlots()
            );

            vehicle.setHourlyPrice(
                    parkingVehicle.getHourlyPrice()
            );

            vehicle.setDailyPrice(
                    parkingVehicle.getDailyPrice()
            );

        } else {

            vehicle = parkingVehicle;
        }

        // Save vehicle first so ID is available
        vehicle = dao.save(vehicle);

        // ---------------------------------------------
        // Check existing slots
        // ---------------------------------------------

        List<ParkingSlot> existingSlots =
                parkingSlotDao.findByParkingVehicleId(
                        vehicle.getId()
                );

        int existingCount = existingSlots.size();

        int requiredCount = vehicle.getTotalSlots();

        System.out.println("================================");
        System.out.println("Parking Vehicle ID : "
                + vehicle.getId());

        System.out.println("Vehicle Type       : "
                + vehicle.getVehicleType());

        System.out.println("Required Slots     : "
                + requiredCount);

        System.out.println("Existing Slots     : "
                + existingCount);

        System.out.println("================================");


        // ---------------------------------------------
        // Create missing slots
        // ---------------------------------------------

        if (existingCount < requiredCount) {

            for (
                int i = existingCount + 1;
                i <= requiredCount;
                i++
            ) {

                createSlot(vehicle, i);
            }
        }

        return vehicle;
    }
    // =====================================================
    // CREATE SLOT
    // =====================================================

    private void createSlot(
            ParkingVehicle vehicle,
            int number) {


        ParkingSlot slot =
                new ParkingSlot();


        slot.setParkingId(
                vehicle.getParkingId()
        );


        slot.setParkingVehicleId(
                vehicle.getId()
        );


        slot.setVehicleType(
                vehicle.getVehicleType()
        );


        slot.setSlotNumber(
                getSlotNumber(
                        vehicle.getVehicleType(),
                        number
                )
        );


        slot.setStatus(
                "EMPTY"
        );


        System.out.println(
                "---------------------------------"
        );

        System.out.println(
                "CREATING SLOT"
        );

        System.out.println(
                "Slot Number = "
                + slot.getSlotNumber()
        );

        System.out.println(
                "Parking ID = "
                + slot.getParkingId()
        );

        System.out.println(
                "Parking Vehicle ID = "
                + slot.getParkingVehicleId()
        );

        System.out.println(
                "Vehicle Type = "
                + slot.getVehicleType()
        );

        System.out.println(
                "Status = "
                + slot.getStatus()
        );


        ParkingSlot savedSlot =
                parkingSlotDao.save(slot);


        System.out.println(
                "SLOT SAVED WITH ID = "
                + savedSlot.getId()
        );

        System.out.println(
                "---------------------------------"
        );
    }


    // =====================================================
    // SLOT NUMBER
    // =====================================================

    private String getSlotNumber(
            String vehicleType,
            int number) {


        String prefix;


        switch (
                vehicleType.toUpperCase()
        ) {

            case "CAR":
                prefix = "C";
                break;

            case "BIKE":
                prefix = "B";
                break;

            case "EV":
                prefix = "E";
                break;

            case "SUV":
                prefix = "S";
                break;

            case "TRUCK":
                prefix = "T";
                break;

            default:
                prefix = "P";
        }


        return prefix
                + String.format(
                        "%02d",
                        number
                );
    }


    // =====================================================
    // GET ALL VEHICLES
    // =====================================================

    public List<ParkingVehicle> getAllParkingVehicles() {

        return dao.findAll();
    }


    // =====================================================
    // GET VEHICLES BY PARKING
    // =====================================================

    public List<ParkingVehicle> getByParkingId(
            int parkingId) {

        return dao.findByParkingId(
                parkingId
        );
    }


    // =====================================================
    // GET VEHICLE BY ID
    // =====================================================

    public ParkingVehicle getById(
            int id) {

        return dao.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Parking Vehicle Not Found"
                        )
                );
    }


    // =====================================================
    // UPDATE VEHICLE
    // =====================================================

    @Transactional
    public ParkingVehicle updateParkingVehicle(
            int id,
            ParkingVehicle parkingVehicle) {


        ParkingVehicle existing =
                getById(id);


        existing.setVehicleType(
                parkingVehicle.getVehicleType()
        );


        existing.setTotalSlots(
                parkingVehicle.getTotalSlots()
        );


        existing.setHourlyPrice(
                parkingVehicle.getHourlyPrice()
        );


        existing.setDailyPrice(
                parkingVehicle.getDailyPrice()
        );


        ParkingVehicle savedVehicle =
                dao.save(existing);


        // Check actual slots
        List<ParkingSlot> existingSlots =
                parkingSlotDao.findByParkingVehicleId(
                        savedVehicle.getId()
                );


        int existingSlotCount =
                existingSlots.size();


        int requiredSlotCount =
                savedVehicle.getTotalSlots();


        if (existingSlotCount < requiredSlotCount) {

            for (
                    int i = existingSlotCount + 1;
                    i <= requiredSlotCount;
                    i++
            ) {

                createSlot(
                        savedVehicle,
                        i
                );
            }
        }


        return savedVehicle;
    }


    // =====================================================
    // DELETE VEHICLE
    // =====================================================

    @Transactional
    public void deleteVehicle(
            int id) {


        ParkingVehicle vehicle =
                getById(id);


        List<ParkingSlot> slots =
                parkingSlotDao.findByParkingVehicleId(
                        id
                );


        parkingSlotDao.deleteAll(
                slots
        );


        dao.delete(vehicle);
    }
}