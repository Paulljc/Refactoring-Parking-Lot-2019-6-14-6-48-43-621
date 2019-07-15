package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Manager extends ParkingBoy {

    private List<ParkingLot> manageParkingLots = new ArrayList<>();
    private List<ParkingBoy> manageParkingBoys = new ArrayList<>();


    public Manager(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
        this.manageParkingLots = parkingLots;
    }

    public Manager(ArrayList<ParkingLot> parkingLots, List<ParkingLot> manageParkingLots, List<ParkingBoy> manageParkingBoys) {
        super(parkingLots);
        this.manageParkingLots = manageParkingLots;
        this.manageParkingBoys = manageParkingBoys;
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        manageParkingBoys.add(parkingBoy);
    }

    public ParkingTicket specifyBoyParkCar(ParkingBoy parkingBoy, Car car) {
        if (manageParkingBoys.contains(parkingBoy)) {
            return parkingBoy.parkCarService(car);
        } else {
            System.out.print("You are not in charge of this boy!");
            return null;
        }
    }

    public Car specifyBoyFetchCar(ParkingBoy parkingBoy, ParkingLot parkingLot, ParkingTicket parkingTicket) {
        if (!manageParkingBoys.contains(parkingBoy)) {
            System.out.print("You are not in charge of this boy!");
            return null;
        } else if (!parkingBoy.parkingLots.contains(parkingLot)) {
            System.out.print("This parking boy can not fetch cay in this parkingLot");
            return null;
        } else {
            return parkingBoy.fetchCarService(parkingTicket);
        }
    }

    @Override
    public ParkingTicket fetchTicketByCar(Car car) {
        for (int i = 0; i < manageParkingLots.size(); i++) {
            if (!manageParkingLots.get(i).isParkingFull()) {
                return manageParkingLots.get(i).generateTicketByCar(car);
            } else {
                if (i + 1 < manageParkingLots.size()) {
                    continue;
                } else {
                    break;
                }
            }
        }
        System.out.print("Not enough position.");
        return null;
    }

    @Override
    public Car fetchCarByTickey(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            System.out.print("Please provide your parking ticket.");
            return null;
        }
        for (int i = 0; i < manageParkingLots.size(); i++) {
            if (manageParkingLots.get(i).checkCarIsInParkingLot(parkingTicket)) {
                return manageParkingLots.get(i).TakeOutCarByTicket(parkingTicket);
            } else {
                if (i + 1 < manageParkingLots.size()) {
                    continue;
                } else {
                    break;
                }
            }
        }
        System.out.print("Unrecognized parking ticket.");
        return null;
    }

    public List<ParkingLot> getManageParkingLots() {
        return manageParkingLots;
    }

    public List<ParkingBoy> getManageParkingBoys() {
        return manageParkingBoys;
    }
}
