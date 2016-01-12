package com.example.rhansen.logic.presenters;

import com.example.SearchCriteria;
import com.example.query.Query;
import com.example.rhansen.logic.internal.utils.ThreadExecutor;
import com.example.vehicle.Vehicle;
import com.example.rhansen.logic.internal.services.VehicleMetadataService;
import com.example.rhansen.logic.internal.services.VehicleSearchService;
import com.example.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Handles the logic related to vehicle searches.
 */
public class SearchPresenter {

    //region View callback interfaces
    public interface NumMatchesListener extends ErrorListener {
        void onNumberOfMatchingVehiclesFound(int numMatches);
    }

    public interface SearchResultListener extends ErrorListener {
        void onSearchComplete(Iterable<Vehicle> vehicles);
    }
    //endregion

    static final int DEFAULT_PAGE = 0;
    static final int DEFAULT_PAGE_SIZE = 10;
    static final SearchCriteria DEFAULT_SEARCH_CRITERIA  = SearchCriteria.MAKE_MODEL_ASC;

    private VehicleSearchService searchService;
    private VehicleMetadataService metadataService;

    private Executor searchExecutor;
    private Executor numberOfMatchesExecutor;

    public SearchPresenter(VehicleSearchService searchService,
                           VehicleMetadataService metadataService) {

        this.searchService = searchService;
        this.metadataService = metadataService;

        //Important note - the ThreadExecutors are only included for demo purposes
        //The async methods where they are used, should not be called from a UI thread, or else!!
        setSearchExecutor(new ThreadExecutor());
        setNumberOfMatchesExecutor(new ThreadExecutor());
    }

    void setSearchExecutor(Executor searchExecutor) {
        this.searchExecutor = searchExecutor;
    }

    void setNumberOfMatchesExecutor(Executor numberOfMatchesExecutor) {
        this.numberOfMatchesExecutor = numberOfMatchesExecutor;
    }

    public Iterable<Vehicle> searchForVehicles(Query query) {
        return searchForVehicles(query, DEFAULT_SEARCH_CRITERIA);
    }

    public Iterable<Vehicle> searchForVehicles(Query query, SearchCriteria criteria) {
        return searchForVehicles(query, criteria, DEFAULT_PAGE);
    }

    public Iterable<Vehicle> searchForVehicles(Query query, SearchCriteria criteria, int page) {
        return searchService.getMatchingVehicles(query, criteria, page, DEFAULT_PAGE_SIZE);
    }

    public void searchForVehiclesAsync(final Query query, final SearchResultListener callback) {
        SearchTask searchTask = new SearchTask(query, callback);
        searchExecutor.execute(searchTask.getRunnable());
    }

    public int getNumberOfMatchingVehicles(Query query) {
        return searchService.getNumberOfMatchingVehicles(query);
    }

    public void getNumberOfMatchingVehiclesAsync(final Query query,
                                                 final NumMatchesListener callback) {

        NumberOfMatchesTask numMatchesTask = new NumberOfMatchesTask(query, callback);
        numberOfMatchesExecutor.execute(numMatchesTask.getRunnable());
    }

    public Iterable<String> getAvailableMakes() {
        return metadataService.getAvailableMakes();
    }

    public Iterable<String> getAvailableModels(String make) {
        return metadataService.getAvailableModels(make);
    }

    public VehicleType[] getAvailableTypes() {
        return metadataService.getAvailableTypes();
    }

    private class SearchTask {
        private Runnable runnable;

        private SearchTask(final Query query, final SearchResultListener callback) {
            runnable = new Runnable() {
                public void run() {
                    try {
                        callback.onSearchComplete(searchForVehicles(query));
                    } catch (Exception ex) {
                        callback.onError(ex);
                    }
                }
            };
        }

        private Runnable getRunnable() {
            return this.runnable;
        }
    }

    private class NumberOfMatchesTask {
        private Runnable runnable;

        private NumberOfMatchesTask(final Query query, final NumMatchesListener callback) {
            runnable = new Runnable() {
                public void run() {
                    try {
                        callback.onNumberOfMatchingVehiclesFound(getNumberOfMatchingVehicles(query));
                    } catch (Exception ex) {
                        callback.onError(ex);
                    }
                }
            };
        }

        private Runnable getRunnable() {
            return this.runnable;
        }
    }
}
