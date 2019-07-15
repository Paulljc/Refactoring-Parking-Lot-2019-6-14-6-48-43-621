package com.parkinglot;

import java.util.ArrayList;

public class ParkingBoy {

    ArrayList<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket fetchTicketByCar(Car car) {
        for (int i = 0; i < parkingLots.size(); i++) {
            if (!parkingLots.get(i).isParkingFull()) {
                return parkingLots.get(i).generateTicketByCar(car);
            } else {
                if (i + 1 < parkingLots.size()) {
                    continue;
                } else {
                    break;
                }
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
        for (int i = 0; i < parkingLots.size(); i++) {
            if (parkingLots.get(i).checkCarIsInParkingLot(parkingTicket)) {
                return parkingLots.get(i).TakeOutCarByTicket(parkingTicket);
            } else {
                if (i + 1 < parkingLots.size()) {
                    continue;
                } else {
                    break;
                }
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
