package ru.geekbrains.persist.product;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;



@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);
    @Override
    public void init() {
        this.saveOrUpdate(new Product("Кронштейн для снаряда BARFITS настенный черный", 1490));
        this.saveOrUpdate(new Product("Кистевые бинты adidas Boxing Crepe Bandage 350 см синий", 819));
        this.saveOrUpdate(new Product("Крепление для боксерского мешка настенно-потолочное 2 отверстия", 812));
        this.saveOrUpdate(new Product("Подвес для боксерского мешка Solo Fight 500, вылет 48 см", 1759));
        this.saveOrUpdate(new Product("Чехол боксерского мешка Чемпион 140х36 см, синий", 2805));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }
    @Override
    public void deleteById(long id) {
        productMap.remove(id);
    }
    @Override
    public void saveOrUpdate(Product product) {
        if(product.getId()!=null){
            productMap.put(product.getId(), product);
        }
        else {
            long id = identity.incrementAndGet();
            product.setId(id);
            productMap.put(id, product);
        }
    }
    @Override
    public Product findById(long id) {
        return productMap.get(id);
    }
}

