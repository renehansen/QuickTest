package com.example.seller;

public abstract class Seller {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Seller(String name) {
        this.setName(name);
    }
}
