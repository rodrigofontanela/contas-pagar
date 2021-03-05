package com.contas.pagar.model.validation;

import com.contas.pagar.configuration.ContextProvider;
import com.contas.pagar.model.specification.ISpecification;
import org.springframework.context.ApplicationContext;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class EntityBuilder<T> implements IBuilder<T> {
    private BusinessRuleValidator validator;
    protected T entity;
    private boolean validate;
    protected final EntityBuilder.EntityState state;
    private List<ISpecification> specifications;
    private List<Class<? extends ISpecification>> specificationClasses;

    protected EntityBuilder(T entity, EntityBuilder.EntityState state) {
        this(entity, state, true);
    }

    protected EntityBuilder(@NotNull T entity, @NotNull EntityBuilder.EntityState state, boolean autoInject) {
        this.validate = true;

        if (autoInject) {
            ApplicationContext applicationContext = ContextProvider.get();
            this.validator = applicationContext.getBean(BusinessRuleValidator.class);
            applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        }

        this.state = state;
        this.entity = entity;
    }

    public <B extends EntityBuilder<T>> B validate(boolean validate) {
        this.validate = validate;
        return (B) this;
    }

    public <B extends EntityBuilder<T>> B skipValidate() {
        return this.validate(false);
    }

    public <B extends EntityBuilder<T>> B addSpecification(ISpecification<T, ?> specification) {
        if (Objects.isNull(this.specifications)) {
            this.specifications = new ArrayList();
        }

        this.specifications.add(specification);
        return (B) this;
    }

    public <B extends EntityBuilder<T>> B addSpecificationClass(Class<? extends ISpecification<T, ?>> specification) {
        if (Objects.isNull(this.specificationClasses)) {
            this.specificationClasses = new ArrayList();
        }

        this.specificationClasses.add(specification);
        return (B) this;
    }

    public <B extends EntityBuilder<T>> B withValidator(BusinessRuleValidator validator) {
        this.validator = validator;
        return (B) this;
    }

    public <B extends EntityBuilder<T>> B withStaleValues(final T staleValues) {
        if (this.entity instanceof HasStaleValues) {
            ((HasStaleValues)this.entity).setStaleValues(staleValues);
            return (B) this;
        } else {
            throw new IllegalArgumentException(String.format("A entidade %s não implementa a interface %s", HasStaleValues.class.getSimpleName(), this.entity.getClass().getName()));
        }
    }

    protected void beforeValidate() {
    }

    protected void afterValidate() {
    }

    protected void validate(@NotNull(message = "É necessário fornecer um validator") final BusinessRuleValidator validator) {
        validator.validate(this.entity);
        validator.validate(this.entity, this.specifications, this.specificationClasses);
    }

    public final T build() {
        Objects.requireNonNull(this.entity);

        if (this.validate) {
            this.beforeValidate();
            this.validate(this.validator);
            this.afterValidate();
        }

        Object entity;
        try {
            entity = this.entity;
        } finally {
            this.entity = null;
        }
        return (T) entity;
    }

    protected static enum EntityState {
        NEW,
        BUILT;

        private EntityState() {
        }
    }
}
