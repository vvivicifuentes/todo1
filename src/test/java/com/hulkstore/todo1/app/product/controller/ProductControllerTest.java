package com.hulkstore.todo1.app.product.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hulkstore.todo1.app.product.entity.Product;
import com.hulkstore.todo1.app.product.entity.SuperHero;
import com.hulkstore.todo1.app.product.repository.ProductRepository;
import com.hulkstore.todo1.app.product.service.ProductService;
import com.hulkstore.todo1.app.product.service.ProductServiceImp;

@SpringBootTest
class ProductControllerTest {

	@Autowired
	private WebApplicationContext wac;
	  
	@Mock
	private ProductService productService;
	
    private ProductRepository productRepMock = Mockito.mock(ProductRepository.class);
	
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

   @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
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
	void listProductTest() throws Exception {
		 mockMvc.perform( get("/products")).andExpect(status().isOk());
	}
	
	@Test
	void getProductTest() throws Exception {
		mockMvc.perform( get("/products",1L)
				.contentType("application/json")
		        .content("")).andExpect(status().isOk());
	}
	
	@Test
	void createProductTest() throws Exception {
		mockMvc.perform( post("/products")
				.contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
				.content(objectMapper.writeValueAsString(this.getProduct()))
				)
		.andExpect(status().isCreated());
	}
	
	@Test
	void updateProductTest() throws Exception {
		long id = 6;
		mockMvc.perform( put("/products/"+id)
				.contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
				.content(objectMapper.writeValueAsString(getProduct2()))
				)
		.andExpect(status().isOk());
	}
	
	public List<Product> getListProduct(){
		List<Product> products = new ArrayList<>();
		 Product product1 = Product.builder().id(1L).name("test1").build();
		 Product product2 = Product.builder().id(2L).name("test2").build();
		 
		products.add(product1);
		products.add(product2);
		
		return products;
	}
	
	public Product getProduct(){
		SuperHero superHero = SuperHero.builder().id(4L).name("DC").build();
		
		Product product1 = Product.builder()
				.id(1L)
				.name("test1")
				.price(35000.0)
				.stock(5)
				.status("CREATED")
				.superHero(superHero)
				.build();
		return product1;
	}
	
	public Product getProduct2(){
		SuperHero superHero = SuperHero.builder().id(4L).name("DC").build();
		
		Product product = Product.builder()
				.name("CAMISETA IRON-MAN")
				.price(37000.0)
				.stock(100)
				.status("CREATED")
				.superHero(superHero)
				.build();
		return product;
	}

}
