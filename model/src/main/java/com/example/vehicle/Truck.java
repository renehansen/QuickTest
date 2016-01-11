package com.example.vehicle;

import com.example.seller.Seller;

public class Truck extends Vehicle {
    private int numWheels;

    public Truck(String make, String model, double price, Seller seller) {
        super(make, model, price, seller);
    }

    public int getNumWheels() {
        return numWheels;
    }

    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    public static class Builder extends VehicleBuilder {

        public Builder(String make, String model, double price, Seller seller) {
            super(make, model, price, seller);
        }

        @Override
        protected Vehicle createVehicle(String make, String model, double price, Seller seller) {
            return new Truck(make, model, price, seller);
        }

        public VehicleBuilder withNumWheels(int numWheels) {
            ((Truck)vehicle).setNumWheels(numWheels);
            return this;
        }
    }
}
