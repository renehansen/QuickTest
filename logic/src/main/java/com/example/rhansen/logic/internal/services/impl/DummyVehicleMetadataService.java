package com.example.rhansen.logic.internal.services.impl;

import com.example.rhansen.logic.internal.services.VehicleMetadataService;
import com.example.vehicle.Vehicle;
import com.example.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO RHA - tilføj tests
public class DummyVehicleMetadataService implements VehicleMetadataService {

    private static final Map<Vehicle.Make, List<Vehicle.Model>> makeModelMap = new HashMap<>();

    private static void initializeMakeModelMap() {
        makeModelMap.put(Vehicle.Make.AUDI, new ArrayList<Vehicle.Model>());
        makeModelMap.get(Vehicle.Make.AUDI).add(Vehicle.Model.AUDI_A1);
        makeModelMap.get(Vehicle.Make.AUDI).add(Vehicle.Model.AUDI_A3);
        makeModelMap.get(Vehicle.Make.AUDI).add(Vehicle.Model.AUDI_A4);
        makeModelMap.get(Vehicle.Make.AUDI).add(Vehicle.Model.AUDI_A5);
        makeModelMap.get(Vehicle.Make.AUDI).add(Vehicle.Model.AUDI_A6);

        makeModelMap.put(Vehicle.Make.BMW, new ArrayList<Vehicle.Model>());
        makeModelMap.get(Vehicle.Make.BMW).add(Vehicle.Model.BMW_1);
        makeModelMap.get(Vehicle.Make.BMW).add(Vehicle.Model.BMW_2);
        makeModelMap.get(Vehicle.Make.BMW).add(Vehicle.Model.BMW_3);
        makeModelMap.get(Vehicle.Make.BMW).add(Vehicle.Model.BMW_4);
        makeModelMap.get(Vehicle.Make.BMW).add(Vehicle.Model.BMW_5);

        makeModelMap.put(Vehicle.Make.SKODA, new ArrayList<Vehicle.Model>());
        makeModelMap.get(Vehicle.Make.SKODA).add(Vehicle.Model.SKODA_CITIGO);
        makeModelMap.get(Vehicle.Make.SKODA).add(Vehicle.Model.SKODA_FABIA);
        makeModelMap.get(Vehicle.Make.SKODA).add(Vehicle.Model.SKODA_RAPID);
        makeModelMap.get(Vehicle.Make.SKODA).add(Vehicle.Model.SKODA_OCTAVIA);
        makeModelMap.get(Vehicle.Make.SKODA).add(Vehicle.Model.SKODA_SUPERB);

        makeModelMap.put(Vehicle.Make.VW, new ArrayList<Vehicle.Model>());
        makeModelMap.get(Vehicle.Make.VW).add(Vehicle.Model.VW_UP);
        makeModelMap.get(Vehicle.Make.VW).add(Vehicle.Model.VW_POLO);
        makeModelMap.get(Vehicle.Make.VW).add(Vehicle.Model.VW_GOLF);
        makeModelMap.get(Vehicle.Make.VW).add(Vehicle.Model.VW_PASSAT);
    }

    static {
        initializeMakeModelMap();
    }

    @Override
    public Iterable<String> getAvailableMakes() {
        List<String> availableMakes = new ArrayList<>();
        for (Vehicle.Make make : makeModelMap.keySet()) {
            availableMakes.add(make.toString());
        }
        return availableMakes;
    }

    @Override
    public Iterable<String> getAvailableModels(String make) {
        List<String> availableModels = new ArrayList<>();

        Vehicle.Make selectedMake =  Vehicle.Make.fromName(make);
        if (selectedMake != null) {
            for (Vehicle.Model model : makeModelMap.get(selectedMake)) {
                availableModels.add(model.toString());
            }
        }
        return availableModels;
    }

    @Override
    public VehicleType[] getAvailableTypes() {
        return VehicleType.values();
    }
}
