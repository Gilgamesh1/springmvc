package guru.springframework.service;

import guru.springframework.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProduct();

    Product getProduct(int id);

    Product saveOrUpdateProduct(Product product);

    void deleteProduct(Integer id);
}
