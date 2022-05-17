package com.progressSoft.demo.controller;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.service.ForeignExchangeTransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ForeignExchangeController {
    @Autowired
    ForeignExchangeTransactionService foreignExchangeTransactionService;

    @PostMapping
    public ResponseEntity<String> insertTransaction(@RequestBody ForeignExchangeTransactionDto foreignExchangeDto) throws Exception {
        log.info("insertTransaction method begin");

        foreignExchangeTransactionService.insertTransaction(foreignExchangeDto);
        return null;
    }
}
