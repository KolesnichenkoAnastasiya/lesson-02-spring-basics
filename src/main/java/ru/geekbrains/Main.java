package ru.geekbrains;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.cart.Cart;
import ru.geekbrains.persist.product.Product;
import ru.geekbrains.service.CartServiceImpl;
import ru.geekbrains.service.ProductService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService  productService = context.getBean("productService", ProductService.class);
        CartServiceImpl cartService = context.getBean("cartServiceImpl", CartServiceImpl.class);

        /*ручной ввод продуктов*/
        productService.saveOrUpdate(new Product ("Кофе в зернах Lavazza Qualita oro 1 кг", 1570));
        productService.saveOrUpdate(new Product ("Кофе в зернах Lavazza Qualita oro 1 кг", 1295));
        productService.saveOrUpdate(new Product ( "Кофе EGOISTE Noir в зернаx 1000г.", 1399));
        productService.saveOrUpdate(new Product ( "Кофе в капсулах Nescafe Dolce Gusto cappuccino 16 капсул ", 1132));
        productService.saveOrUpdate(new Product ( "Кофе BUSHIDO Original сублимированный 100г.", 642));

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        Cart cart = cartService.getNewCart();

        System.out.println("Консольное приложение для работы с корзиной\n");
        CartServiceImpl.listCommand();

        while(!exit) {
            System.out.println("Введите команду:  ");
            String command = sc.nextLine();
            String[] parts = command.split("\\s");
            String commandLike = parts[0];
            long productId;
            int quantity;

            if (parts.length == 1) {
            if (commandLike.equalsIgnoreCase("exit")) {
                    exit = true; // флаг - выйти из цикла и завершить работу
                    System.out.println("Спасибо, что воспользовались нашим сервисом");
            }
            else if (commandLike.equalsIgnoreCase("/?")) {
                CartServiceImpl.listCommand();
            }
            else if (commandLike.equalsIgnoreCase("list")) {
                productService.printProduct(productService.getProductList());
            }
            else if (commandLike.equalsIgnoreCase("print")) {
                cartService.printCart(cart);
            }
            else if (commandLike.equalsIgnoreCase("sum")) {
                cartService.printSum(cart);
            }
            else if (commandLike.equalsIgnoreCase("clean")) {
                cart = cartService.getNewCart();
                System.out.println("Корзина очищена");
            }}

            else if (parts.length == 3) {
                try {
                    productId = Long.valueOf(parts[1]);
                    quantity = Integer.parseInt(parts[2]);
                    Product product = productService.getProductById(productId);
                    if (product!=null){
                        if (commandLike.equalsIgnoreCase("add")) {
                        cartService.addProduct(cart, productService.getProductById(productId), quantity);
                        } else if (commandLike.equalsIgnoreCase("del")){
                        cartService.delProductByProd(cart, productService.getProductById(productId), quantity);
                    }} else if (product == null) {
                        System.out.println("Товар с данным id не найден");
                    }
                } catch (NumberFormatException e) {
                    CartServiceImpl.wrongCommand();
                }}

            else {
                CartServiceImpl.wrongCommand();
        }}
        sc.close();
    }
}
