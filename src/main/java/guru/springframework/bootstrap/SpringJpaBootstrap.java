package guru.springframework.bootstrap;

import guru.springframework.persistence.domain.Customer;
import guru.springframework.persistence.domain.Product;
import guru.springframework.service.CustomerService;
import guru.springframework.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    private void loadProducts() {
        Product product1 = new Product(1, "Product 1", new BigDecimal(15456.15),
                "http://example.com/product1");
        productService.saveOrUpdate(product1);
        Product product2 = new Product(2, "Product 2", new BigDecimal(6854.516),
                "http://example.com/product2");
        productService.saveOrUpdate(product2);
        Product product3 = new Product(3, "Product 3", new BigDecimal(651.51),
                "http://example.com/product3");
        productService.saveOrUpdate(product3);
        Product product4 = new Product(4, "Product 4", new BigDecimal(16546.51),
                "http://example.com/product4");
        productService.saveOrUpdate(product4);
    }

    private void loadCustomers() {
        Customer customer1 = new Customer(1, "Nombre 1", "Apellidos", "email1@email.com", "897897897", "Direccion 1", "Direccion 2", "Ciudad 1", "Estado 1", "Zip Code 1");
        customerService.saveOrUpdate(customer1);
        Customer customer2 = new Customer(2, "Nombre 2", "Apellidos", "email2@email.com", "897897897", "Direccion 2", "Direccion 2", "Ciudad 2", "Estado 2", "Zip Code 2");
        customerService.saveOrUpdate(customer2);
        Customer customer3 = new Customer(3, "Nombre 3", "Apellidos", "email3@email.com", "897897897", "Direccion 3", "Direccion 3", "Ciudad 3", "Estado 3", "Zip Code 3");
        customerService.saveOrUpdate(customer3);
        Customer customer4 = new Customer(4, "Nombre 4", "Apellidos", "email4@email.com", "897897897", "Direccion 4", "Direccion 4", "Ciudad 4", "Estado 4", "Zip Code 4");
        customerService.saveOrUpdate(customer4);
    }
}
