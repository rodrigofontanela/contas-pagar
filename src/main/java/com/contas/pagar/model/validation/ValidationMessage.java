package com.contas.pagar.model.validation;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Getter
@EqualsAndHashCode
public class ValidationMessage<T> {
    private String bean;
    private String property;
    private String message;
    private ConstraintViolation violation;
    private T source;

    public ValidationMessage(final String message) {
        this((String) null, (String) message);
    }

    public ValidationMessage(final String message, final ConstraintViolation violation) {
        this((String) null, message, (ConstraintViolation) violation);
    }

    public ValidationMessage(final String bean, final String message) {
        this(bean, (String) null, (String) message);
    }

    public ValidationMessage(final String bean, final String message, final ConstraintViolation violation) {
        this(bean, (String) null, message, (ConstraintViolation) violation);
    }

    public ValidationMessage(final String bean, final String message, T source) {
        this(bean, (String) null, message, (T) source);
    }

    public ValidationMessage(final String bean, final String property, final String message) {
        this(bean, property, message, (ConstraintViolation) null, (T) null);
    }

    public ValidationMessage(final String bean, final String property, final String message, final ConstraintViolation violation) {
        this(bean, property, message, violation, (T) null);
    }

    public ValidationMessage(final String bean, final String property, final String message, final ConstraintViolation violation, final T source) {
        this.bean = bean;
        this.property = property;
        this.message = message;
        this.violation = violation;
        this.source = source;
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

    public T getSource() {
        return this.source;
    }

    public ConstraintViolation getViolation() {
        return this.violation;
    }

    public ValidationMessage(final String bean, final String property, final String message, final T source) {
        this(bean, property, message, (ConstraintViolation) null, source);
    }

    public String getErrorPath() {
       return isNull(this.bean)
               ? "message"
               : this.bean.isEmpty() ? "message" : this.bean.concat(this.property.isEmpty() ? "" : ".".concat(this.property));
    }

    public static final class Builder<T> {
        private final Set<ValidationMessage> validationMessages = new LinkedHashSet();

        private Builder() {
        }

        public static ValidationMessage.Builder create() {
            return new ValidationMessage.Builder();
        }

        public ValidationMessage.Builder add(final String message) {
            this.add((String) null, (String) message);
            return this;
        }

        public ValidationMessage.Builder add(final String message, final ConstraintViolation violation) {
            this.add((String) null, (String) null, message, violation, (T) null);
            return this;
        }

        public ValidationMessage.Builder add(final String bean, final String message) {
            this.add(bean, (String) null, (String) message);
            return this;
        }

        public ValidationMessage.Builder add(final String bean, final String message, final ConstraintViolation violation) {
            this.add(bean, (String) null, message, violation, (T) null);
            return this;
        }

        public ValidationMessage.Builder add(final String bean, final String message, final T source) {
            this.add(bean, (String) null, message, (ConstraintViolation) null, source);
            return this;
        }

        public ValidationMessage.Builder add(final String bean, final String property, final String message) {
            this.add(bean, property, message, (ConstraintViolation) null, (T) null);
            return this;
        }

        public ValidationMessage.Builder add(final String bean, final String property, final String message, final ConstraintViolation violation) {
            this.add(bean, property, message, violation, (T) null);
            return this;
        }

        public ValidationMessage.Builder add(final String bean, final String property, final String message, final ConstraintViolation violation, final T source) {
            this.validationMessages.add(new ValidationMessage(bean, property, message, violation, source));
            return this;
        }

        public ValidationMessage.Builder add(final String bean, final String property, final String message, final T source) {
            this.add(bean, property, message, (ConstraintViolation) null, source);
            return this;
        }

        public ValidationMessage.Builder addAll(final Collection<ValidationMessage> messages) {
            this.validationMessages.addAll(messages);
            return this;
        }

        public ValidationResult build() {
            return ValidationResult.of(this.validationMessages);
        }
    }
}

