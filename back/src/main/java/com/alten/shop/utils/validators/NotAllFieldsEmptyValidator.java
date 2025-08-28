package com.alten.shop.utils.validators;

import com.alten.shop.utils.exceptions.Uncheck.AllFieldsNullException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class NotAllFieldsEmptyValidator implements ConstraintValidator<NotAllFieldsEmpty, Record> {


    @Override
    public boolean isValid(Record value, ConstraintValidatorContext context) {
        if(  value == null ) {  throw new AllFieldsNullException("ARequest body cannot be null");}

        boolean hasNoNullField = Arrays.stream( value.getClass().getRecordComponents())
                .anyMatch(  recordComponent -> {
                    try {
                            return recordComponent.getAccessor().invoke(value) != null;
                        } catch (Exception e) {
                            return false;
                        }
                });
        if (!hasNoNullField) {
            throw new AllFieldsNullException("At least one field must be provided for the partial update");
        }
        return true;
    }

}
