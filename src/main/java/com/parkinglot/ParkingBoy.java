package com.parkinglot;

import java.util.ArrayList;

public class ParkingBoy {

    ArrayList<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket fetchTicketByCar(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isParkingFull()) {
                return parkingLot.generateTicketByCar(car);
            }
        }
        System.out.print("Not enough position.");
        return null;
    }

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

    public ParkingTicket parkCarService(Car car) {
        return fetchTicketByCar(car);
    }

    public Car fetchCarService(ParkingTicket parkingTicket) {
        return fetchCarByTickey(parkingTicket);
    }
}
