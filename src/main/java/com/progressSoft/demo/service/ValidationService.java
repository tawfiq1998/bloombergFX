package com.progressSoft.demo.service;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;

public interface ValidationService {

    String validateCurrenciesText(ForeignExchangeTransactionDto foreignExchangeTransactionDto);

    String checkCurrencyType(ForeignExchangeTransactionDto foreignExchangeTransactionDto);


    boolean checkIfExist(ForeignExchangeTransactionDto foreignExchangeTransactionDto);

    boolean validateAmount(double dealAmount);
}
