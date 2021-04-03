package com.central.test.controller.request;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTestHelper {
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    public static <T> void assertConstraintFieldFail(T actualObject, String expectField, String expectMessage) {
        Set<ConstraintViolation<T>> actualErrors = VALIDATOR.validate(actualObject);
        assertEquals(1, actualErrors.size());
        actualErrors.forEach(actualError -> {
            assertEquals(expectField, actualError.getPropertyPath().toString());
            assertEquals(expectMessage, actualError.getMessage());
        });
    }

    public static <T> void assertConstraintFieldSuccess(T actualObject) {
        Set<ConstraintViolation<T>> actualErrors = VALIDATOR.validate(actualObject);
        assertTrue(actualErrors.isEmpty());
    }
}
