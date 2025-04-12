package com.example.todo.infra;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnumValidatorConstraint implements
        ConstraintValidator<EnumValidator, String> {

    private EnumValidator annotation;

    private Class<? extends Enum<?>> enumClass;
    private String allowedValues;

    @Override
    public void initialize(EnumValidator annotation) {
        this.enumClass = annotation.enumClass();
        this.allowedValues = Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining(", "));

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // deixa @NotNull cuidar disso se necessário

        boolean isValid = Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equalsIgnoreCase(value));

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Invalid value '" + value + "'. Allowed values: " + allowedValues
            ).addConstraintViolation();
        };


        return isValid;
    }
}
