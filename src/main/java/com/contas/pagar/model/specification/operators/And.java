package com.contas.pagar.model.specification.operators;

import com.contas.pagar.model.specification.BasicSpecification;
import com.contas.pagar.model.specification.ISpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

public class And<T> extends BasicSpecification<T> {
    private ISpecification<T, BasicSpecification<T>> left;
    private ISpecification<T, BasicSpecification<T>> right;
    private boolean leftResult;
    private boolean rightResult;

    public And(ISpecification<T, BasicSpecification<T>> left, ISpecification<T, BasicSpecification<T>> right) {
        this.left = left;
        this.right = right;
    }

    public String getBean() {
        if (nonNull(super.getBean())) {
            return super.getBean();
        } else if (!this.leftResult) {
            return this.left.getBean();
        } else {
            return !this.rightResult ? this.right.getBean() : null;
        }
    }

    public String getMessage() {
        if (nonNull(super.getMessage())) {
            return super.getMessage();
        } else if (!this.leftResult) {
            return this.left.getMessage();
        } else {
            return !this.rightResult ? this.right.getMessage() : null;
        }
    }

    public String getProperty() {
        if (nonNull(super.getProperty())) {
            return super.getProperty();
        } else if (!this.leftResult) {
            return this.left.getProperty();
        } else {
            return !this.rightResult ? this.right.getProperty() : null;
        }
    }

    public boolean isSatisfiedBy(T candidate) {
        this.leftResult = this.left.isSatisfiedBy(candidate);
        this.rightResult = this.right.isSatisfiedBy(candidate);
        return this.leftResult && this.rightResult;
    }

    public String toString() {
        return "(".concat(this.left.toString()).concat(" && ").concat(this.right.toString()).concat(")");
    }

    public static class Builder<T> {
        private final List<BasicSpecification<T>> specifications = new ArrayList();

        public Builder(BasicSpecification<T>... specifications) {
            this.specifications.addAll(Arrays.asList(specifications));
        }

        public And.Builder<T> and(BasicSpecification<T> specification) {
            this.specifications.add(specification);
            return this;
        }

        public BasicSpecification<T> build() {
            if (this.specifications.isEmpty()) {
                return new BasicSpecification<T>() {
                    public boolean isSatisfiedBy(T candidate) {
                        return true;
                    }
                };
            } else if (this.specifications.size() == 1) {
                return (BasicSpecification) this.specifications.get(0);
            } else {
                And and = new And((ISpecification) this.specifications.get(0), (ISpecification) this.specifications.get(1));

                for (int i = 2; i < this.specifications.size(); ++i) {
                    and = new And(and, (ISpecification) this.specifications.get(i));
                }
                return and;
            }
        }
    }
}
