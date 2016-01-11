package com.example.rhansen.logic.internal.services;

import com.android.internal.util.Predicate;
import com.example.SearchCriteria;
import com.example.query.Query;
import com.example.rhansen.logic.presenters.SearchPresenter;
import com.example.vehicle.Vehicle;

/**
 * Manages vehicle searches for BILBASEN Mini
 */
public interface VehicleSearchService {
    /**
     * Returns a subset of the vehicles that matches the specified query and search criteria. The pageSize determines the result size while the page determines subset's starting point.
     *
     * @param query Specifies how vehicles are filtered. If no query is supplied, no filtering is done.
     * @param criteria Specifies how vehicles are ordered. If no criteria is specified, no particular order is imposed.
     * @param page Specifies the starting point for the subset of vehicles that is returned. If the starting point is after the last element of the result, all vehicles are returned.
     * @param pageSize Specifies how many vehicles are (at most) returned
     * @return The matching vehicle subset.
     */
    Iterable<Vehicle> getMatchingVehicles(Query query, SearchCriteria criteria, int page, int pageSize);

    int getNumberOfMatchingVehicles(Query query);
}

