package com.progressSoft.demo.service.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class ExceptionAdvisor extends Exception {
    @ExceptionHandler({InvalidCurrencyTextException.class, AmountException.class, CurrencyNotFoundException.class})
    public ResponseEntity<String> exception(Exception ex) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
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
