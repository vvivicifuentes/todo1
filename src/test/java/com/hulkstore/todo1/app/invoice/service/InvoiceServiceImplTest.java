package com.hulkstore.todo1.app.invoice.service;


import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hulkstore.todo1.app.invoice.entity.Invoice;
import com.hulkstore.todo1.app.invoice.entity.InvoiceItem;
import com.hulkstore.todo1.app.invoice.repository.InvoiceItemsRepository;
import com.hulkstore.todo1.app.invoice.repository.InvoiceRepository;
import com.hulkstore.todo1.app.product.entity.Product;
import com.hulkstore.todo1.app.product.service.ProductService;


@SpringBootTest
public class InvoiceServiceImplTest {

    private InvoiceRepository invoiceRepMock = Mockito.mock(InvoiceRepository.class);
    private InvoiceItemsRepository invoiceItemsRepMock = Mockito.mock(InvoiceItemsRepository.class);

    ProductService productService = Mockito.mock(ProductService.class);
    InvoiceService invoiceService;

    public InvoiceItem item01;
    public Invoice invoice;
    
    public InvoiceItem item02;
    public Invoice invoice02;

    @BeforeEach
    public void setUp(){
        invoiceService = new InvoiceServiceImpl( invoiceRepMock, invoiceItemsRepMock );

        List<InvoiceItem> items = new ArrayList<>();
        List<Invoice> invoices = new ArrayList<>();

        this.item01 = InvoiceItem.builder().id(1L)
                .price( (double) 25000)
                .quantity(2)
                .productId(1L)
                .build();
        
        items.add(this.item01);

        this.invoice = Invoice.builder().id(1L)
                .description("venta")
                .numberInvoice("2021-01")
                .items(items)
                .build();
        
                invoices.add(this.invoice);

                Mockito.when(invoiceRepMock.findAll()).thenReturn(invoices);
                Mockito.when(invoiceItemsRepMock.findAll()).thenReturn(items);
                Mockito.when(invoiceRepMock.findByNumberInvoice("2021_2")).thenReturn(null);
                Mockito.when(invoiceRepMock.findByNumberInvoice("2021_1")).thenReturn(this.invoice);

                items.clear();
                invoices.clear();

                this.item02 = InvoiceItem.builder().id(2L)
                .price( (double) 25000)
                .quantity(2)
                .productId(1L)
                .build();
        
        items.add(this.item02);

        this.invoice02 = Invoice.builder().id(2L)
                .description("venta")
                .numberInvoice("2021-02")
                .items(items)
                .build();
        
                invoices.add(this.invoice02);

       Mockito.when(invoiceRepMock.save(any(Invoice.class))).thenReturn(this.invoice02);

       Product product01 = Product.builder().id(1L).name("product name").price((double) 20000).stock(10).build();
       
       Mockito.when(productService.updateStock(1L, 2)).thenReturn(product01);    
    }

    @Test
    public void whenFindAllInvoice_thenReturnListInvoices(){
        List<Invoice> invoices = invoiceService.findInvoiceAll();

        Assertions.assertThat(invoices.size()).isEqualTo(1);
    }

    @Test
    public void whenCreateInvoice_thenReturnInvoice(){
        Invoice invoiceSave = invoiceService.createInvoice(this.invoice);

        Assertions.assertThat(invoiceSave).isEqualTo(this.invoice);
    }
}