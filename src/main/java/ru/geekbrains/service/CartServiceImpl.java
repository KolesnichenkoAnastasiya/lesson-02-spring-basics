package ru.geekbrains.service;

import ru.geekbrains.persist.cart.Cart;
import ru.geekbrains.persist.product.Product;
import ru.geekbrains.persist.product.ProductRepository;

import java.util.Map;

public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    public CartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Cart getNewCart() {
        return new Cart();
    }
    @Override
    public void addProduct(Cart cart, Product product, Integer quantity) {
        try {cart.addProduct(product, quantity);
            System.out.println("Вы добавили товар: " + product.getProductTitle() + " - " + quantity + " шт.");
        }catch (NullPointerException e) {
            System.out.println("Продукт не найден. " +
                    "Для просмотра списка товаров доступных для заказа введите команду: list");
        }
    }
    @Override
    public void delProductByProd(Cart cart, Product product, Integer quantity) {
        try {
            if(!productInCart(cart,product)){
                System.out.println("Данного товара нет в корзине. " +
                        "Для просмотра списка товаров в корзине введите команду: print");
            }
            else {
                cart.delProduct(product, quantity);
                System.out.println("Вы удалили товар: " + product.getProductTitle() + " - " + quantity + " шт.");
            }
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Продукт не найден. " +
                    "Для просмотра списка товаров в корзине введите команду: print");
        }
    }
    @Override
    public int getSum(Cart cart) {
        return cart.getSum();
    }
    public void printSum (Cart cart){
        System.out.println("Стоимость товаров в корзине составляет " + cart.getSum() + " руб");
    }
    @Override
    public void printCart(Cart cart) {
        if (cart.getCartMap().isEmpty()) {
            System.out.println("Корзина пуста");
        } else {for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            Product product = entryMap.getKey();
            int prodSum = (product.getCost())*(entryMap.getValue());
            System.out.println("id = " + product.getId() + "| " + product.getProductTitle() +
                    " стоимость = " + product.getCost() + "руб. | количество = " + entryMap.getValue() + "шт. | сумма = " + prodSum + " руб.");
        }}
        System.out.println("Общая стоимость товаров в корзине: " + cart.getSum()  + " руб");
    }
    @Override
    public boolean productInCart(Cart cart, Product product) {
        boolean inCart = false;
        try{
            if (cart.getCartMap().get(product)>0){
                inCart=true;
            }} catch (NullPointerException e) {
            inCart=false;
        }
        return inCart;
    }
    public static void listCommand() {
        System.out.println("Для вывода инструкции по работе с приложением введите: /?");
        System.out.println("Список товаров доступных для заказа: list");
        System.out.println("Добавить товар в корзину: add [N продукта] [количество]");
        System.out.println("Удалить товар из корзины: del [N продукта] [количество]");
        System.out.println("Распечатать содержимое корзины: print");
        System.out.println("Вывести общую стоимость товаров в корзине: sum");
        System.out.println("Очистить содержимое корзины: clean");
        System.out.println("Завершить: exit");
    }
    public static void wrongCommand (){
        System.out.println("Введена не корректная команда.");
        System.out.println("Для отображения списка доступных команд введите: /?");
    }
}
