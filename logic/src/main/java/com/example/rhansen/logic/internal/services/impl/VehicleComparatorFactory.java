package com.example.rhansen.logic.internal.services.impl;

import com.example.SearchCriteria;
import com.example.vehicle.Vehicle;

import java.util.Comparator;

public class VehicleComparatorFactory {
    protected enum Order { ASC, DESC }

    /**
     * Creates an appropriate vehicle comparator based on the supplied search criteria
     * @param criteria The search criteria
     * @return The resulting comparator
     */
    public Comparator<Vehicle> createFrom(SearchCriteria criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("A search criteria must be supplied");
        }

        switch (criteria) {
            case MAKE_MODEL_ASC:
                return createMakeModelComparator(Order.ASC);
            case MAKE_MODEL_DESC:
                return createMakeModelComparator(Order.DESC);
            case PRICE_ASC:
                return createPriceComparator(Order.ASC);
            case PRICE_DESC:
                return createPriceComparator(Order.DESC);
            default:
                throw new IllegalArgumentException("Unknown search criteria: " + criteria);
        }
    }

    protected Comparator<Vehicle> createMakeModelComparator(final Order order) {
        return new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle left, Vehicle right) {
                int ascOrder = compareMakeAndModelAsc(left, right);
                return order == Order.ASC ? ascOrder : switchSign(ascOrder);
            }
        };
    }

    protected Comparator<Vehicle> createPriceComparator(final Order order) {
        return new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle left, Vehicle right) {
                int ascOrder = comparePriceAsc(left, right);
                return order == Order.ASC ? ascOrder : switchSign(ascOrder);
            }
        };
    }

    protected int compareMakeAndModelAsc(Vehicle left, Vehicle right) {
        final int makeComparison = left.getMake().compareTo(right.getMake());
        return makeComparison != 0
                ? makeComparison
                : left.getModel().compareTo(right.getModel());
    }

    protected int comparePriceAsc(Vehicle left, Vehicle right) {
        if (left.getPrice() < right.getPrice()) {
            return -1;
        }
        else if (left.getPrice() > right.getPrice()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    private static int switchSign(int value) {
        return value * -1;
    }
}
