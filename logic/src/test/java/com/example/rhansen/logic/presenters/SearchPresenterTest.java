package com.example.rhansen.logic.presenters;

import com.example.SearchCriteria;
import com.example.query.Query;
import com.example.rhansen.logic.internal.services.VehicleMetadataService;
import com.example.rhansen.logic.internal.services.VehicleSearchService;
import com.example.rhansen.logic.internal.utils.SynchronousExecutor;
import com.example.vehicle.Vehicle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchPresenterTest {

    @Mock
    VehicleSearchService searchService;
    @Mock
    VehicleMetadataService metadataService;
    //Class under test
    SearchPresenter cut;

    @Before
    public void setUp() throws Exception {
        searchService = mock(VehicleSearchService.class);
        metadataService = mock(VehicleMetadataService.class);
        cut = new SearchPresenter(searchService, metadataService);
        //Important! Ensure synchronous invocation of tasks
        cut.setSearchExecutor(new SynchronousExecutor());
    }

    @Test
    public void testSearchForVehicles_QueryOnlySupplied_UsesDefaultValues() throws Exception {
        //ARRANGE
        Query query = Query.empty();

        //ACT
        cut.searchForVehicles(query);

        //ASSERT
        verify(searchService).getMatchingVehicles(query,
                SearchPresenter.DEFAULT_SEARCH_CRITERIA,
                SearchPresenter.DEFAULT_PAGE,
                SearchPresenter.DEFAULT_PAGE_SIZE);
    }

    @Test
    public void testSearchForVehicles_QueryAndCriteriaSupplied_UsesDefaultValues() throws Exception {
        //ARRANGE
        Query query = Query.empty();
        SearchCriteria criteria = SearchCriteria.PRICE_ASC;

        //ACT
        cut.searchForVehicles(query, criteria);

        //ASSERT
        verify(searchService).getMatchingVehicles(query, criteria,
                SearchPresenter.DEFAULT_PAGE,
                SearchPresenter.DEFAULT_PAGE_SIZE);
    }

    @Test
    public void testSearchForVehicles_QueryAndCriteriaAndPageSupplied_UsesDefaultPageSize() throws Exception {
        //ARRANGE
        Query query = Query.empty();
        SearchCriteria criteria = SearchCriteria.PRICE_ASC;
        int page = 1;

        //ACT
        cut.searchForVehicles(query, criteria, page);

        //ASSERT
        verify(searchService).getMatchingVehicles(query, criteria, page,
                SearchPresenter.DEFAULT_PAGE_SIZE);
    }

    @Test
    public void testSearchForVehiclesAsync_OnSuccess_SuccessCallbackIsCalled() throws Exception {
        //ARRANGE
        Query irrelevantQuery = Query.empty();
        SearchResultListener callback = mock(SearchResultListener.class);

        //ACT
        cut.searchForVehiclesAsync(irrelevantQuery, callback);

        //ASSERT
        verify(callback).onSearchComplete(Matchers.<Iterable<Vehicle>>any());
    }

    @Test
    public void testSearchForVehiclesAsync_OnError_ErrorCallbackIsCalled() throws Exception {
        //ARRANGE
        Query irrelevantQuery = Query.empty();
        SearchResultListener callback = mock(SearchResultListener.class);
        //Ensure service throws exception. TODO: Consider throwing custom exception instead
        RuntimeException expectedException = new RuntimeException();
        when(searchService.getMatchingVehicles(
                eq(irrelevantQuery), any(SearchCriteria.class), anyInt(), anyInt()))
                .thenThrow(expectedException);

        //ACT
        cut.searchForVehiclesAsync(irrelevantQuery, callback);

        //ASSERT
        verify(callback).onError(expectedException);
    }

    //TODO - de resterende tests er mere af det samme, så det må blive en anden god gang...
    @Test
    public void testGetNumberOfMatchingVehiclesAsync_OnSuccess_SuccessCallbackIsCalled() throws Exception {

    }

    @Test
    public void testGetAvailableMakes() throws Exception {

    }

    @Test
    public void testGetAvailableModels() throws Exception {

    }

    @Test
    public void testGetAvailableTypes() throws Exception {

    }
}