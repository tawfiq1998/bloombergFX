package com.progressSoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.progressSoft.demo.entity.ForeignExchangeTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ForeignExchangeTransactionRepository extends JpaRepository<ForeignExchangeTransaction, Long> {


    @Query(value = "SELECT count(*)" +
            " FROM ForeignExchangeTransaction f" +
            " WHERE f.fromCurrencyCode= ?1" +
            " AND f.toCurrencyCode= ?2" +
            " AND f.dealAmount= ?3" +
            " AND f.transactionDate= ?4")
    Long foreign_exchange_transaction(String fromCurrency, String toCurrency, double amount, Timestamp transactionDate);
}
