package guru.springframework.service;

import guru.springframework.configuration.JpaIntegrationConfig;
import guru.springframework.persistence.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;


@SpringBootTest
@ContextConfiguration(classes = {JpaIntegrationConfig.class})
@ActiveProfiles({"jpadao"})
public class CustomerServiceImplJpaDaoImplTest {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testListMethod() throws Exception {
        List<Customer> customers = (List<Customer>) customerService.listAll();
        assert customers.size() == 4;
    }

    @Test
    public void testGetByIdMethod() throws Exception {
        Customer customer = customerService.getById(1);
        assert customer != null;
    }

    @Test
    public void testSaveOrUpdateMethod() throws Exception {
        Customer customer1 = new Customer(1, "Nombre 1", "Apellidos", "email1@email.com", "897897897", "Direccion 1", "Direccion 2", "Ciudad 1", "Estado 1", "Zip Code 1");
        Customer customer = customerService.saveOrUpdate(customer1);
        assert customer != null;
    }

    @Test
    public void testDeleteMethod() throws Exception {
        customerService.delete(2);
    }
}
