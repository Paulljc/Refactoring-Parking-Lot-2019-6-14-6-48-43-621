package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingAttendant {

    protected List<ParkingLot> parkingLots = new ArrayList<>();

    public abstract ParkingTicket fetchTicketByCar(Car car);

    public Car fetchCarByTickey(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            System.out.print("Please provide your parking ticket.");
            return null;
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.checkCarIsInParkingLot(parkingTicket)) {
                return parkingLot.TakeOutCarByTicket(parkingTicket);
            }
        }
        System.out.print("Unrecognized parking ticket.");
        return null;
    }

    public void manageParkingLot(ParkingLot parkinglot) {
        if (!parkingLots.contains(parkinglot) && parkinglot != null) {
            parkingLots.add(parkinglot);
        }
    }
}
