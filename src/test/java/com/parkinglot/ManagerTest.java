package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ManagerTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_fetch_ticket_by_car_when_manager_spectify_his_boy() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        List<ParkingBoy> manageParkingBoys = new ArrayList<>();
        ArrayList<ParkingLot> manageParkingLots = new ArrayList<>();
        manageParkingLots.add(parkingLot1);
        ParkingBoy parkingBoy1 = new ParkingBoy();
        parkingBoy1.manageParkingLot(parkingLot1);
        Manager manager = new Manager(manageParkingLots, manageParkingBoys);
        manager.addParkingBoy(parkingBoy1);

        String parkingTicket = manager.specifyBoyParkCar(parkingBoy1, car).getCarLicense();

        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_not_fetch_ticket_by_car_when_manager_spectify_not_his_boy() {
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> manageParkingBoys = new ArrayList<>();
        ArrayList<ParkingLot> manageParkingLots = new ArrayList<>();
        manageParkingLots.add(parkingLot1);
        manageParkingLots.add(parkingLot2);
        ParkingBoy parkingBoy1 = new ParkingBoy();
        parkingBoy1.manageParkingLot(parkingLot1);
        Manager manager = new Manager(manageParkingLots, new ArrayList<>());

        ParkingTicket parkingTicket = manager.specifyBoyParkCar(parkingBoy1, car);

        assertThat(systemOut(), is("You are not in charge of this boy!"));
        assertThat(parkingTicket, nullValue());
    }

    @Test
    public void should_fetch_ticket_by_car_when_manager_park_car_in_his_parkingLot() {
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> manageParkingLots = new ArrayList<>();
        manageParkingLots.add(parkingLot1);
        manageParkingLots.add(parkingLot2);
        Manager manager = new Manager(manageParkingLots, new ArrayList<>());

        String parkingTicket = manager.fetchTicketByCar(car).getCarLicense();

        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_fetch_car_by_ticket_when_manager_in_his_parkingLot() {
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar2 = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed2 = new HashMap<>(0);
        ticketMatchCar2.put(parkingTicket, car);
        ticketIsUsed2.put("9527", false);
        ParkingLot parkingLot2 = new ParkingLot(10, 10, ticketMatchCar2, ticketIsUsed2);
        ArrayList<ParkingLot> manageParkingLots = new ArrayList<>();
        manageParkingLots.add(parkingLot2);
        Manager manager = new Manager(manageParkingLots, new ArrayList<>());

        Car fetchCar = manager.fetchCarByTickey(parkingTicket);

        assertThat(fetchCar.getCarLicense(), is("9527"));
    }

    @Test
    public void should_not_fetch_car_if_ticket_invalid_when_manager_specify_boy() {
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = new ParkingTicket("8080");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        Manager manager = new Manager(parkingLots, new ArrayList<>());
        manager.addParkingBoy(parkingBoy);

        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy, parkingLot, parkingTicket2);

        assertThat(systemOut(), is("Unrecognized parking ticket."));
        assertThat(fetchCar, nullValue());
    }

    @Test
    public void should_not_fetch_car_if_no_ticket_when_manager_specify_boy() {
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = null;
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        Manager manager = new Manager(parkingLots, new ArrayList<>());
        manager.addParkingBoy(parkingBoy);

        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy, parkingLot, parkingTicket2);

        assertThat(systemOut(), is("Please provide your parking ticket."));
        assertThat(fetchCar, nullValue());
    }

    @Test
    public void should_not_fetch_car_if_ticket_has_used_when_manager_specify_boy() {
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ticketIsUsed.put("9527", true);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        Manager manager = new Manager(parkingLots, new ArrayList<>());
        manager.addParkingBoy(parkingBoy);

        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy, parkingLot, parkingTicket1);

        assertThat(systemOut(), is("Sorry, your ticket has uesd!"));
        assertThat(fetchCar, nullValue());
    }

    @Test
    public void should_not_fetch_ticket_if_parkingLot_is_full_when_manager_specify_boy() {
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        Manager manager = new Manager(parkingLots, new ArrayList<>());
        manager.addParkingBoy(parkingBoy);

        ParkingTicket parkingTicket = manager.specifyBoyParkCar(parkingBoy, car);

        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket, nullValue());
    }
}
