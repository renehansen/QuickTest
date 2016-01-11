package com.example.rhansen.logic.presenters;

public interface NumMatchesListener extends ErrorListener {
    void onNumberOfMatchingVehiclesFound(int numMatches);
}
