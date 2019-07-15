package com.parkinglot;

public class Car {
    private String carLicense;

    public Car(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }
}
