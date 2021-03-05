package com.contas.pagar.model.validation;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationException extends javax.validation.ValidationException {
    private final Set<ValidationMessage> validationMessages;

    protected ValidationException(final Set<ValidationMessage> validationMessages) {
        super((String) validationMessages.stream().map(ValidationMessage::getMessage).collect(Collectors.joining(", ")));
        this.validationMessages = validationMessages;
    }

    public Set<ValidationMessage> getValidationMessages() {
        return Collections.unmodifiableSet(this.validationMessages);
    }
}
