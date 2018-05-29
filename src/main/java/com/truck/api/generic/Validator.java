package com.truck.api.generic;

@FunctionalInterface
public interface Validator<T> {
    boolean validate(T value);
}
