package com.example.rhansen.logic.presenters;

import com.example.vehicle.Vehicle;

public interface SearchResultListener extends ErrorListener {
    void onSearchComplete(Iterable<Vehicle> vehicles);
}
