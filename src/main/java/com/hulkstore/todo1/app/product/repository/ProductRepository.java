package com.hulkstore.todo1.app.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstore.todo1.app.product.entity.Product;
import com.hulkstore.todo1.app.product.entity.SuperHero;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    public List<Product> findBySuperHero(SuperHero name);

}