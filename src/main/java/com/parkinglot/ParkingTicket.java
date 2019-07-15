package com.parkinglot;

public class ParkingTicket {

    private String carLicense;

    public ParkingTicket(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }
}
