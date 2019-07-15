package com.parkinglot;

public class ParkingBoy extends ParkingAttendant{

    @Override
    public ParkingTicket fetchTicketByCar(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isParkingFull()) {
                return parkingLot.generateTicketByCar(car);
            }
        }
        System.out.print("Not enough position.");
        return null;
    }

    public ParkingTicket parkCarService(Car car) {
        return fetchTicketByCar(car);
    }

    public Car fetchCarService(ParkingTicket parkingTicket) {
        return fetchCarByTickey(parkingTicket);
    }
}
