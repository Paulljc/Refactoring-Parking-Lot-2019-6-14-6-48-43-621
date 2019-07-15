package com.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingAttendant {

    @Override
    public ParkingTicket fetchTicketByCar(Car car) {
        List<ParkingLot> filterParkingLots = new ArrayList<>();
        filterParkingLots =  parkingLots.stream().filter(parkingLot -> !parkingLot.isParkingFull()).collect(Collectors.toList());
        if (filterParkingLots.size() != 0){
            return filterParkingLots.stream().sorted(Comparator.comparing(ParkingLot::getCapacity).reversed()).collect(Collectors.toList()).get(0).generateTicketByCar(car);
        }else{
            System.out.print("Not enough position.");
            return null;
        }
    }
}
