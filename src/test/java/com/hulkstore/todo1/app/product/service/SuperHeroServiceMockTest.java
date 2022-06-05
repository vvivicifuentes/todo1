package com.hulkstore.todo1.app.product.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hulkstore.todo1.app.product.entity.Product;
import com.hulkstore.todo1.app.product.entity.SuperHero;
import com.hulkstore.todo1.app.product.repository.SuperHeroRepository;

@SpringBootTest
public class SuperHeroServiceMockTest {
    
    private SuperHeroRepository superHeroRepMock = Mockito.mock(SuperHeroRepository.class);

  
    private SuperHeroService superHeroService;

    @BeforeEach
    public void setUp() {
        superHeroService = new SuperHeroServiceImp( superHeroRepMock );
        List<SuperHero> superHeroMock = new ArrayList<>();
        SuperHero superHero01 = SuperHero.builder().id(1L).name("super man").editorial("DC Comics").build();
        
        superHeroMock.add(superHero01);
       
        Mockito.when(superHeroRepMock.findAll()).thenReturn(superHeroMock);
        Mockito.when(superHeroRepMock.findById(1L)).thenReturn(Optional.of(superHero01));
        Mockito.when(superHeroRepMock.save(any(SuperHero.class))).thenReturn(superHero01);
        System.out.println("Hola Mundo");

    }

    @Test
    public void whenGetAllSuperHero_thenReturnListSuperHero() {
        List<SuperHero> superHeros01 = superHeroService.listAllSuperHero();
        Assertions.assertThat(superHeros01.size()).isEqualTo(1);
    }

    @Test
    public void whenCreateSuperHero_thenReturnSuperHero(){
        SuperHero super01 =SuperHero.builder().id(1L).name("super man").editorial("DC Comics").build();

        SuperHero superSave = superHeroService.createSuperHero(super01);
        
        Assertions.assertThat(superSave).isEqualTo(super01);
    }
    
    @Test
    public void whenFindSuperHeroById_thenReturnSuperHero(){
    	 SuperHero super01 = superHeroService.getSuperHero(1L);
        Assertions.assertThat(super01.getName()).isEqualTo("super man");
    }

    @Test
    public void whenFindSuperHeroByIdAndNotExist_thenReturnNull(){
    	 SuperHero super01 = superHeroService.getSuperHero(3L);
        Assertions.assertThat(super01).isNull();
    }
}