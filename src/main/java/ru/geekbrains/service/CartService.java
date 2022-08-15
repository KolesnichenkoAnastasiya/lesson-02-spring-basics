package ru.geekbrains.service;

import ru.geekbrains.persist.cart.Cart;
import ru.geekbrains.persist.product.Product;

public interface CartService {
    void addProduct(Cart cart, Product product, Integer quantity);
    void delProductByProd(Cart cart, Product product, Integer quantity);
    int getSum(Cart cart);
    void printCart(Cart cart);
    boolean productInCart (Cart cart, Product product);
}
