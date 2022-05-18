package com.progressSoft.demo.exception;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Data
public class BeanValidationCustomException extends RuntimeException {

    Set<ConstraintViolation<ForeignExchangeTransactionDto>> constraintSet;

    public BeanValidationCustomException(Set<ConstraintViolation<ForeignExchangeTransactionDto>> constraintSet){
        this.constraintSet = constraintSet;
    }



}
