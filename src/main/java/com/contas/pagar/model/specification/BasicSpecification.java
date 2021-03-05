package com.contas.pagar.model.specification;

import com.contas.pagar.model.specification.operators.And;
import com.contas.pagar.model.specification.operators.Not;
import com.contas.pagar.model.specification.operators.Or;

public abstract class BasicSpecification<T> implements ISpecification<T, BasicSpecification<T>> {
    private String bean;
    private String property;
    private String message;

    public BasicSpecification() {
    }

    public BasicSpecification<T> and(BasicSpecification<T> other) {
        return new And(this, other);
    }

    public BasicSpecification<T> not() {
        return new Not(this);
    }

    public BasicSpecification<T> or(BasicSpecification<T> other) {
        return new Or(this, other);
    }

    public String getBean() {
        return this.bean;
    }

    public String getMessage() {
        return this.message;
    }

    public String getProperty() {
        return this.property;
    }

    public BasicSpecification<T> setBean(String bean) {
        this.bean = bean;
        return this;
    }

    public BasicSpecification<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public BasicSpecification<T> setProperty(String property) {
        this.property = property;
        return this;
    }

    public String toString() {
        String specName;
        if (this.getClass().isAnonymousClass()) {
            specName = this.getEnclosingClassString() + "." + this.getClass().getEnclosingMethod().getName();
        } else if (this.getClass().getEnclosingClass() != null) {
            specName = this.getEnclosingClassString() + "." + this.getClass().getSimpleName();
        } else {
            specName = this.getClass().getSimpleName();
        }

        return specName;
    }

    private String getEnclosingClassString() {
        Class clazz = this.getClass();

        StringBuilder sb;
        for(sb = new StringBuilder(clazz.getName().length() * 2); (clazz = clazz.getEnclosingClass()) != null; sb.insert(0, clazz.getSimpleName())) {
            if (sb.length() > 0) {
                sb.insert(0, ".");
            }
        }
        return sb.toString();
    }
}
