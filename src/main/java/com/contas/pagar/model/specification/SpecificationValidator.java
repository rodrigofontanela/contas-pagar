package com.contas.pagar.model.specification;

import com.contas.pagar.model.validation.ValidationMessage;
import com.contas.pagar.model.validation.ValidationResult;
import org.springframework.context.ApplicationContext;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class SpecificationValidator<T> {
    private final List specifications = new ArrayList();
    private final ApplicationContext applicationContext;

    private SpecificationValidator(@NotNull final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static <T> SpecificationValidator<T> create(final ApplicationContext applicationContext) {
        return new SpecificationValidator(applicationContext);
    }

    public SpecificationValidator<T> addSpecification(final ISpecification specification) {
        if (nonNull(specification))
            this.specifications.add(specification);

        return this;
    }

    public SpecificationValidator<T> addSpecifications(final List<ISpecification> specifications) {
        if (nonNull(specifications))
            this.specifications.addAll(specifications);

        return this;
    }

    public SpecificationValidator<T> addSpecificationClass(Class<? extends ISpecification> specification) {
        if (nonNull(specification))
            this.specifications.add(specification);

        return this;
    }

    public SpecificationValidator<T> addSpecificationClasses(List<Class<? extends ISpecification>> specifications) {
        if (nonNull(specifications))
            this.specifications.addAll(specifications);

        return this;
    }

    public SpecificationValidator<T> clear() {
        this.specifications.clear();
        return this;
    }

    public SpecificationValidator<T> remove(ISpecification s) {
        this.specifications.remove(s);
        return this;
    }

    public SpecificationValidator<T> remove(Class<? extends ISpecification> s) {
        this.specifications.remove(s);
        return this;
    }

    public int count() {
        return this.specifications.size();
    }

    public void validateWithException(T candidate) {
        this.validateWithException(candidate, false);
    }

    public void validateWithException(T candidate, boolean stopOnError) {
        this.validate(candidate, stopOnError).throwMessages();
    }

    public ValidationResult validate(T candidate) {
        return this.validate(candidate, false);
    }

    public ValidationResult validate(T candidate, boolean stopOnError) {
        ValidationMessage.Builder<ISpecification> builder = ValidationMessage.Builder.create();
        Iterator iterator = this.specifications.iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();
            ISpecification specification = this.getSpecification(object);

            if (!specification.isSatisfiedBy(candidate)) {
                String bean = specification.getBean();
                String message = specification.getMessage();

                if (isNull(bean))
                    bean = candidate.getClass().getSimpleName();

                if (isNull(message))
                    message = specification.toString();

                builder.add(bean, specification.getProperty(), message, specification);

                if (stopOnError)
                    break;
            }
        }

        return builder.build();
    }

    private ISpecification getSpecification(Object candidate) {
        if (candidate instanceof ISpecification) {
            return (ISpecification) candidate;
        } else if (candidate instanceof Class) {
            return (ISpecification) this.applicationContext.getBean((Class) candidate);
        } else {
            throw new IllegalArgumentException(String.format("Não foi possível inicializar a especificação de validação %s", candidate));
        }
    }
}

