package com.truck.api.generic;

public interface Validator<T> {
    boolean validate(T value);
}
