package com.anmv.validation;

import com.anmv.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.persistence.Table;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNameNotExistsValidator implements ConstraintValidator<DepartmentNameNotExist, String> {
    @Autowired
    private DepartmentService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)){
            return true;
        }

        return !service.isDepartmentExistByName(value);
    }
}
