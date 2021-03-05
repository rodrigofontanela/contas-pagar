package com.contas.pagar.model.specification.operators;

import com.contas.pagar.model.specification.BasicSpecification;
import com.contas.pagar.model.specification.ISpecification;

public class Or<T> extends BasicSpecification<T> {
    private ISpecification<T, BasicSpecification<T>> a;
    private ISpecification<T, BasicSpecification<T>> b;
    private boolean resultA;
    private boolean resultB;

    public Or(ISpecification<T, BasicSpecification<T>> a, ISpecification<T, BasicSpecification<T>> b) {
        this.a = a;
        this.b = b;
    }

    public String getBean() {
        if (super.getBean() != null) {
            return super.getBean();
        } else if (!this.resultA) {
            return this.a.getBean();
        } else {
            return !this.resultB ? this.b.getBean() : null;
        }
    }

    public String getMessage() {
        if (super.getMessage() != null) {
            return super.getMessage();
        } else if (!this.resultA) {
            return this.a.getMessage();
        } else {
            return !this.resultB ? this.b.getMessage() : null;
        }
    }

    public String getProperty() {
        if (super.getProperty() != null) {
            return super.getProperty();
        } else if (!this.resultA) {
            return this.a.getProperty();
        } else {
            return !this.resultB ? this.b.getProperty() : null;
        }
    }

    public boolean isSatisfiedBy(T candidate) {
        this.resultA = this.a.isSatisfiedBy(candidate);
        this.resultB = this.b.isSatisfiedBy(candidate);
        return this.resultA || this.resultB;
    }

    public String toString() {
        return "(" + this.a + " || " + this.b + ")";
    }
}
