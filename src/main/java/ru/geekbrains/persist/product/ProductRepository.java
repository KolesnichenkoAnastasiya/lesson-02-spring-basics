package ru.geekbrains.persist.product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void saveOrUpdate(Product product);
    Product findById(long id);
    void deleteById(long id);
    void init();
}
