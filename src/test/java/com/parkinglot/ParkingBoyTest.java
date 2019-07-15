package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingBoyTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_fetch_ticket_by_car() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        String parkingTicket = parkingBoy.fetchTicketByCar(car).getCarLicense();
        //then
        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_fetch_car_by_ticket() {
        //given
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket, car);
        ticketIsUsed.put("9527", false);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar = parkingBoy.fetchCarByTickey(parkingTicket);
        //then
        assertThat(fetchCar.getCarLicense(), is("9527"));
    }

    @Test
    public void should_fetch_corresponding_ticket_by_different_car() {
        //given
        Car car1 = new Car("9527");
        Car car2 = new Car("8080");
        ParkingLot parkingLot = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        String parkingTicket1 = parkingBoy.fetchTicketByCar(car1).getCarLicense();
        String parkingTicket2 = parkingBoy.fetchTicketByCar(car2).getCarLicense();
        //then
        assertThat(parkingTicket1, is("9527"));
        assertThat(parkingTicket2, is("8080"));
    }

    @Test
    public void should_fetch_corresponding_car_by_different_ticket() {
        //given
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = new ParkingTicket("8080");
        Car car1 = new Car("9527");
        Car car2 = new Car("8080");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car1);
        ticketMatchCar.put(parkingTicket2, car2);
        ticketIsUsed.put("9527", false);
        ticketIsUsed.put("8080", false);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar1 = parkingBoy.fetchCarByTickey(parkingTicket1);
        Car fetchCar2 = parkingBoy.fetchCarByTickey(parkingTicket2);
        //then
        assertThat(fetchCar1.getCarLicense(), is("9527"));
        assertThat(fetchCar2.getCarLicense(), is("8080"));
    }

    @Test
    public void should_not_fetch_car_if_ticket_invalid() {
        //given
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = new ParkingTicket("8080");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar = parkingBoy.fetchCarByTickey(parkingTicket2);
        //then
        assertThat(systemOut(), is("Unrecognized parking ticket."));
        assertThat(fetchCar, nullValue());
    }

    @Test
    public void should_not_fetch_car_if_no_ticket() {
        //given
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = null;
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar = parkingBoy.fetchCarByTickey(parkingTicket2);
        //then
        assertThat(systemOut(), is("Please provide your parking ticket."));
        assertThat(fetchCar, nullValue());
    }

    @Test
    public void should_not_fetch_ticket_if_parkingLot_is_full() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        ParkingTicket parkingTicket = parkingBoy.fetchTicketByCar(car);
        //then
        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket, nullValue());
    }

    @Test
    public void should_park_second_parkingLot_if_first_parkingLot_is_full() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        String parkingTicket = parkingBoy.fetchTicketByCar(car).getCarLicense();
        //then
        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_fetch_car_by_ticket_in_different_paarkingLot() {
        //given
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar1 = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed1 = new HashMap<>(0);
        HashMap<ParkingTicket, Car> ticketMatchCar2 = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed2 = new HashMap<>(0);
        ticketMatchCar2.put(parkingTicket, car);
        ticketIsUsed2.put("9527", false);
        ParkingLot parkingLot1 = new ParkingLot(10, 10, ticketMatchCar1, ticketIsUsed1);
        ParkingLot parkingLot2 = new ParkingLot(10, 10, ticketMatchCar2, ticketIsUsed2);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar = parkingBoy.fetchCarByTickey(parkingTicket);
        //then
        assertThat(fetchCar.getCarLicense(), is("9527"));
    }

    @Test
    public void should_fetch_ticket_give_car_to_smart_boy() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(11, 11, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot3 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        String parkingTicket = smartParkingBoy.fetchTicketByCar(car).getCarLicense();
        //then
        assertThat(parkingTicket, is("9527"));
        assertThat(parkingLot2.getCapacity(), is(11));
    }

    @Test
    public void should_not_fetch_ticket_give_car_to_smart_boy_when_parkinglot_is_all_full() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(0, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        ParkingTicket parkingTicket = smartParkingBoy.fetchTicketByCar(car);
        //then
        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket, nullValue());
    }

    @Test
    public void should_fetch_ticket_give_car_to_super_smart_boy() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(5, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(8, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot3 = new ParkingLot(9, 10, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when
        String parkingTicket = superSmartParkingBoy.fetchTicketByCar(car).getCarLicense();
        //then
        assertThat(parkingTicket, is("9527"));
        assertThat(parkingLot3.getCapacity(), is(8));
    }

    @Test
    public void should_not_fetch_ticket_give_car_to_super_smart_boy_when_parkinglot_is_all_full() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(0, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.fetchTicketByCar(car);
        //then
        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket, nullValue());
    }
}
