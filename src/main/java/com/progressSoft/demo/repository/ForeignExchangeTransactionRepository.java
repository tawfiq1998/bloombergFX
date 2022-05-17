package com.progressSoft.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.progressSoft.demo.entity.ForeignExchangeTransaction;

public interface ForeignExchangeTransactionRepository extends JpaRepository<ForeignExchangeTransaction, Long> {
}
