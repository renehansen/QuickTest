package com.example.rhansen.logic.internal.services;

import com.example.vehicle.VehicleType;

/**
 * Manages metadata about vehicles
 */
public interface VehicleMetadataService {
    Iterable<String> getAvailableMakes();
    Iterable<String> getAvailableModels(String make);
    VehicleType[] getAvailableTypes();
}
