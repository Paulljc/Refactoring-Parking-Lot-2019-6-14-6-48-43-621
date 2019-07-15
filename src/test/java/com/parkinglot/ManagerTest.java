//package com.parkinglot;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class ManagerTest {
//
//    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//
//    @BeforeEach
//    public void setup() {
//        System.setOut(new PrintStream(outContent));
//    }
//
//    private String systemOut() {
//        return outContent.toString();
//    }
//
//    @Test
//    public void should_fetch_ticket_by_car_when_manager_spectify_his_boy() {
//        //given
//        Car car = new Car("9527");
//        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
//        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
//        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
//        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
//        boy_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot2);
//        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
//        Manager manager = new Manager(manager_01_parkingLots);
//        manager.addParkingBoy(parkingBoy1);
//        //when
//        String parkingTicket = manager.specifyBoyParkCar(parkingBoy1, car).getCarLicense();
//        //then
//        assertThat(parkingTicket, is("9527"));
//    }
//
//    @Test
//    public void should_not_fetch_ticket_by_car_when_manager_spectify_not_his_boy() {
//        //given
//        Car car = new Car("9527");
//        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
//        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
//        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
//        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
//        boy_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot2);
//        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
//        Manager manager = new Manager(manager_01_parkingLots);
//        //when
//        ParkingTicket parkingTicket = manager.specifyBoyParkCar(parkingBoy1, car);
//        //then
//        assertThat(systemOut(), is("You are not in charge of this boy!"));
//        assertThat(parkingTicket, nullValue());
//    }
//
//    @Test
//    public void should_fetch_car_by_ticket_when_manager_spectify_his_boy_in_parkingLot() {
//        //given
//        Car car = new Car("9527");
//        ParkingTicket parkingTicket = new ParkingTicket("9527");
//        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
//        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
//        ticketMatchCar.put(parkingTicket, car);
//        ticketIsUsed.put("9527", false);
//        ParkingLot parkingLot1 = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
//        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
//        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
//        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
//        boy_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot2);
//        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
//        Manager manager = new Manager(manager_01_parkingLots);
//        manager.addParkingBoy(parkingBoy1);
//        //when
//        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy1, parkingLot1, parkingTicket);
//        //then
//        assertThat(fetchCar.getCarLicense(), is("9527"));
//    }
//
//    @Test
//    public void should_fetch_car_by_ticket_when_manager_spectify_his_boy_not_in_parkingLot() {
//        //given
//        Car car = new Car("9527");
//        ParkingTicket parkingTicket = new ParkingTicket("9527");
//        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
//        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
//        ticketMatchCar.put(parkingTicket, car);
//        ticketIsUsed.put("9527", false);
//        ParkingLot parkingLot1 = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
//        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
//        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
//        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
//        boy_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot2);
//        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
//        Manager manager = new Manager(manager_01_parkingLots);
//        manager.addParkingBoy(parkingBoy1);
//        //when
//        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy1, parkingLot2, parkingTicket);
//        //then
//        assertThat(systemOut(), is("This parking boy can not fetch cay in this parkingLot"));
//        assertThat(fetchCar, nullValue());
//    }
//
//    @Test
//    public void should_fetch_ticket_by_car_when_manager_park_car_in_his_parkingLot() {
//        //given
//        Car car = new Car("9527");
//        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
//        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
//        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
//        manager_01_parkingLots.add(parkingLot1);
//        manager_01_parkingLots.add(parkingLot2);
//        Manager manager = new Manager(manager_01_parkingLots);
//        //when
//        String parkingTicket = manager.fetchTicketByCar(car).getCarLicense();
//        //then
//        assertThat(parkingTicket, is("9527"));
//    }
//
//    @Test
//    public void should_fetch_car_by_ticket_when_manager_in_his_parkingLot() {
//        //given
//        ParkingTicket parkingTicket = new ParkingTicket("9527");
//        Car car = new Car("9527");
//        HashMap<ParkingTicket, Car> ticketMatchCar2 = new HashMap<>(0);
//        HashMap<String, Boolean> ticketIsUsed2 = new HashMap<>(0);
//        ticketMatchCar2.put(parkingTicket, car);
//        ticketIsUsed2.put("9527", false);
//        ParkingLot parkingLot2 = new ParkingLot(10, 10, ticketMatchCar2, ticketIsUsed2);
//        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
//        manager_01_parkingLots.add(parkingLot2);
//        Manager manager = new Manager(manager_01_parkingLots);
//        //when
//        Car fetchCar = manager.fetchCarByTickey(parkingTicket);
//        //then
//        assertThat(fetchCar.getCarLicense(), is("9527"));
//    }
//
//    @Test
//    public void should_not_fetch_car_if_ticket_invalid_when_manager_specify_boy() {
//        //given
//        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
//        ParkingTicket parkingTicket2 = new ParkingTicket("8080");
//        Car car = new Car("9527");
//        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
//        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
//        ticketMatchCar.put(parkingTicket1, car);
//        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        Manager manager = new Manager(parkingLots);
//        manager.addParkingBoy(parkingBoy);
//        //when
//        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy, parkingLot, parkingTicket2);
//        //then
//        assertThat(systemOut(), is("Unrecognized parking ticket."));
//        assertThat(fetchCar, nullValue());
//    }
//
//    @Test
//    public void should_not_fetch_car_if_no_ticket_when_manager_specify_boy() {
//        //given
//        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
//        ParkingTicket parkingTicket2 = null;
//        Car car = new Car("9527");
//        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
//        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
//        ticketMatchCar.put(parkingTicket1, car);
//        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        Manager manager = new Manager(parkingLots);
//        manager.addParkingBoy(parkingBoy);
//        //when
//        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy, parkingLot, parkingTicket2);
//        //then
//        assertThat(systemOut(), is("Please provide your parking ticket."));
//        assertThat(fetchCar, nullValue());
//    }
//
//    @Test
//    public void should_not_fetch_car_if_ticket_has_used_when_manager_specify_boy() {
//        //given
//        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
//        Car car = new Car("9527");
//        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
//        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
//        ticketMatchCar.put(parkingTicket1, car);
//        ticketIsUsed.put("9527", true);
//        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
//        parkingLots.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        Manager manager = new Manager(parkingLots);
//        manager.addParkingBoy(parkingBoy);
//        //when
//        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy, parkingLot, parkingTicket1);
//        //then
//        assertThat(systemOut(), is("Sorry, your ticket has uesd!"));
//        assertThat(fetchCar, nullValue());
//    }
//
//    @Test
//    public void should_not_fetch_ticket_if_parkingLot_is_full_when_manager_specify_boy() {
//        //given
//        Car car = new Car("9527");
//        ParkingLot parkingLot = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
//        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
//        parkingLots.add(parkingLot);
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
//        Manager manager = new Manager(parkingLots);
//        manager.addParkingBoy(parkingBoy);
//        //when
//        ParkingTicket parkingTicket = manager.specifyBoyParkCar(parkingBoy, car);
//        //then
//        assertThat(systemOut(), is("Not enough position."));
//        assertThat(parkingTicket, nullValue());
//    }
//}
