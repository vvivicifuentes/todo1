package com.hulkstore.todo1.app.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hulkstore.todo1.app.product.entity.SuperHero;
import com.hulkstore.todo1.app.product.repository.SuperHeroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuperHeroServiceImp implements SuperHeroService {

    private final SuperHeroRepository superHeroRep;

    @Override
    public List<SuperHero> listAllSuperHero() {
        return superHeroRep.findAll();
    }

    @Override
    public SuperHero createSuperHero(SuperHero superHero) {
        return superHeroRep.save(superHero);
    }

	@Override
	public SuperHero getSuperHero(Long id) {
		return superHeroRep.findById(id).orElse(null);
	}

}