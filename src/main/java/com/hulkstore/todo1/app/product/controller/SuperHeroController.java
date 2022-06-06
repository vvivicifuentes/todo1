package com.hulkstore.todo1.app.product.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hulkstore.todo1.app.product.entity.Product;
import com.hulkstore.todo1.app.product.entity.SuperHero;
import com.hulkstore.todo1.app.product.service.SuperHeroService;
import com.hulkstore.todo1.helper.FormatMessage;

import org.springframework.validation.BindingResult;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/superHero")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    
    private FormatMessage message = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<SuperHero>> listSuperHero() {
        List<SuperHero> superHeros = superHeroService.listAllSuperHero();

        if (superHeros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(superHeros);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<SuperHero> getSuperHero(@PathVariable("id") Long id) {
        SuperHero superHero = superHeroService.getSuperHero(id);

        if (null == superHero) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(superHero);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<SuperHero> createSuperHero(@Valid @RequestBody SuperHero superHero, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message.formatMessage(result));
        }

        SuperHero superHeroDB = superHeroService.createSuperHero(superHero);

        return ResponseEntity.status(HttpStatus.CREATED).body(superHeroDB);
    }

}