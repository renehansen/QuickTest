package com.example.rhansen.logic.presenters;

import com.example.rhansen.logic.internal.services.impl.DummyVehicleMetadataService;
import com.example.rhansen.logic.internal.services.impl.DummyVehicleSearchService;

/**
 * Handles the configuration for presenters (so the views don't have to).
 * This simplifies view creation / prevents information leak and ensures consistent service usage
 */
public class PresenterManager {
    /**
     * The mode determines how presenters are set up.
     */
    public enum ConfigurationMode { DUMMY }

    private static SearchPresenter searchPresenter;

    public static void configurePresenters(ConfigurationMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("The configuration mode must not be null");
        }
        configureLoginPresenter(mode);
        //configure the "remaining" presenters...
    }

    private static void configureLoginPresenter(ConfigurationMode mode) {
        switch (mode) {
            //TODO RHA - Indkapsl evt. IOC containere
            case DUMMY:
                searchPresenter = new SearchPresenter(
                        new DummyVehicleSearchService(
                                DummyVehicleSearchService.createDummyVehicles()),
                        new DummyVehicleMetadataService());
        }
    }

    public static SearchPresenter getSearchPresenter(){
        return searchPresenter;
    }
}
