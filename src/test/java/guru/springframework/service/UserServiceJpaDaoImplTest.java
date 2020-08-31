package guru.springframework.service;

import guru.springframework.configuration.JpaIntegrationConfig;
import guru.springframework.persistence.domain.Customer;
import guru.springframework.persistence.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ContextConfiguration(classes = {JpaIntegrationConfig.class})
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();

        user.setUserName("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);
        System.out.println(savedUser.toString());
        assert savedUser.getId() != null;
        assert savedUser.getStrongPassword() != null;

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getStrongPassword());

    }

    @Test
    public void testSaveOfUserWithCustomer() throws Exception {
        User user = new User();
        user.setUserName("someusername");
        user.setPassword("myPassword");
        Customer customer = new Customer();
        customer.setFirstName("Chevy");
        customer.setLastName("Chase");
        user.setCustomer(customer);
        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCustomer() != null;
        assert savedUser.getCustomer().getId() != null;
    }
}
