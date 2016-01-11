package com.example.query;

import com.example.vehicle.Vehicle;
import com.example.vehicle.utils.MyOptional;

import java.util.Calendar;

public class Query {
    //region Convenience methods for range comparisons
    public static class RangeUtils {
        /**
         * Returns the specified value if it is within the min- and max range; Otherwise, the nearest bound is returned.
         *
         * @param value The value to return, if it is in range
         * @param min The lower bound of the range
         * @param max The upper bound of the range
         * @param <T> The type parameter of range values
         * @return The specified value if in range; the nearest bound otherwise
         */
        public static <T extends Comparable<T>> T getValueInRange(T value, T min, T max) {
            if (value.compareTo(min) < 0) {
                return min;
            } else if (value.compareTo(max) > 0) {
                return max;
            } else {
                return value;
            }
        }

        /**
         * Returns the greater value, if present. If both parameters are empty, the result is empty.
         *
         * @param a   An optional value
         * @param b   An optional value
         * @param <T> The type parameter must extend {@link Comparable}
         * @return The greater of the two optional parameters, or empty.
         */
        public static <T extends Comparable<T>> MyOptional<T> max(MyOptional<T> a, MyOptional<T> b) {
            if (a.isPresent()) {
                if (b.isPresent()) {
                    return a.get().compareTo(b.get()) > 0 ? a : b;
                }
                return a;
            }
            return b;
        }

        /**
         * Returns the smaller value, if present. If both parameters are missing, the result is empty.
         *
         * @param a   An optional value
         * @param b   An optional value
         * @param <T> The type parameter must extend {@link Comparable}
         * @return The smaller of the two optional parameters, or empty.
         */
        public static <T extends Comparable<T>> MyOptional<T> min(MyOptional<T> a, MyOptional<T> b) {
            return max(a, b) == a ? b : a;
        }

        public static <T extends Comparable<T>> boolean isInRange(
                T value, MyOptional<T> min, MyOptional<T> max) {

            final boolean greaterThanOrEqualToMin =
                    !min.isPresent() ? true : value.compareTo(min.get()) >= 0;
            final boolean lessThanOrEqualToMax
                    = !max.isPresent() ? true : value.compareTo(max.get()) <= 0;

            return greaterThanOrEqualToMin && lessThanOrEqualToMax;
        }

        public static <T extends Comparable<T>> boolean isInRange(
                T value, T min, T max) {

            return isInRange(value, MyOptional.of(min), MyOptional.of(max));
        }
    }

    //region Helper methods to ensure that values remain in range
    <T extends Comparable<T>> MyOptional<T> getValidFromValue(
            MyOptional<T> value, MyOptional<T> toField, T min, T max) {

        MyOptional<T> result = MyOptional.empty();

        if (value.isPresent()) {
            T validValue = RangeUtils.getValueInRange(value.get(), min, max);
            result = RangeUtils.min(MyOptional.of(validValue), toField);
        }

        return result;
    }

    <T extends Comparable<T>> MyOptional<T> getValidToValue(
            MyOptional<T> value, MyOptional<T> fromField, T min, T max) {

        MyOptional<T> result = MyOptional.empty();

        if (value.isPresent()) {
            T validValue = RangeUtils.getValueInRange(value.get(), min, max);
            result = RangeUtils.max(MyOptional.of(validValue), fromField);
        }

        return result;
    }
    //endregion

    private static final Query emptyQuery = new Query();
    public static Query empty() {
        return emptyQuery;
    }

    //region Valid value ranges for the query's fields
    public double getPriceMin() {
        return 0;
    }
    public double getPriceMax() {
        return 1000000;
    }

    public int getYearMin() {
        return 1976;
    }
    public int getYearMax() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    public int getKmDrivenMin() {
        return 0;
    }
    public int getKmDrivenMax() {
        return 500000;
    }
    public int getHorsePowerMin() {
        return 0;
    }
    public int getHorsePowerMax() {
        return 500;
    }
    //endregion

