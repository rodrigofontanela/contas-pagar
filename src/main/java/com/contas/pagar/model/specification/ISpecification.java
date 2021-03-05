package com.contas.pagar.model.specification;

public interface ISpecification<T, E extends ISpecification<T, E>> {
    boolean isSatisfiedBy(T value);

    E and(E value);

    E or(E value);

    E not();

    String getBean();

    String getMessage();

    String getProperty();

    E setBean(String value);

    E setMessage(String value);

    E setProperty(String value);
}
