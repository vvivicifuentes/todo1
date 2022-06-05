package com.hulkstore.todo1.app.product.service;

import java.util.List;

import com.hulkstore.todo1.app.product.entity.SuperHero;


public interface SuperHeroService {

    public List<SuperHero> listAllSuperHero();
    public SuperHero createSuperHero(SuperHero superHero);
    public SuperHero getSuperHero(Long id);
    
}