package com.example.vehicle;

import com.example.seller.Seller;

/**
 * Der er ingen input validering af klasserne, da klasserne udelukkende er DTOer som ikke redigeres
 * på klienten.
 */
public abstract class Vehicle {

    enum Fuel { GASOLINE, DIESEL, ELECTRIC, HYBRID_GASOLINE, HYBRID_DIESEL }

    //region Simulation of make- and model values (normalt ville jeg kun lave i kode - men benytter også i "produktion" indtil videre
    public enum Make {
        AUDI("Audi"),
        BMW("BMW"),
        SKODA("Skoda"),
        VW("VW");

        private String name;

        Make(String name) {
            this.name = name;
        }

        public static Make fromName(String name) {
            for (Make curMake : Make.values()) {
                if (curMake.name.equalsIgnoreCase(name)) {
                    return curMake;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public enum Model {
        AUDI_A1("A1"),
        AUDI_A3("A3"),
        AUDI_A4("A4"),
        AUDI_A5("A5"),
        AUDI_A6("A6"),

        BMW_1("1-Serie"),
        BMW_2("2-Serie"),
        BMW_3("3-Serie"),
        BMW_4("4-Serie"),
        BMW_5("5-Serie"),

        SKODA_CITIGO("Citigo"),
        SKODA_FABIA("Fabia"),
        SKODA_RAPID("Rapid"),
        SKODA_OCTAVIA("Octavia"),
        SKODA_SUPERB("Superb"),

        VW_UP("Up"),
        VW_POLO("Polo"),
        VW_GOLF("Golf"),
        VW_PASSAT("Passat");

        private String name;

        Model(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
    //endregion

    private Seller seller;
    private String make;
    private String model;
    private double price;
    private int year;
    private int kmDriven;
    private int maxSpeed;
    private int horsePower;
    private int numSeats;

    protected Vehicle(String make, String model, double price, Seller seller) {
        this.make = make;
        this.model = model;
        this.price = price;
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getNumSeats() {
        return numSeats;
    }
    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }
}
