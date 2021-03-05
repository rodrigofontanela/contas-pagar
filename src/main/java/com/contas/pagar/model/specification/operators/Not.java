package com.contas.pagar.model.specification.operators;

import com.contas.pagar.model.specification.BasicSpecification;
import com.contas.pagar.model.specification.ISpecification;

import static java.util.Objects.nonNull;

public class Not<T> extends BasicSpecification<T> {
    private ISpecification<T, BasicSpecification<T>> wrapped;
    private boolean result;

    public Not(ISpecification<T, BasicSpecification<T>> wrapped) {
        this.wrapped = wrapped;
    }

    public String getBean() {
        if (nonNull(super.getBean())) {
            return super.getBean();
        }
        return !this.result ? this.wrapped.getBean() : null;
    }

    public String getMessage() {
        if (nonNull(super.getMessage())) {
            return super.getMessage();
        }
        return !this.result ? this.wrapped.getMessage() : null;
    }

    public String getProperty() {
        if (nonNull(super.getProperty())) {
            return super.getProperty();
        }
        return !this.result ? this.wrapped.getProperty() : null;
    }

    public boolean isSatisfiedBy(T candidate) {
        this.result = this.wrapped.isSatisfiedBy(candidate);
        return !this.result;
    }

    public String toString() {
        return "!".concat(this.wrapped.toString());
    }
}
