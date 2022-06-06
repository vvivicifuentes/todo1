package com.hulkstore.todo1.app.product.service;


import java.util.List;

import com.hulkstore.todo1.app.product.entity.Product;
import com.hulkstore.todo1.app.product.entity.SuperHero;


public interface ProductService {
    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public  Product deleteProduct(Long id);
    public List<Product> findBySuperHero(SuperHero name);
    public Product updateStock(Long id, int quantity);
    public boolean stockValidate(Long id, int quantity);
}