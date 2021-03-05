package com.contas.pagar.model.specification;

import java.util.List;
import java.util.function.BiFunction;

public final class Specifications {
    private Specifications() {
    }
/*
    public static <T> BasicSpecification<T> basic(BiFunction<T, BasicSpecification, Boolean> function) {
        return basic((String)null, function);
    }

    public static <T> BasicSpecification<T> basic(String message, BiFunction<T, BasicSpecification, Boolean> function) {
        return basic((String)null, message, function);
    }

    public static <T> BasicSpecification<T> basic(String property, String message, BiFunction<T, BasicSpecification, Boolean> function) {
        return basic((String)null, property, message, function);
    }

    public static <T> BasicSpecification<T> basic(String bean, String property, String message, BiFunction<T, BasicSpecification, Boolean> function) {
        return new FunctionSpecification(bean, property, message, function);
    }

    public static <T> BasicSpecification<T> unique(BiFunction<T, UniqueValueSpecification, List<Long>> function) {
        return unique((String)null, function);
    }

    public static <T> BasicSpecification<T> unique(String message, BiFunction<T, UniqueValueSpecification, List<Long>> function) {
        return unique((String)null, message, function);
    }

    public static <T> BasicSpecification<T> unique(String property, String message, BiFunction<T, UniqueValueSpecification, List<Long>> function) {
        return unique((String)null, property, message, function);
    }

    public static <T> BasicSpecification<T> unique(String bean, String property, String message, BiFunction<T, UniqueValueSpecification, List<Long>> function) {
        return new UniqueValueSpecification(bean, property, message, function);
    }*/
}
