package com.contas.pagar.model.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class ValidationResult {
    private Set<ValidationMessage> messages;

    private ValidationResult(Set<ValidationMessage> messages) {
        this.messages = messages;
    }

    public static ValidationResult of() {
        return of(Collections.emptySet());
    }

    public static ValidationResult of(ValidationMessage... messages) {
        List<ValidationMessage> list = Arrays.asList(messages);
        return of((Set)(new LinkedHashSet(list)));
    }

    public static ValidationResult of(Set<ValidationMessage> messages) {
        return new ValidationResult(messages);
    }

    public ValidationResult merge(ValidationResult other) {
        if (other.getMessages().isEmpty()) {
            return this;
        }

        final Set<ValidationMessage> validation = new LinkedHashSet(this.messages);
        validation.addAll(other.getMessages());
        this.messages = validation;
        return this;
    }

    public boolean isValid() {
        return this.messages.isEmpty();
    }

    public Set<ValidationMessage> getMessages() {
        return Collections.unmodifiableSet(this.messages);
    }

    public void throwMessages() throws ValidationException {
        if (!this.messages.isEmpty()) {
            throw new ValidationException(this.messages);
        }
    }
}