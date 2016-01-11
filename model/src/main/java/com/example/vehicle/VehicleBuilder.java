package com.example.vehicle;

import com.example.seller.Seller;

/**
 * Convenience builder - not strictly required for creating vehicles
 */
public abstract class VehicleBuilder {
    protected Vehicle vehicle;

    protected abstract Vehicle createVehicle(String make, String model, double price, Seller seller);

    public VehicleBuilder(String make, String model, double price, Seller seller) {
        vehicle = createVehicle(make, model, price, seller);
    }

    public Vehicle withYear(int year) {
        vehicle.setYear(year);
        return vehicle;
    }

    public VehicleBuilder withKmDriven(int kmDriven) {
        vehicle.setKmDriven(kmDriven);
        return this;
    }

    public VehicleBuilder withMaxSpeed(int maxSpeed) {
        vehicle.setMaxSpeed(maxSpeed);
        return this;
    }

    public VehicleBuilder withHorsePower(int horsePower) {
        vehicle.setHorsePower(horsePower);
        return this;
    }

    public VehicleBuilder withNumSeats(int numSeats) {
        vehicle.setNumSeats(numSeats);
        return this;
    }

    public Vehicle build() {
        return vehicle;
    }
}
