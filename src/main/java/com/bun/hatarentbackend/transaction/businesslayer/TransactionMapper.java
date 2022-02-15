package com.bun.hatarentbackend.transaction.businesslayer;

import com.bun.hatarentbackend.transaction.datalayer.Transaction;
import com.bun.hatarentbackend.transaction.datalayer.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "transactionId", ignore = true)
    Transaction transactionDTOToTransactionEntity(TransactionDTO transactionDTO);
    TransactionDTO transactionToTransactionDTO(Transaction transaction);
}
