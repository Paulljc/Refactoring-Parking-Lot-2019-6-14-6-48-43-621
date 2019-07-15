package com.parkinglot;

public interface Parkable {
    ParkingTicket fetchTicketByCar(Car car);
    Car fetchCarByTickey(ParkingTicket ticket);
}
