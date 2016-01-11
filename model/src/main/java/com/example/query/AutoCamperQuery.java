package com.example.query;

import com.example.vehicle.AutoCamper;
import com.example.vehicle.Vehicle;
import com.example.vehicle.utils.MyOptional;

//Eksempel på udvidelse af query
public class AutoCamperQuery extends Query {

    public int getNumBedsMin() {
        return 0;
    }
    public int getNumBedsMax() {
        return 20;
    }

    private MyOptional<Integer> numBedsFrom;
    private MyOptional<Integer> numBedsTo;
    private MyOptional<Boolean> hasKitchen;

    public MyOptional<Integer> getNumBedsFrom() {
        return numBedsFrom;
    }

    public void setNumBedsFrom(MyOptional<Integer> numBedsFrom) {
        this.numBedsFrom = getValidFromValue(
                numBedsFrom, this.numBedsTo, getNumBedsMin(), getNumBedsMax());
    }

    public MyOptional<Integer> getNumBedsTo() {
        return numBedsTo;
    }

    public void setNumBedsTo(MyOptional<Integer> numBedsTo) {
        this.numBedsTo = getValidToValue(
                numBedsTo, this.numBedsFrom, getNumBedsMin(), getNumBedsMax());
    }

    public MyOptional<Boolean> getHasKitchen() {
        return hasKitchen;
    }

    public void setHasKitchen(MyOptional<Boolean> hasKitchen) {
        this.hasKitchen = hasKitchen;
    }

    @Override
    public boolean matches(Vehicle vehicle) {
        if (!(vehicle instanceof AutoCamper)) {
            return false;
        }
        AutoCamper autoCamper = (AutoCamper)vehicle;
        final boolean numBedsInRange =
                RangeUtils.isInRange(autoCamper.getNumBeds(), getNumBedsFrom(), getNumBedsTo());
        final boolean hasKitchenMatches =
                !getHasKitchen().isPresent() || getHasKitchen().get() == autoCamper.hasKitchen();

        return numBedsInRange && hasKitchenMatches && super.matches(autoCamper);
    }
}
