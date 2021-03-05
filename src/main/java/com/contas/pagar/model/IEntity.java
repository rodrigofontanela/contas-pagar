package com.contas.pagar.model;

import java.io.Serializable;

import static java.util.Objects.isNull;

public interface IEntity<I> extends Serializable {
    I getId();

    default boolean isNew() {
        return isNull(this.getId());
    }
}
