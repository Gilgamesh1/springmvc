package guru.springframework.service;

import guru.springframework.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {
    private Map<Integer, Product> productMap;

    @Override
    protected void loadDomainObjects() {
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
    }

    @Override
    public List<Product> listAllProduct() {
        return super.listAll();
    }

    @Override
    public Product getProduct(int id) {
        return super.getById(id);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return super.saveOrUpdate(product);
    }

    @Override
    public void deleteProduct(Integer id) {
      super.delete(id);
    }
}
