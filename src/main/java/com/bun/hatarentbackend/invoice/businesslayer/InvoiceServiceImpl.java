package com.bun.hatarentbackend.invoice.businesslayer;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import com.bun.hatarentbackend.invoice.datalayer.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public List<Invoice> findAll() {
        log.info("Getting all the data");
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findByUuid(UUID invoiceId) {
        log.info("Getting invoice");
        Optional<Invoice> invoiceEntity = invoiceRepository.findById(invoiceId);
        log.info("Found invoice");
        return invoiceEntity;
    }

    @Override
    public Invoice update(Invoice invoiceEntity) {
        log.info("Updating invoice");
        Invoice invoice = invoiceRepository.save(invoiceEntity);
        log.info("Updated invoice");
        return invoice;
    }

    @Override
    public Invoice create(Invoice invoiceEntity) {
        log.info("Creating invoice");
        Invoice invoice = invoiceRepository.save(invoiceEntity);
        if (invoice.getInvoiceId() == null){
            log.info("invoice not created");
        }
        log.info("Created invoice");
        return invoice;
    }

    @Override
    public void delete(UUID invoiceId) {
        log.info("deleting invoice");
        final Integer deleted = invoiceRepository.deleteByInvoiceId(invoiceId);
        if(deleted <= 0) {
            log.info("invoice not found");
            return;
        }
        log.info("deleted invoice");
    }
}
