package com.progressSoft.demo.exception;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Log4j2
public class ExceptionAdvisor extends Exception {
    @ExceptionHandler({InvalidCurrencyTextException.class, AmountException.class, CurrencyNotFoundException.class, TransactionExistException.class, Exception.class})
    public ResponseEntity<String> exception(Exception ex) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BeanValidationCustomException.class)
    public ResponseEntity<List<String>> exception(BeanValidationCustomException ex) {
        log.error(ex);

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<ForeignExchangeTransactionDto> constraint : ex.getConstraintSet()) {
            errors.add(constraint.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


/*    @ExceptionHandler(AmountException.class)
    public ResponseEntity<String> amountException(AmountException ex) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<String> currencyNotFoundException(CurrencyNotFoundException ex) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }*/


}
