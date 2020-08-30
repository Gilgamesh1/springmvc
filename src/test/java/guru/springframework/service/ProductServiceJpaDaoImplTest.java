package guru.springframework.service;

import guru.springframework.configuration.JpaIntegrationConfig;
import guru.springframework.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
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
        List<Product> products = (List<Product>) productService.listAllProduct();
        assert products.size() == 4;
    }
}
