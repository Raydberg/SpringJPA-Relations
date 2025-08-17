package com.practice.annotations;

import com.practice.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsExistDbValidation implements ConstraintValidator<IsExistDb, String> {
    private final ProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !productService.existsBySku(value);
    }
}
