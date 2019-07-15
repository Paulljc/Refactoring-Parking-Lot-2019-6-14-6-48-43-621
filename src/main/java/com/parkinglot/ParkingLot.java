package com.parkinglot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParkingLot {

    private int capacity;
    private int totalCapacity;
    private HashMap<ParkingTicket, Car> ticketMatchCar;
    private HashMap<String, Boolean> ticketIsUsed;


    public ParkingLot(int capacity, int totalCapacity, HashMap<ParkingTicket, Car> ticketMatchCar, HashMap<String, Boolean> ticketIsUsed) {
        this.capacity = capacity;
        this.totalCapacity = totalCapacity;
        this.ticketMatchCar = ticketMatchCar;
        this.ticketIsUsed = ticketIsUsed;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashMap<ParkingTicket, Car> getTicketMatchCar() {
        return ticketMatchCar;
    }

    public HashMap<String, Boolean> getTicketIsUsed() {
        return ticketIsUsed;
    }

    public ParkingTicket generateTicketByCar(Car car) {
        if (ticketMatchCar.containsKey(car.getCarLicense())) {
            System.out.print("Car is in the parkingLot！");
            return null;
        } else {
            ParkingTicket parkingTicket = new ParkingTicket(car.getCarLicense());
            ticketMatchCar.put(parkingTicket, car);
            capacity -= 1;
            if (ticketIsUsed.containsKey(car.getCarLicense())) {
                ticketIsUsed.put(car.getCarLicense(), false);
            } else {
                ticketIsUsed.put(car.getCarLicense(), false);
            }
            return parkingTicket;
        }
    }

    public Car TakeOutCarByTicket(ParkingTicket parkingTicket) {
        if (!ticketIsUsed.get(parkingTicket.getCarLicense())) {
            Car car = ticketMatchCar.get(parkingTicket);
            ticketIsUsed.put(parkingTicket.getCarLicense(), true);
            capacity += 1;
            return car;
        } else {
            System.out.print("Sorry, your ticket has uesd!");
            return null;
        }
    }

    public boolean isParkingFull() {
        return capacity <= 0;
    }

    public boolean checkCarIsInParkingLot(ParkingTicket parkingTicket) {
        return ticketMatchCar.containsKey(parkingTicket);
    }

    public double culParkingRates() {
        return (double) this.capacity / (double) this.totalCapacity;
    }
}
