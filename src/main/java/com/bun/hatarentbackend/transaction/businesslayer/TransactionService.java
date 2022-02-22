package com.bun.hatarentbackend.transaction.businesslayer;

import com.bun.hatarentbackend.transaction.datalayer.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> findAll();
    List<Transaction> findAllByUserId(UUID userId);
    List<Transaction> findAllByHostId(UUID userId);
    Optional<Transaction> findByTransactionId(UUID transactionId);
    Transaction update(Transaction transaction);
    Transaction create(Transaction transaction);
    void delete(UUID transactionId);
}

