package ru.geekbrains.persist.product;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import ru.geekbrains.persist.product.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }
    @Override
    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }
    @Override
    public void update(Product product) {
        productMap.put(product.getId(), product);
    }
    @Override
    public void deleteById(long id) {
        productMap.remove(id);
    }
    @Override
    public long getCount() {
        return 0;
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

