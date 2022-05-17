package com.progressSoft.demo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ForeignExchangeTransactionDto {

    private String fromCurrencyCode;

    private String toCurrencyCode;

    private Timestamp transactionDate;

    private double dealAmount;
}
