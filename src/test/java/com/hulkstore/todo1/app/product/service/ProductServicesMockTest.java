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
import com.hulkstore.todo1.app.product.repository.ProductRepository;


@SpringBootTest
public class ProductServicesMockTest {

    private ProductRepository productRepMock = Mockito.mock(ProductRepository.class);

  
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductServiceImp( productRepMock );
        List<Product> productsMock = new ArrayList<>();
        Product product01 = Product.builder().id(1L).name("product name").price((double) 20000).stock(10).build();
        Product product02 = Product.builder().id(2L).name("product name 2").price((double) 30000).stock(50).build();
        
        Product productSave = product01;
        productSave.setStatus("CREATED");
        productSave.setSuperHero(SuperHero.builder().id(1L).build());

        productsMock.add(product01);
        productsMock.add(product02);

        Mockito.when(productRepMock.findAll()).thenReturn(productsMock);
        Mockito.when(productRepMock.findById(1L)).thenReturn(Optional.of(product01));
        Mockito.when(productRepMock.save(any(Product.class))).thenReturn(productSave);

    }

    @Test
    public void whenFindAllProduct_thenReturnListProduct() {
        List<Product> products01 = productService.listAllProduct();
        Assertions.assertThat(products01.size()).isEqualTo(2);
    }

    @Test
    public void whenFindProductById_thenReturnProduct(){
        Product product01 = productService.getProduct(1L);
        Assertions.assertThat(product01.getName()).isEqualTo("product name");
    }

    @Test
    public void whenFindProductByIdAndNotExist_thenReturnNull(){
        Product product01 = productService.getProduct(3L);
        Assertions.assertThat(product01).isNull();
    }

    @Test
    public void whenSaveProduct_thenReturnProduct(){
        Product product01 = Product.builder().id(1L)
            .name("product name")
            .price((double) 20000)
            .stock(10)
            .superHero(SuperHero.builder().id(1L).build())
            .build();
        Product productSave = productService.createProduct(product01);
        
        Assertions.assertThat(productSave).isEqualTo(product01);
        Assertions.assertThat(productSave.getStatus()).isEqualTo("CREATED");
    }

    @Test
    public void whenUpdateProductExist_thenReturnProduct(){
        Product product01 = Product.builder().id(1L)
            .name("product name")
            .price((double) 20000)
            .stock(10)
            .superHero(SuperHero.builder().id(1L).build())
            .build();
        Product productSave = productService.createProduct(product01);
        
        product01.setName("product name change");
        productSave = productService.updateProduct(product01);
        Assertions.assertThat(productSave.getName()).isEqualTo("product name change");
    }

    @Test
    public void whenUpdateProductNotExist_thenReturnNull(){
        Product product01 = Product.builder().id(1L)
            .name("product name")
            .price((double) 20000)
            .stock(10)
            .superHero(SuperHero.builder().id(1L).build())
            .build();
        Product productSave = productService.createProduct(product01);
        
        product01.setName("product name change");
        product01.setId(2L);
        
        productSave = productService.updateProduct(product01);
        Assertions.assertThat(productSave).isNull();
    }

    @Test
    public void whenDeleteProductExist_thenReturnProduct(){
        Product product01 = Product.builder().id(1L)
            .name("product name")
            .price((double) 20000)
            .stock(10)
            .superHero(SuperHero.builder().id(1L).build())
            .build();
        productService.createProduct(product01);
        Product productDelete = productService.deleteProduct(1L);
         
        Assertions.assertThat(productDelete.getStatus()).isEqualTo("DELETED");
    }

    @Test
    public void whenUpdateStock_thenReturnProductNewStock(){
        Product product01 = Product.builder().id(1L)
            .name("product name")
            .price((double) 20000)
            .stock(10)
            .superHero(SuperHero.builder().id(1L).build())
            .build();

        productService.createProduct(product01);

        Product productUpdateStock = productService.updateStock(1L, 5);
        Assertions.assertThat(productUpdateStock.getStock()).isEqualTo(15);

        productUpdateStock = productService.updateStock(1L, -8);
        Assertions.assertThat(productUpdateStock.getStock()).isEqualTo(7);

        /**This case, the function doesn't do any */
        productUpdateStock = productService.updateStock(1L, -10);
        Assertions.assertThat(productUpdateStock.getStock()).isEqualTo(7);
    }

}
