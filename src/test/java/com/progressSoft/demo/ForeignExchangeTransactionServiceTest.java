package com.progressSoft.demo;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.exception.AmountException;
import com.progressSoft.demo.exception.CurrencyNotFoundException;
import com.progressSoft.demo.service.ForeignExchangeTransactionService;
import com.progressSoft.demo.exception.InvalidCurrencyTextException;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest()
public class ForeignExchangeTransactionServiceTest {

    @Autowired
    ForeignExchangeTransactionService foreignExchangeTransactionService;

    @Test
    void validateCurrenciesText() {
        ForeignExchangeTransactionDto foreignExchangeTransactionDto = new ForeignExchangeTransactionDto();

        foreignExchangeTransactionDto.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        foreignExchangeTransactionDto.setDealAmount(12.0);
        foreignExchangeTransactionDto.setToCurrencyCode("12cdfs");
        foreignExchangeTransactionDto.setFromCurrencyCode("ewewr3");

        InvalidCurrencyTextException exception = assertThrows(InvalidCurrencyTextException.class, () -> {
            foreignExchangeTransactionService.insertTransaction(foreignExchangeTransactionDto);
        });

        assertEquals(exception.getClass(), InvalidCurrencyTextException.class);
    }

    @Test
    void validateCurrenciesTextNotValidCurrency() {
        ForeignExchangeTransactionDto foreignExchangeTransactionDto = new ForeignExchangeTransactionDto();

        foreignExchangeTransactionDto.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        foreignExchangeTransactionDto.setDealAmount(12.0);
        foreignExchangeTransactionDto.setToCurrencyCode("ABCD");
        foreignExchangeTransactionDto.setFromCurrencyCode("EFG");

        CurrencyNotFoundException exception = assertThrows(CurrencyNotFoundException.class, () -> {
            foreignExchangeTransactionService.insertTransaction(foreignExchangeTransactionDto);
        });

        assertEquals(exception.getClass(), CurrencyNotFoundException.class);
    }

    @Test
    void validateCurrenciesTextNotValidAmount() {
        ForeignExchangeTransactionDto foreignExchangeTransactionDto = new ForeignExchangeTransactionDto();

        foreignExchangeTransactionDto.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        foreignExchangeTransactionDto.setDealAmount(-12.0);
        foreignExchangeTransactionDto.setToCurrencyCode("USD");
        foreignExchangeTransactionDto.setFromCurrencyCode("JOD");

        AmountException exception = assertThrows(AmountException.class, () -> {
            foreignExchangeTransactionService.insertTransaction(foreignExchangeTransactionDto);
        });

        assertEquals(exception.getClass(), AmountException.class);
    }
}
