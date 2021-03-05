package com.contas.pagar.model.validation;

public interface HasStaleValues<T> {
    void setStaleValues(T value);

    T getStaleValues();
}