    private MyOptional<String> make = MyOptional.empty();
    private MyOptional<String> model = MyOptional.empty();
    private MyOptional<Double> priceFrom = MyOptional.empty();
    private MyOptional<Double> priceTo = MyOptional.empty();
    private MyOptional<Integer> yearFrom = MyOptional.empty();
    private MyOptional<Integer> yearTo = MyOptional.empty();
    private MyOptional<Integer> kmDrivenFrom = MyOptional.empty();
    private MyOptional<Integer> kmDrivenTo = MyOptional.empty();
    private MyOptional<Integer> horsePowerFrom = MyOptional.empty();
    private MyOptional<Integer> horsePowerTo = MyOptional.empty();

    public MyOptional<String> getMake() {
        return make;
    }

    public void setMake(MyOptional<String> make) {
        this.make = make;
    }

    public MyOptional<String> getModel() {
        return model;
    }

    public void setModel(MyOptional<String> model) {
        this.model = model;
    }

    public MyOptional<Double> getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(MyOptional<Double> priceFrom) {
        this.priceFrom = getValidFromValue(priceFrom, this.priceTo, getPriceMin(), getPriceMax());
    }

    public MyOptional<Double> getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(MyOptional<Double> priceTo) {
        this.priceTo = getValidToValue(priceTo, this.priceFrom, getPriceMin(), getPriceMax());
    }

    public MyOptional<Integer> getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(MyOptional<Integer> yearFrom) {
        this.yearFrom = getValidFromValue(yearFrom, this.yearTo, getYearMin(), getYearMax());
    }

    public MyOptional<Integer> getYearTo() {
        return yearTo;
    }

    public void setYearTo(MyOptional<Integer> yearTo) {
        this.yearTo = getValidToValue(yearTo, yearFrom, getYearMin(), getYearMax());
    }

    public MyOptional<Integer> getKmDrivenFrom() {
        return kmDrivenFrom;
    }

    public void setKmDrivenFrom(MyOptional<Integer> kmDrivenFrom) {
        this.kmDrivenFrom = getValidFromValue(
                kmDrivenFrom, kmDrivenTo, getKmDrivenMin(), getKmDrivenMax());
    }

    public MyOptional<Integer> getKmDrivenTo() {
        return kmDrivenTo;
    }

    public void setKmDrivenTo(MyOptional<Integer> kmDrivenTo) {
        this.kmDrivenTo = getValidToValue(
                kmDrivenTo, kmDrivenFrom, getKmDrivenMin(), getKmDrivenMax());
    }

    public MyOptional<Integer> getHorsePowerFrom() {
        return horsePowerFrom;
    }

    public void setHorsePowerFrom(MyOptional<Integer> horsePowerFrom) {
        this.horsePowerFrom = getValidFromValue(
                horsePowerFrom, horsePowerTo, getHorsePowerMin(), getHorsePowerMax());
    }

    public MyOptional<Integer> getHorsePowerTo() {
        return horsePowerTo;
    }

    public void setHorsePowerTo(MyOptional<Integer> horsePowerTo) {
        this.horsePowerTo = getValidToValue(
                horsePowerTo, horsePowerFrom, getHorsePowerMin(), getHorsePowerMax());
    }

    public boolean matches(Vehicle vehicle) {
        final boolean makeMatches =
                !getMake().isPresent() || getMake().get().equalsIgnoreCase(vehicle.getMake());
        final boolean modelMatches =
                !getModel().isPresent() || getModel().get().equalsIgnoreCase(vehicle.getModel());

        final boolean priceInRange =
                RangeUtils.isInRange(vehicle.getPrice(), getPriceFrom(), getPriceTo());
        final boolean yearInRange =
                RangeUtils.isInRange(vehicle.getYear(), getYearFrom(), getYearTo());
        final boolean kmDrivenInRange =
                RangeUtils.isInRange(vehicle.getKmDriven(), getKmDrivenFrom(), getKmDrivenTo());
        final boolean horsePowerInRange =
                RangeUtils.isInRange(vehicle.getHorsePower(), getHorsePowerFrom(), getHorsePowerTo());

        return makeMatches && modelMatches && priceInRange && yearInRange
                && kmDrivenInRange && horsePowerInRange;
    }
}
