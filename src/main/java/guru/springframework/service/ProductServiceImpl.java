package guru.springframework.service;

import guru.springframework.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private Map<Integer, Product> productMap;

    public ProductServiceImpl() {
        loadProduct();
    }

    private Map<Integer, Product> loadProduct() {
        productMap = new HashMap<>();
        Product product1 = new Product(1, "Product 1", new BigDecimal(15456.15),
                "http://example.com/product1");
        Product product2 = new Product(2, "Product 2", new BigDecimal(6854.516),
                "http://example.com/product2");
        Product product3 = new Product(3, "Product 3", new BigDecimal(651.51),
                "http://example.com/product3");
        Product product4 = new Product(4, "Product 4", new BigDecimal(16546.51),
                "http://example.com/product4");
        productMap.put(1, product1);
        productMap.put(2, product2);
        productMap.put(3, product3);
        productMap.put(4, product4);
        return productMap;
    }

    @Override
    public List<Product> listAllProduct() {
        return new ArrayList<>(productMap.values());
    }

}
