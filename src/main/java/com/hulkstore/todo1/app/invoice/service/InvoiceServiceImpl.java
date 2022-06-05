package com.hulkstore.todo1.app.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkstore.todo1.app.invoice.entity.Invoice;
import com.hulkstore.todo1.app.invoice.entity.InvoiceItem;
import com.hulkstore.todo1.app.invoice.repository.InvoiceItemsRepository;
import com.hulkstore.todo1.app.invoice.repository.InvoiceRepository;
import com.hulkstore.todo1.app.product.entity.Product;
import com.hulkstore.todo1.app.product.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    public final InvoiceRepository invoiceRepository;

    public final InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    ProductService productService;

    @Override
    public List<Invoice> findInvoiceAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB != null) {
            return invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(invoiceItem -> {
            productService.updateStock(invoiceItem.getProductId(), (int) (invoiceItem.getQuantity() * -1));
        });

        return invoiceDB;
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if ( invoiceDB == null || !invoiceDB.getState().equals("CREATED") ) {
            return null;
        }
        
        invoiceDB.setState("DELETED");

        invoiceDB.getItems().forEach(invoiceItem -> {
            productService.updateStock(invoiceItem.getProductId(), (int) (invoiceItem.getQuantity()));
        });

        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (null != invoice) {

            List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productService.getProduct(invoiceItem.getProductId());
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
        }
        return invoice;
    }
}