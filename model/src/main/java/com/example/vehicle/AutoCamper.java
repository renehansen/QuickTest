package com.example.vehicle;

import com.example.seller.Seller;

public class AutoCamper extends Vehicle {
    private int numBeds;
    private boolean hasKitchen;

    protected AutoCamper(String make, String model, double price, Seller seller) {
        super(make, model, price, seller);
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public boolean hasKitchen() {
        return hasKitchen;
    }

    public void setHasKitchen(boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
    }
}
