package com.bun.hatarentbackend.presentationlayer.controller;

import com.bun.hatarentbackend.invoice.businesslayer.InvoiceMapper;
import com.bun.hatarentbackend.invoice.businesslayer.InvoiceService;
import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import com.bun.hatarentbackend.invoice.datalayer.InvoiceDTO;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    public InvoiceController(InvoiceService invoiceService, InvoiceMapper invoiceMapper) {
        this.invoiceService = invoiceService;
        this.invoiceMapper = invoiceMapper;
    }
    @GetMapping("/invoice")
    public List<Invoice> findAllInvoices(){
        List<Invoice> invoiceList = invoiceService.findAll();
        log.info("Found invoices");
        return invoiceList;
    }

    @GetMapping("/invoice/{uuid}")
    public Invoice findInvoiceById(@PathVariable @NotNull UUID uuid){
        Optional<Invoice> invoiceEntity = invoiceService.findByUuid(uuid);
        if(invoiceEntity.isEmpty()){
            log.info("invoice with invoice id: {} not found", uuid);
            throw new NotFoundException();
        }
        final Invoice invoice = invoiceEntity.get();
        log.info("retrieved invoice by uuid {}", invoice.getInvoiceId());
        return invoice;
    }

    @PostMapping(value = "/invoice",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO){
        Invoice invoiceMapped = invoiceMapper.invoiceDTOToInvoiceEntity(invoiceDTO);
        Invoice invoiceCreated = invoiceService.create(invoiceMapped);
        log.info("Invoice created");
        return invoiceCreated;
    }

    @PutMapping(value = "/invoice/{uuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Invoice updateInvoice(@PathVariable UUID uuid, @RequestBody InvoiceDTO invoiceDTO){
        log.info("updating invoice");
        Invoice invoiceMapped = invoiceMapper.invoiceDTOToInvoiceEntity(invoiceDTO);
        invoiceMapped.setInvoiceId(uuid);
        Invoice invoiceUpdated = invoiceService.update(invoiceMapped);
        log.info("Invoice updated");
        return invoiceUpdated;
    }
    @Transactional
    @DeleteMapping(path = "/invoice/{uuid}")
    public void deleteByInvoiceId(@PathVariable UUID uuid){
        log.info("deleting invoice");
        invoiceService.delete(uuid);
        log.info("deleted invoice");
    }


}
