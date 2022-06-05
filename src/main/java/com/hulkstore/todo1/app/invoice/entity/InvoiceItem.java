package com.hulkstore.todo1.app.invoice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import com.hulkstore.todo1.app.product.entity.Product;
// import java.io.Serializable;

@Data
@Entity
@Table(name = "invoice_items")
@Builder
@AllArgsConstructor
public class InvoiceItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "Price should has value longer cero")
    private int quantity;
    private Double  price;

    @Column(name = "product_id")
    private Long productId;


    @Transient
    private Double subTotal;

    @Transient
    private Product product;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    public InvoiceItem(){
        this.quantity=(int) 0;
        this.price=(double) 0;

    }

}
