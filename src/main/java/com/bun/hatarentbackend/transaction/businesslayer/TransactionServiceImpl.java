package com.bun.hatarentbackend.transaction.businesslayer;

import com.bun.hatarentbackend.transaction.datalayer.Transaction;
import com.bun.hatarentbackend.transaction.datalayer.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class TransactionServiceImpl  implements TransactionService{
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        log.info("Getting all transactions");
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findByTransactionId(UUID transactionId) {
        log.info("Getting transaction");
        Optional<Transaction> transactionEntity = transactionRepository.findById(transactionId);
        log.info("Found transaction");
        return transactionEntity;
    }

    @Override
    public Transaction update(Transaction transaction) {
        log.info("Updating transaction");
        Transaction transaction1 = transactionRepository.save(transaction);
        log.info("Updated transaction");
        return transaction1;
    }

    @Override
    public Transaction create(Transaction transaction) {
        log.info("Creating transaction");
        Transaction transaction1 = transactionRepository.save(transaction);
        if (transaction1.getTransactionId() == null){
            log.info("Transaction not created");
        }
        log.info("Created transaction");
        return transaction1;
    }

    @Override
    public void delete(UUID transactionId) {
        log.info("deleting transaction");
        final Integer deleted = transactionRepository.deleteByTransactionId(transactionId);
        if(deleted <= 0){
            log.info("transaction not found");
            return;
        }
        log.info("deleted transaction");
    }
}
