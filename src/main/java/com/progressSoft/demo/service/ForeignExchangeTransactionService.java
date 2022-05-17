package com.progressSoft.demo.service;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import org.springframework.http.ResponseEntity;

public interface ForeignExchangeTransactionService {

    ResponseEntity<String> insertTransaction(ForeignExchangeTransactionDto foreignExchangeTransactionDto) throws Exception;
}
