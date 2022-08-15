package ru.geekbrains.persist.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.geekbrains.persist.product.Product;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    private final Map<Product, Integer> cartMap = new HashMap<>();
    public Map<Product, Integer> getCartMap() {
        return cartMap;
    }
    public void addProduct(Product product, Integer count) {
        if (product != null)
            cartMap.merge(product, count, Integer::sum);
    }
    public void delProduct(Product product, Integer count) {
        if (cartMap.containsKey(product)) {
            if (count != null && cartMap.get(product).compareTo(count) > 0) {
                cartMap.put(product, cartMap.get(product) - count);
            } else {
                cartMap.remove(product);
            }
        }
    }
    public int getSum() {
        int sum = 0;
        if(!cartMap.isEmpty()){
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            sum = sum+((entry.getKey().getCost())*(entry.getValue()));;
        }}
        return sum;
    }
}
