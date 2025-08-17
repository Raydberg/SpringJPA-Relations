package com.practice.validations;

import com.practice.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
//-> para que se pueda inyectar en spring
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "El nombre no puede ser vacio");
        if (product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null, "La descripcion no puede estar vacia");
        }
        if (product.getPrice() == null) {
            errors.rejectValue("price", null, "El precio no puede estar vacio");
        }
    }
}
