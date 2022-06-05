package com.hulkstore.todo1.app.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table( name = "super_hero" )
@Builder
@NoArgsConstructor @AllArgsConstructor
public class SuperHero {

	
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @NotEmpty(message = "Super Hero name is required")
    private String name;
    @NotEmpty(message = "editorial name is required")
    private String editorial;
}
