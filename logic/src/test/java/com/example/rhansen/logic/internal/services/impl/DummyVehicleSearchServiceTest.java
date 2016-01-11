package com.example.rhansen.logic.internal.services.impl;

import com.example.SearchCriteria;
import com.example.query.Query;
import com.example.seller.PrivateSeller;
import com.example.seller.Seller;
import com.example.vehicle.Car;
import com.example.vehicle.Vehicle;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by rhansen on 10/01/2016.
 */
public class DummyVehicleSearchServiceTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    //region Default value tests
    @Test
    public void testGetMatchingVehicles_NoQuerySupplied_AllVehiclesReturned() throws Exception {
        //ARRANGE
        final int allVehicleSize = 5;
        List<Vehicle> inputVehicles = TestData.createRandomVehicles(allVehicleSize);
        DummyVehicleSearchService cut = new DummyVehicleSearchService(inputVehicles);

        Query query = null;
        SearchCriteria irrelevantCriteria = SearchCriteria.MAKE_MODEL_ASC;
        int irrelevantPage = 0;
        int irrelevantPageSize = 2;

        //ACT
        Iterable<Vehicle> resultVehicles = cut.getMatchingVehicles(
                query, irrelevantCriteria, irrelevantPage, irrelevantPageSize);

        //ASSERT
        assertThat(size(resultVehicles), is(equalTo(allVehicleSize)));
    }

    @Test
    public void testGetMatchingVehicles_NoSearchCriteriaSupplied_AllVehiclesReturned() throws Exception {
        Assert.fail();
    }

    @Test
    public void testGetMatchingVehicles_PageExceedsLength_AllVehiclesReturned() throws Exception {
        Assert.fail();
    }
    //endregion

    private static <T> int size(Iterable<T> elements) {
        int numElements = 0;
        for (T elem : elements) {
            numElements++;
        }
        return numElements;
    }

    @Test
    public void testGetNumberOfMatchingVehicles() throws Exception {

    }

    @Test
    public void testGetResultsPageSize() throws Exception {

    }

    private static class TestData {
        private static List<Vehicle> getFiveRandomVehicles() {
            PrivateSeller sellerRene = new PrivateSeller("Rene");
            Car upByRene = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_UP.toString(), 85000, sellerRene);
            Car poloByRene = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_POLO.toString(), 135000, sellerRene);

            return Arrays.<Vehicle>asList(upByRene, poloByRene);
        }

        private static List<Vehicle> createRandomVehicles(int numVehiclesToCreate) {
            List<Vehicle> result = new ArrayList<>();
            String dummyMake = "Dummy make";
            String dummyModel = "Dummy model";
            double dummyPrice = 149000;
            Seller dummySeller = new PrivateSeller("Dummy Seller");

            for (int i = 0; i < numVehiclesToCreate; i++) {
                result.add(new Car(dummyMake + i, dummyModel + i, dummyPrice, dummySeller));
            }
            return result;
        }
    }
}