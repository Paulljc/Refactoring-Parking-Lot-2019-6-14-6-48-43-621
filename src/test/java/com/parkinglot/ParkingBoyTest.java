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
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);

        String parkingTicket = parkingBoy.fetchTicketByCar(car).getCarLicense();

        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_fetch_car_by_ticket() {
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket, car);
        ticketIsUsed.put("9527", false);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);

        Car fetchCar = parkingBoy.fetchCarByTickey(parkingTicket);

        assertThat(fetchCar.getCarLicense(), is("9527"));
    }

    @Test
    public void should_fetch_corresponding_ticket_by_different_car() {
        Car car1 = new Car("9527");
        Car car2 = new Car("8080");
        ParkingLot parkingLot = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);

        String parkingTicket1 = parkingBoy.fetchTicketByCar(car1).getCarLicense();
        String parkingTicket2 = parkingBoy.fetchTicketByCar(car2).getCarLicense();

        assertThat(parkingTicket1, is("9527"));
        assertThat(parkingTicket2, is("8080"));
    }

    @Test
    public void should_not_fetch_car_if_ticket_invalid() {
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = new ParkingTicket("8080");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);

        Car fetchCar = parkingBoy.fetchCarByTickey(parkingTicket2);

        assertThat(systemOut(), is("Unrecognized parking ticket."));
        assertThat(fetchCar, nullValue());
    }

    @Test
    public void should_not_fetch_car_if_no_ticket() {
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = null;
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, 10, ticketMatchCar, new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);

        Car fetchCar = parkingBoy.fetchCarByTickey(parkingTicket2);

        assertThat(systemOut(), is("Please provide your parking ticket."));
        assertThat(fetchCar, nullValue());
    }

    @Test
    public void should_not_fetch_ticket_if_parkingLot_is_full() {
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);

        ParkingTicket parkingTicket = parkingBoy.fetchTicketByCar(car);

        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket, nullValue());
    }

    @Test
    public void should_park_second_parkingLot_if_first_parkingLot_is_full() {
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot1);
        parkingBoy.manageParkingLot(parkingLot2);

        String parkingTicket = parkingBoy.fetchTicketByCar(car).getCarLicense();

        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_fetch_ticket_give_car_to_smart_boy() {
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(11, 11, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot1);
        smartParkingBoy.manageParkingLot(parkingLot2);

        String parkingTicket = smartParkingBoy.fetchTicketByCar(car).getCarLicense();

        assertThat(parkingTicket, is("9527"));
        assertThat(parkingLot2.getCapacity(), is(11));
    }

    @Test
    public void should_not_fetch_ticket_give_car_to_smart_boy_when_parkinglot_is_all_full() {
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(0, 12, new HashMap<>(0), new HashMap<>(0));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot1);
        smartParkingBoy.manageParkingLot(parkingLot2);

        ParkingTicket parkingTicket = smartParkingBoy.fetchTicketByCar(car);

        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket, nullValue());
    }

    @Test
    public void should_fetch_ticket_give_car_to_super_smart_boy() {
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(8, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(9, 10, new HashMap<>(0), new HashMap<>(0));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot1);
        superSmartParkingBoy.manageParkingLot(parkingLot2);

        String parkingTicket = superSmartParkingBoy.fetchTicketByCar(car).getCarLicense();

        assertThat(parkingTicket, is("9527"));
        assertThat(parkingLot2.getCapacity(), is(8));
    }

    @Test
    public void should_not_fetch_ticket_give_car_to_super_smart_boy_when_parkinglot_is_all_full() {
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(0, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(0, 12, new HashMap<>(0), new HashMap<>(0));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot1);
        superSmartParkingBoy.manageParkingLot(parkingLot2);

        ParkingTicket parkingTicket = superSmartParkingBoy.fetchTicketByCar(car);

        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket, nullValue());
    }
}
