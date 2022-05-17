package com.progressSoft.demo;

import com.progressSoft.demo.dto.ForeignExchangeTransactionDto;
import com.progressSoft.demo.service.ForeignExchangeTransactionService;
import com.progressSoft.demo.service.exceptions.InvalidCurrencyTextException;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertThrows;

@SpringBootTest()
public class ForeignExchangeTransactionServiceTest {

    @Autowired
    ForeignExchangeTransactionService foreignExchangeTransactionService;
    @Test
    void validateCurrenciesText() throws Exception {
        ForeignExchangeTransactionDto foreignExchangeTransactionDto=new ForeignExchangeTransactionDto();

        foreignExchangeTransactionDto.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        foreignExchangeTransactionDto.setDealAmount(12);
        foreignExchangeTransactionDto.setToCurrencyCode("12cdfs");
        foreignExchangeTransactionDto.setFromCurrencyCode("ewewr3");

        assertThrows(InvalidCurrencyTextException.class, (ThrowingRunnable) foreignExchangeTransactionService.insertTransaction(foreignExchangeTransactionDto));
    }
}
