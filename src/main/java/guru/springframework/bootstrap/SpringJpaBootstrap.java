package guru.springframework.bootstrap;

import guru.springframework.domain.Product;
import guru.springframework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
    }

    private void loadProducts() {
        Product product1 = new Product(1, "Product 1", new BigDecimal(15456.15),
                "http://example.com/product1");
        productService.saveOrUpdateProduct(product1);
        Product product2 = new Product(2, "Product 2", new BigDecimal(6854.516),
                "http://example.com/product2");
        productService.saveOrUpdateProduct(product2);
        Product product3 = new Product(3, "Product 3", new BigDecimal(651.51),
                "http://example.com/product3");
        productService.saveOrUpdateProduct(product3);
        Product product4 = new Product(4, "Product 4", new BigDecimal(16546.51),
                "http://example.com/product4");
        productService.saveOrUpdateProduct(product4);
    }
}
