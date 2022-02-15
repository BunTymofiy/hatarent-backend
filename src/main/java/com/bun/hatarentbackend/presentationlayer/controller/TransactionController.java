package com.bun.hatarentbackend.presentationlayer.controller;

import com.bun.hatarentbackend.transaction.businesslayer.TransactionMapper;
import com.bun.hatarentbackend.transaction.businesslayer.TransactionService;
import com.bun.hatarentbackend.transaction.datalayer.Transaction;
import com.bun.hatarentbackend.transaction.datalayer.TransactionDTO;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping()
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }
    @GetMapping("/transaction")
    public List<Transaction> findAllTransactions(){
        List<Transaction> transactionList = transactionService.findAll();
        log.info("Found transactions");
        return transactionList;
    }

    @GetMapping("/transaction/{uuid}")
    public Transaction findTransactionById(@PathVariable @NotNull UUID uuid){
        Optional<Transaction> transactionEntity = transactionService.findByTransactionId(uuid);
        if(transactionEntity.isEmpty()){
            log.info("transaction with uuid {} not found", uuid);
            throw new NotFoundException();
        }
        final Transaction transaction = transactionEntity.get();
        log.info("retrieved transaction by uuid {}", transaction.getTransactionId());
        return transaction;
    }

    @PostMapping(value = "/transaction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Transaction addTransaction(@RequestBody @Valid TransactionDTO transactionDTO){
        Transaction transactionMapped = transactionMapper.transactionDTOToTransactionEntity(transactionDTO);
        Transaction transactionCreated = transactionService.create(transactionMapped);
        log.info("Transaction created");
        return transactionCreated;
    }

    @PutMapping(value = "/transaction/{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Transaction updateTransaction(@PathVariable UUID uuid, @RequestBody TransactionDTO transactionDTO){
        log.info("Updating transaction");
        Transaction transactionMapped = transactionMapper.transactionDTOToTransactionEntity(transactionDTO);
        transactionMapped.setTransactionId(uuid);
        Transaction transactionUpdated = transactionService.update(transactionMapped);
        log.info("Transaction updated");
        return transactionUpdated;
    }

    @Transactional
    @DeleteMapping(path = "/transaction/{uuid}")
    public void deleteByTransactionId(@PathVariable UUID uuid){
        log.info("deleting transaction");
        transactionService.delete(uuid);
        log.info("deleted transaction");
    }

}
