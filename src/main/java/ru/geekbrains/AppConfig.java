package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.persist.product.Product;
import ru.geekbrains.persist.product.ProductRepository;
import ru.geekbrains.persist.product.ProductRepositoryImpl;
import ru.geekbrains.service.CartServiceImpl;
import ru.geekbrains.service.ProductService;

@Configuration
@ComponentScan("ru.geekbrains")
public class AppConfig {
    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService (productRepository);
    }

    @Bean
    @Scope("prototype")
    public CartServiceImpl cartServiceImpl() {
        return new CartServiceImpl(productRepository());
    }
}
