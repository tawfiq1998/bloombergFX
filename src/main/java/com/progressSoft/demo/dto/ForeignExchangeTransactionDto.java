package com.progressSoft.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class ForeignExchangeTransactionDto {

    @NotBlank(message = "fromCurrencyCode should not be null")
    private String fromCurrencyCode;

    @NotBlank(message = "toCurrencyCode should not be null")
    private String toCurrencyCode;

    private Timestamp transactionDate;

    @NotNull(message = "dealAmount should not be null")
    private Double dealAmount ;
}
