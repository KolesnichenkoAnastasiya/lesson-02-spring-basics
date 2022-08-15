package ru.geekbrains.persist.product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void insert(Product product);
    void update(Product product);
    long getCount();
    void saveOrUpdate(Product product);
    Product findById(long id);
    void deleteById(long id);
}
