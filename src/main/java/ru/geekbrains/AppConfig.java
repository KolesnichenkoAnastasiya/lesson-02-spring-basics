package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.persist.product.ProductRepository;
import ru.geekbrains.persist.product.ProductRepositoryImpl;
import ru.geekbrains.service.CartServiceImpl;
import ru.geekbrains.service.ProductService;

@Configuration
@ComponentScan("ru.geekbrains.persist")
public class AppConfig {
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
