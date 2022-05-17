package com.progressSoft.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ForeignExchangeTransaction")
@Data
public class ForeignExchangeTransaction {
    @Id
    @Column(name = "DealID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dealId;

    /**
     * Ordering Currency
     * */
    @Column(name = "fromCurrencyISOCode")
    private String fromCurrencyCode;

    @Column(name = "toCurrencyISOCode")
    private String toCurrencyCode;

    /**
     * Ordering Date
     * */
    @Column(name = "transactionDate")
    private Timestamp transactionDate;

    /**
     * Deal Amount in ordering currency
     * */
    @Column(name = "dealAmount")
    private double dealAmount;

}
