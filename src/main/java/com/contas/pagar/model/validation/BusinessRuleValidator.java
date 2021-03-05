package com.contas.pagar.model.validation;

import com.contas.pagar.model.specification.ISpecification;
import com.contas.pagar.model.specification.SpecificationValidator;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.*;

public class BusinessRuleValidator {
    private final Validator beanValidator;
    private final ApplicationContext applicationContext;

    public BusinessRuleValidator(@NotNull final Validator beanValidator, @NotNull final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.beanValidator = beanValidator;
    }

    public <T> T validate(T bean, Class<?>... groups) {
        final Set<ConstraintViolation<T>> violations = this.beanValidator.validate(bean, groups);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(new LinkedHashSet(violations));

        return bean;
    }

    public <T> T validate(T bean) {
        this.validate(bean, Default.class);
        return bean;
    }

    public <T> T validate(T bean, ISpecification specification) {
        return this.validate(bean, Collections.singletonList(specification), Collections.emptyList());
    }

    public <T> T validate(T bean, List<ISpecification> specifications) {
        return this.validate(bean, specifications, Collections.emptyList());
    }

    public <T> T validate(T bean, ISpecification... specifications) {
        return this.validate(bean, Arrays.asList(specifications), Collections.emptyList());
    }

    public <T> T validate(T bean, List<ISpecification> specifications, List<Class<? extends ISpecification>> specificationClasses) {
        SpecificationValidator.create(this.applicationContext)
                .addSpecifications(specifications)
                .addSpecificationClasses(specificationClasses)
                .validateWithException(bean);
        return bean;
    }
}
