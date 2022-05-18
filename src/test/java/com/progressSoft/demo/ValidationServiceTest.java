package com.progressSoft.demo;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.service.ValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidationServiceTest {
    @Autowired
    private ValidationService validationService;

    @Test
    public void validateCurrenciesTextTestValid(){
        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();

        foreignExchangeTransactionDto.setFromCurrencyCode("EUR");
        foreignExchangeTransactionDto.setToCurrencyCode("USD");

        Assertions.assertEquals("", validationService.validateCurrenciesText(foreignExchangeTransactionDto));
    }

    @Test
    public void validateCurrenciesTextTestBothNotValid(){
        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();
        foreignExchangeTransactionDto.setFromCurrencyCode("USD1");
        foreignExchangeTransactionDto.setToCurrencyCode("EUR1");

        Assertions.assertEquals("FromCurrency, ToCurrency, ", validationService.validateCurrenciesText(foreignExchangeTransactionDto));

        foreignExchangeTransactionDto.setFromCurrencyCode("دينار");
        foreignExchangeTransactionDto.setToCurrencyCode("دولار");

        Assertions.assertEquals("FromCurrency, ToCurrency, ", validationService.validateCurrenciesText(foreignExchangeTransactionDto));

    }

    @Test
    public void validateCurrenciesTextTestFromCurrNotValid(){
        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();
        foreignExchangeTransactionDto.setFromCurrencyCode("دولار");
        foreignExchangeTransactionDto.setToCurrencyCode("EUR");

        Assertions.assertEquals("FromCurrency, ", validationService.validateCurrenciesText(foreignExchangeTransactionDto));

        foreignExchangeTransactionDto.setFromCurrencyCode("USD1");
        foreignExchangeTransactionDto.setToCurrencyCode("EUR");

        Assertions.assertEquals("FromCurrency, ", validationService.validateCurrenciesText(foreignExchangeTransactionDto));
    }

    @Test
    public void validateCurrenciesTextTestToCurrNotValid(){
        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();
        foreignExchangeTransactionDto.setFromCurrencyCode("EUR");
        foreignExchangeTransactionDto.setToCurrencyCode("دولار");

        Assertions.assertEquals("ToCurrency, ", validationService.validateCurrenciesText(foreignExchangeTransactionDto));

        foreignExchangeTransactionDto.setFromCurrencyCode("USD");
        foreignExchangeTransactionDto.setToCurrencyCode("EUR1");

        Assertions.assertEquals("ToCurrency, ", validationService.validateCurrenciesText(foreignExchangeTransactionDto));
    }


    @Test
    public void validateAmountValid(){
        Assertions.assertTrue(validationService.validateAmount(100), "Expected true actual false");
        Assertions.assertTrue(validationService.validateAmount(0), "Expected true actual false");
        Assertions.assertTrue(validationService.validateAmount(5.4), "Expected true actual false");
    }

    @Test
    public void validateAmountNotValid(){
        Assertions.assertFalse(validationService.validateAmount(-100), "Expected false actual true");
    }

    @Test
    public void checkIfExistTest(){

    }
}
