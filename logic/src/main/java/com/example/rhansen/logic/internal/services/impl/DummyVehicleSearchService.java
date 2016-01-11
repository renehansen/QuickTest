package com.example.rhansen.logic.internal.services.impl;

import android.location.Criteria;

import com.example.SearchCriteria;
import com.example.query.Query;
import com.example.seller.PrivateSeller;
import com.example.seller.RetailSeller;
import com.example.vehicle.Car;
import com.example.vehicle.Truck;
import com.example.vehicle.Vehicle;
import com.example.rhansen.logic.internal.services.VehicleSearchService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Uses hardcoded dummy vehicles as the underlying data source
 */
public class DummyVehicleSearchService implements VehicleSearchService {

    public static List<Vehicle> createDummyVehicles() {
        PrivateSeller sellerRene = new PrivateSeller("Rene");
        PrivateSeller sellerMette = new PrivateSeller("Mette");
        PrivateSeller sellerTrine = new PrivateSeller("Trine");
        PrivateSeller sellerMads = new PrivateSeller("Mads");
        PrivateSeller sellerRikke = new PrivateSeller("Rikke");
        PrivateSeller sellerHanne = new PrivateSeller("Hanne");
        PrivateSeller sellerAllan = new PrivateSeller("Allan");
        PrivateSeller sellerPusser = new PrivateSeller("Pusser");
        PrivateSeller sellerMorten = new PrivateSeller("Morten");
        PrivateSeller sellerRagna = new PrivateSeller("Ragna");
        PrivateSeller sellerBo = new PrivateSeller("Bo");
        PrivateSeller sellerHelle = new PrivateSeller("Helle");
        RetailSeller jac = new RetailSeller("JAC");

        //Vehicle.Make.VW.toString(), Vehicle.Model.VW_GOLF.toString(), 219000, sellerRene
        Car upByRene = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_UP.toString(), 85000, sellerRene);
        Car poloByRene = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_POLO.toString(), 135000, sellerRene);
        Car golfByRene = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_GOLF.toString(), 215000, sellerRene);

        Car upByMette = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_UP.toString(), 209000, sellerMette);
        Car poloByMette = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_POLO.toString(), 140000, sellerMette);
        Car golfByMette = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_GOLF.toString(), 209000, sellerMette);

        Car citigoByTrine = new Car(Vehicle.Make.SKODA.toString(), Vehicle.Model.SKODA_CITIGO.toString(), 79000, sellerTrine);
        Car fabiaByTrine = new Car(Vehicle.Make.SKODA.toString(), Vehicle.Model.SKODA_FABIA.toString(), 24000, sellerTrine);
        Car rapidByTrine = new Car(Vehicle.Make.SKODA.toString(), Vehicle.Model.SKODA_RAPID.toString(), 167000, sellerTrine);
        Car octaviaByTrine = new Car(Vehicle.Make.SKODA.toString(), Vehicle.Model.SKODA_OCTAVIA.toString(), 300000, sellerTrine);
        Car SuperbByTrine = new Car(Vehicle.Make.SKODA.toString(), Vehicle.Model.SKODA_SUPERB.toString(), 28000, sellerTrine);

        Car a1ByMads = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A1.toString(), 450000, sellerMads);
        Car a3ByMads = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A3.toString(), 199000, sellerMads);
        Car a4ByMads = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A4.toString(), 12000, sellerMads);
        Car a5ByMads = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A5.toString(), 788000, sellerMads);
        Car a6ByMads = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A6.toString(), 499000, sellerMads);

        Car serie1ByRikke = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_1.toString(), 9000, sellerRikke);
        Car serie2ByRikke = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_2.toString(), 295000, sellerRikke);
        Car serie3ByRikke = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_3.toString(), 300000, sellerRikke);
        Car serie4ByRikke = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_4.toString(), 166000, sellerRikke);
        Car serie5ByRikke = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_5.toString(), 78000, sellerRikke);

        Car upByHanne = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_UP.toString(), 90000, sellerHanne);
        Car poloByHanne = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_POLO.toString(), 944000, sellerHanne);
        Car golfByHanne= new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_GOLF.toString(), 288000, sellerHanne);
        Car passatByHanne = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_PASSAT.toString(), 799000, sellerHanne);

        Car a1ByAllan = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A1.toString(), 70000, sellerAllan);
        Car a3ByAllan = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A3.toString(), 800000, sellerAllan);
        Car a4ByAllan = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A4.toString(), 38000, sellerAllan);
        Car a5ByAllan = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A5.toString(), 25000, sellerAllan);
        Car a6ByAllan = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A6.toString(), 588000, sellerAllan);

        Car citigoByPusser = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.SKODA_CITIGO.toString(), 56000, sellerPusser);
        Car fabiaByPusser = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.SKODA_FABIA.toString(), 570000, sellerPusser);
        Car rapidByPusser = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.SKODA_RAPID.toString(), 355000, sellerPusser);
        Car octaviaByPusser = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.SKODA_OCTAVIA.toString(), 123000, sellerPusser);
        Car superbByPusser = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.SKODA_SUPERB.toString(), 144000, sellerPusser);

        Car serie1ByMorten = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_1.toString(), 98000, sellerMorten);
        Car serie2ByMorten = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_2.toString(), 166000, sellerMorten);
        Car serie3ByMorten = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_3.toString(), 236000, sellerMorten);
        Car serie4ByMorten = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_4.toString(), 411000, sellerMorten);
        Car serie5ByMorten = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_5.toString(), 88000, sellerMorten);

        Car a1ByRagna = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A1.toString(), 540000, sellerRagna);
        Car a3ByRagna = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A3.toString(), 340000, sellerRagna);
        Car a4ByRagna = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A4.toString(), 65000, sellerRagna);
        Car a5ByRagna = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A5.toString(), 77000, sellerRagna);
        Car a6ByRagna = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A6.toString(), 750000, sellerRagna);

        Car serie1ByBo = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_1.toString(), 430000, sellerBo);
        Car serie2ByBo = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_2.toString(), 421000, sellerBo);
        Car serie3ByBo = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_3.toString(), 780000, sellerBo);
        Car serie4ByBo = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_4.toString(), 320000, sellerBo);
        Car serie5ByBo = new Car(Vehicle.Make.BMW.toString(), Vehicle.Model.BMW_5.toString(), 650000, sellerBo);

        Car a1ByHelle = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A1.toString(), 210000, sellerHelle);
        Car a3ByHelle = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A3.toString(), 980000, sellerHelle);
        Car a4ByHelle = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A4.toString(), 411000, sellerHelle);
        Car a5ByHelle = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A5.toString(), 27000, sellerHelle);
        Car a6ByHelle = new Car(Vehicle.Make.AUDI.toString(), Vehicle.Model.AUDI_A6.toString(), 720000, sellerHelle);

        Car upByJac = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_UP.toString(), 23000, jac);
        Car poloByJac = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_POLO.toString(), 760000, jac);
        Car golfByJac = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_GOLF.toString(), 155000, jac);
        Car passatByJac = new Car(Vehicle.Make.VW.toString(), Vehicle.Model.VW_PASSAT.toString(), 92000, jac);

        //TODO RHA - tilføj flere køretøjer...

        Vehicle eighteenWheeler = new Truck.Builder(
                Vehicle.Make.VW.toString(), Vehicle.Model.VW_GOLF.toString(), 419000, sellerRene)
                .withNumWheels(18).build();

        return Arrays.<Vehicle>asList(upByRene, poloByRene, golfByRene, upByMette, poloByMette,
                golfByMette, citigoByTrine, eighteenWheeler, fabiaByTrine, rapidByTrine, octaviaByTrine, SuperbByTrine,
                a1ByMads, a3ByMads, a4ByMads, a5ByMads, a6ByMads, serie1ByRikke, serie2ByRikke, serie3ByRikke, serie4ByRikke, serie5ByRikke,
                upByHanne ,poloByHanne ,golfByHanne ,passatByHanne ,a1ByAllan ,a3ByAllan , a4ByAllan, a5ByAllan,a6ByAllan, citigoByPusser,
                fabiaByPusser, rapidByPusser, octaviaByPusser, superbByPusser, serie1ByMorten, serie2ByMorten, serie3ByMorten, serie4ByMorten,
                serie5ByMorten, a1ByRagna, a3ByRagna, a4ByRagna, a5ByRagna, a6ByRagna, serie1ByBo, serie2ByBo, serie3ByBo, serie4ByBo, serie5ByBo,
                a1ByHelle, a3ByHelle, a4ByHelle, a5ByHelle, a6ByHelle, upByJac, poloByJac, golfByJac, passatByJac);
    }

    private List<Vehicle> vehicles;

    public DummyVehicleSearchService(List<Vehicle> dummyData) {
        this.vehicles = dummyData;
    }

    //TODO RHA: Man bør bruge DI, men pga. tidspres...
    private static VehicleComparatorFactory vehicleComparatorFactory = new VehicleComparatorFactory();
    private static final Query defaultQuery = Query.empty();
    private static final SearchCriteria defaultSearhCriteria = SearchCriteria.MAKE_MODEL_ASC;

    @Override
    public Iterable<Vehicle> getMatchingVehicles(Query query, SearchCriteria criteria,
                                                 int page, int pageSize) {
        if (query == null) {
            query = defaultQuery;
        }
        if (criteria == null) {
            criteria = defaultSearhCriteria;
        }

        List<Vehicle> result = subsetOf(matchingVehicles(query), page, pageSize);
        orderVehicles(result, criteria);
        return result;
    }

    private List<Vehicle> matchingVehicles(Query query) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (query.matches(vehicle)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    private List<Vehicle> subsetOf(List<Vehicle> vehicles, int page, int pageSize) {
        if (vehicles.isEmpty()) {
            return vehicles;
        }
        final int listEnd = vehicles.size() - 1;

        int start = page == 0 ? page : page * pageSize - 1;
        if (start > listEnd) {
            return vehicles;
        }

        int end = start + pageSize;
        if (end > listEnd) {
            end = listEnd;
        }

        return vehicles.subList(start, end);
    }

    private void orderVehicles(List<Vehicle> vehicles, SearchCriteria criteria) {
        Collections.sort(vehicles, vehicleComparatorFactory.createFrom(criteria));
    }

    @Override
    public int getNumberOfMatchingVehicles(Query query) {
        int numMatches = 0;
        for (Vehicle vehicle : vehicles) {
            if (query.matches(vehicle)) {
                numMatches++;
            }
        }
        return numMatches;
    }
}
