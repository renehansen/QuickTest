package com.example.rhansen.quicktest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.query.Query;
import com.example.rhansen.logic.presenters.NumMatchesListener;
import com.example.rhansen.logic.presenters.PresenterManager;
import com.example.rhansen.logic.presenters.SearchPresenter;
import com.example.vehicle.VehicleType;
import com.example.vehicle.utils.MyOptional;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QueryBuilderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QueryBuilderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryBuilderFragment extends Fragment implements AdapterView.OnItemClickListener {

    //region Android boilerplate code
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //TODO - put back in - HVIS vi får behov for at kalde callback...
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Factory metode til at skabe nye instanser så snart vi har behov for at sende input til fragment
     */
    public static QueryBuilderFragment newInstance() {
        QueryBuilderFragment fragment = new QueryBuilderFragment();
        /* Fremgangsmåde - vi bruger dog bare en no-args constructor
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        return fragment;
    }

    public QueryBuilderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    //endregion

    private ListView queryChoicesListView;
    private Button searchButton;

    int selectedCategoryIdx = 0;
    int selectedMakeIdx = 0;
    int selectedModelIdx = 0;

    private Query searchQuery = new Query();
    private SearchPresenter searchPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_query_builder, container, false);
        initializeUI(rootView);

        PresenterManager.configurePresenters(PresenterManager.ConfigurationMode.DUMMY);
        searchPresenter = PresenterManager.getSearchPresenter();

        return rootView;
    }

    private void initializeUI(View rootView) {
        searchButton = (Button)rootView.findViewById(R.id.main_searchbutton);
        queryChoicesListView = (ListView)rootView.findViewById(R.id.menu_list);
        queryChoicesListView.setOnItemClickListener(this);
        queryChoicesListView.setAdapter(new ArrayAdapter(
                getActivity(), R.layout.menu_item, R.id.textview_menu_item, createQueryChoices()));
    }

    enum QueryChoice {
        VEHICLE_TYPE(0), MAKE(1), MODEL(2), PRICE(3), YEAR(4), KM_DRIVEN(5), FUEL(6), FURTHER(7);

        private int intValue;
        QueryChoice(int intValue) {
            this.intValue = intValue;
        }

        static QueryChoice from(int value) {
            for (QueryChoice choice : values()) {
                if (value == choice.getIntValue()) {
                    return choice;
                }
            }
            return null;
        }

        int getIntValue()  {
            return this.intValue;
        }
    }

    private String[] createQueryChoices() {
        String[] queryChoices = new String[QueryChoice.values().length];
        queryChoices[QueryChoice.VEHICLE_TYPE.getIntValue()] = getString(R.string.vehicle_type);
        queryChoices[QueryChoice.MAKE.getIntValue()] = getString(R.string.make);
        queryChoices[QueryChoice.MODEL.getIntValue()] = getString(R.string.model);
        queryChoices[QueryChoice.PRICE.getIntValue()] = getString(R.string.price);
        queryChoices[QueryChoice.YEAR.getIntValue()] = getString(R.string.year);
        queryChoices[QueryChoice.KM_DRIVEN.getIntValue()] = getString(R.string.km_driven);
        queryChoices[QueryChoice.FUEL.getIntValue()] = getString(R.string.fuel);
        queryChoices[QueryChoice.FURTHER.getIntValue()] = getString(R.string.further);

        checkAllItemsHaveBeenInitialized(queryChoices);
        return queryChoices;
    }

    private static <T> void checkAllItemsHaveBeenInitialized(T[] items) {
        final String errorMsg = "Item at index %d has not been initialized";
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                //TODO: Lav og smid ConfigurationException istedet
                throw new RuntimeException(String.format(errorMsg, i));
            }
        }
    }

    private void doSearch() {
        final String resultPrefix = "Antal resultater: ";
        searchButton.setText(resultPrefix + searchPresenter.getNumberOfMatchingVehicles(searchQuery));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        QueryChoice choice = QueryChoice.from(position);
        if (choice == null) {
            throw new IllegalArgumentException("Missing query choice for position: " + position);
        }

        switch (choice) {
            case VEHICLE_TYPE:
                showChoiceDialogForVehicleType();
                break;
            case MAKE:
                showChoiceDialogForMake();
                break;
            case MODEL:
                showChoiceDialogForModel();
                break;
            case PRICE:
                showChoiceDialogForPrice();
                break;
            case YEAR:
                break;
            case KM_DRIVEN:
                break;
            default:
//TODO RHA - PUT BACK IN:                throw new IllegalArgumentException("Unknown query choice for position " + position);

        }
    }

    private void showChoiceDialogForVehicleType() {
        final String[] categories = translate(searchPresenter.getAvailableTypes());

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.vehicle_type)
                .setNegativeButton(R.string.fortryd, null)
                .setSingleChoiceItems(categories, selectedCategoryIdx,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int position) {
                                selectedCategoryIdx = position;
                                updateDisplayValue(QueryChoice.VEHICLE_TYPE, categories[position]);
                                doSearch();
                                dialog.dismiss();
                            }
                        }
                ).show();
    }

    private void showChoiceDialogForMake() {
        final String[] availableMakes = toArray(searchPresenter.getAvailableMakes());

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.make)
                .setNegativeButton(R.string.fortryd, null)
                .setSingleChoiceItems(availableMakes, selectedMakeIdx,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int position) {
                                if (selectedMakeIdx != position) {
                                    searchQuery.setModel(MyOptional.<String>empty());
                                    clearDisplayValue(QueryChoice.MODEL);
                                }
                                searchQuery.setMake(MyOptional.of(availableMakes[position]));
                                selectedMakeIdx = position;
                                updateDisplayValue(QueryChoice.MAKE, availableMakes[position]);
                                doSearch();
                                dialog.dismiss();
                            }
                        }
                ).show();
    }

    private void showChoiceDialogForModel() {
        String currentMake = searchQuery.getMake().get();
        final String[] availableModels = toArray(searchPresenter.getAvailableModels(currentMake));

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.model)
                .setNegativeButton(R.string.fortryd, null)
                .setSingleChoiceItems(availableModels, selectedModelIdx,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {
                                searchQuery.setModel(MyOptional.of(availableModels[position]));
                                selectedModelIdx = position;
                                updateDisplayValue(QueryChoice.MODEL, availableModels[position]);
                                doSearch();
                                dialog.dismiss();
                            }
                        }
                ).show();
    }

    private void showChoiceDialogForPrice() {
        final Dialog myDialog= new Dialog(getActivity());
        myDialog.setTitle("Pris");
        myDialog.setContentView(R.layout.numberpicker_dialog_layout);

        Button okButton = (Button)myDialog.findViewById(R.id.button_ok);
        Button cancelButton = (Button)myDialog.findViewById(R.id.button_cancel);

        final String[] displayedPrices = getDisplayedPrices();
        final NumberPicker minPricePicker = (NumberPicker)myDialog.findViewById(R.id.numberpicker_from);
        final NumberPicker maxPricePicker = (NumberPicker)myDialog.findViewById(R.id.numberpicker_to);
        initializePicker(minPricePicker, displayedPrices);
        initializePicker(maxPricePicker, displayedPrices);

        synchronizeListeners(minPricePicker, maxPricePicker);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayPrice selectedMin = DisplayPrice.values()[minPricePicker.getValue()];
                DisplayPrice selectedMax = DisplayPrice.values()[maxPricePicker.getValue()];
                setQueryPriceFrom(selectedMin);
                setQueryPriceTo(selectedMax);

                myDialog.dismiss();
                String rangeDisplay = selectedMin.displayValue + " - " + selectedMax.displayValue;
                updateDisplayValue(QueryChoice.PRICE, rangeDisplay);
                doSearch();
            }

            private void setQueryPriceFrom(DisplayPrice selectedMin) {
                if (selectedMin.displayValue.equalsIgnoreCase("Alle")) {
                    searchQuery.setPriceFrom(MyOptional.<Double>empty());
                }
                else {
                    searchQuery.setPriceFrom(MyOptional.of(selectedMin.moneyValue));
                }
            }

            private void setQueryPriceTo(DisplayPrice selectedMax) {
                if (selectedMax.displayValue.equalsIgnoreCase("Alle")) {
                    searchQuery.setPriceTo(MyOptional.<Double>empty());
                }
                else {
                    searchQuery.setPriceTo(MyOptional.of(selectedMax.moneyValue));
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }

    private void synchronizeListeners(final NumberPicker minPicker, final NumberPicker maxPicker) {
        minPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (minPicker.getValue() >= maxPicker.getValue()) {
                    maxPicker.setValue(minPicker.getValue() + 1);
                }

            }
        });

        maxPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (minPicker.getValue() >= maxPicker.getValue()) {
                    minPicker.setValue(maxPicker.getValue() - 1);
                }
            }
        });
    }

    @NonNull
    private String[] getDisplayedPrices() {
        int tempPos = 0;
        final String[] displayedPriceVals = new String[DisplayPrice.values().length];
        for (DisplayPrice d: DisplayPrice.values()) {
            displayedPriceVals[tempPos] = d.displayValue;
            tempPos++;
        }
        return displayedPriceVals;
    }

    private void initializePicker(NumberPicker picker, String[] displayedValues) {
        picker.setDisplayedValues(displayedValues);
        picker.setMinValue(0);
        picker.setMaxValue(displayedValues.length - 1);
        picker.setWrapSelectorWheel(false);
    }

    private void updateDisplayValue(QueryChoice choice, String value){
        View v = queryChoicesListView.getChildAt(choice.intValue);
        TextView selectedItemToShow = (TextView) v.findViewById(R.id.textview_menu_picked);
        selectedItemToShow.setText(value);
    }

    private void clearDisplayValue(QueryChoice choice){
        View v = queryChoicesListView.getChildAt(choice.intValue);

        TextView selectedItemToShow = (TextView) v.findViewById(R.id.textview_menu_picked);
        selectedItemToShow.setText("");
    }

    private String[] translate(VehicleType[] vehicleTypes) {
        String[] result = new String[vehicleTypes.length];
        for (int i = 0; i < vehicleTypes.length; i++) {
            switch (vehicleTypes[i]) {
                case CAR:
                    result[i] = getString(R.string.vehicle_type_car);
                    break;
                case VAN:
                    result[i] = getString(R.string.vehicle_type_van);
                    break;
                case BUS:
                    result[i] = getString(R.string.vehicle_type_bus);
                    break;
                case TRUCK:
                    result[i] = getString(R.string.vehicle_type_truck);
                    break;
                case AUTOCAMPER:
                    result[i] = getString(R.string.vehicle_type_autocamper);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown vehicle type: " + vehicleTypes[i]);
            }
        }
        return result;
    }

    private static String[] toArray(Iterable<String> iterable) {
        List<String> tempList = new ArrayList<>();
        for (String item : iterable) {
            tempList.add(item);
        }
        String[] result = new String[tempList.size()];
        return tempList.toArray(result);
    }

    //Værdierne burde nok have været udregnet istedet, men tiden går...
    enum DisplayPrice {
        NONE(null, "Alle"), ONE((double) 1, "1 kr"), K10((double) 10000, "10.000 kr"), K25((double) 25000, "25.000 kr"), K50((double) 50000, "50.000 kr"),
        K75((double) 75000, "75.000 kr"), K100((double) 100000, "100.000 kr"), K125((double) 125000, "125.000 kr"), K150((double) 150000, "150.000 kr"),
        K175((double) 175000, "175.000 kr"), K200((double) 200000, "200.000 kr"), K250((double) 250000, "250.000 kr"), K300((double) 300000, "300.000 kr"),
        K400((double) 400000, "400.000 kr"), K500((double) 500000, "500.000 kr"), K600((double) 600000, "600.000 kr"), K750((double) 750000, "750.000 kr"),
        K1000((double) 1000000, "1.000.000 kr");

        private Double moneyValue;
        private String displayValue;

        DisplayPrice(Double moneyValue, String displayValue) {
            this.moneyValue = moneyValue;
            this.displayValue = displayValue;
        }
    }
}
