package com.hulkstore.todo1.app.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hulkstore.todo1.app.product.entity.SuperHero;


@Repository
public interface SuperHeroRepository extends JpaRepository< SuperHero , Long >{
    
}