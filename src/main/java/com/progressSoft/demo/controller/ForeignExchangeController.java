package com.progressSoft.demo.controller;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.exception.BeanValidationCustomException;
import com.progressSoft.demo.service.ForeignExchangeTransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping("/ForeignExchange")
@Log4j2
public class ForeignExchangeController {
    @Autowired
    ForeignExchangeTransactionService foreignExchangeTransactionService;

    @Autowired
    Validator validator;

    /**
     * this controller to save transaction
     * */
    @PostMapping
    public ResponseEntity<String> insertTransaction(@RequestBody ForeignExchangeTransactionDto foreignExchangeDto) throws Exception {
        log.info("insertTransaction method begin");

        if (foreignExchangeDto == null)
            throw new Exception("error message");

        Set<ConstraintViolation<ForeignExchangeTransactionDto>>  validateResult =  validator.validate(foreignExchangeDto);
        if (!validateResult.isEmpty())
            throw new BeanValidationCustomException(validateResult);


        ResponseEntity<String> responseEntity = foreignExchangeTransactionService.insertTransaction(foreignExchangeDto);
        return responseEntity;
    }
}
