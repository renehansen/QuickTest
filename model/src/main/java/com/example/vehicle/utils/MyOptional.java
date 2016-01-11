package com.example.vehicle.utils;

import java.util.NoSuchElementException;

public final class MyOptional<T> {
    private T value;

    private MyOptional(T value) {
        this.value = value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public static <T> MyOptional<T> of(T value) {
        return new MyOptional<>(value);
    }

    public static <T> MyOptional<T> empty() {
        return new MyOptional<>(null);
    }
}
