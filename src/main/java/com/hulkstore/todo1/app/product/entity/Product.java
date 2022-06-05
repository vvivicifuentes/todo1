package com.hulkstore.todo1.app.product.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table( name = "producto" )
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;
    @Positive(message = "Price should has value longer cero")
    private Double price;
    @Positive(message = "Stock should has value longer cero")
    private int stock;
    private String status;
    @NotNull(message = "Super hero type is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_hero_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private SuperHero superHero;
}