package com.bun.hatarentbackend.transaction.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Integer deleteByTransactionId(UUID transactionId);
    List<Transaction> findBySenderId(UUID transactionId);
    List<Transaction> findByReceiverId(UUID transactionId);
}
