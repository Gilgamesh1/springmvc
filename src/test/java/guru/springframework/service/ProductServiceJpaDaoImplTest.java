package guru.springframework.service;

import guru.springframework.configuration.JpaIntegrationConfig;
import guru.springframework.persistence.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {JpaIntegrationConfig.class})
@ActiveProfiles({"jpadao"})
public class ProductServiceJpaDaoImplTest {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();
        assert products.size() == 4;
    }

    @Test
    public void testGetByIdMethod() throws Exception {
        Product product = productService.getById(1);
        assert product != null;
    }

    @Test
    public void testSaveOrUpdateMethod() throws Exception {
        Product product1 = new Product(1, "Product 1", new BigDecimal(15456.15),
                "http://example.com/product1");
        Product product = productService.saveOrUpdate(product1);
        assert product != null;
    }

    @Test
    public void testDeleteMethod() throws Exception {
        productService.delete(2);
    }
}
