package ru.geekbrains.service;

import ru.geekbrains.persist.product.Product;
import ru.geekbrains.persist.product.ProductRepository;

import java.util.List;
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        productRepository.init();
    }
    public List<Product> getProductList() {
        return productRepository.findAll();
    }
    public void printProduct(List<Product> productList) {
    for (Product o: productList) {
        o.printProduct();
    }}
    public void printProduct(Product product) {
        product.printProduct();
        }
    public void saveOrUpdate(Product product) {
        productRepository.saveOrUpdate(product);
    }
    public Product getProductById(Long id) {
        if (productRepository.findById(id)!=null){
            return productRepository.findById(id);
        } else return null;
    }
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

}
