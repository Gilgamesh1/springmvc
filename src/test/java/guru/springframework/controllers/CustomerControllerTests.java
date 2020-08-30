package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTests {
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetListCustomers() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer());
        customerList.add(new Customer());
        when(customerService.listAllCustomer()).thenReturn((List) customerList);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customers"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShowCustomers() throws Exception {
        Integer id = 1;
        when(customerService.getCustomer(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomerFrom() throws Exception {
        verifyNoInteractions(customerService);
        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void customerFromEdit() throws Exception {
        Integer id = 1;
        when(customerService.getCustomer(id)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void saveOrUpdateCustomer() throws Exception {
        Integer id = 1;
        String firstName = "Ty";
        String lastName = "Mony";
        String email = "qwerty@hot.com";
        String phoneNumber = "987654321";
        String addressLine1 = "direccion 1";
        String addressLine2 = "direccion 2";
        String city = "lima";
        String state = "arequipa";
        String zipCode = "054";
        Customer customer = new Customer(id, firstName, lastName, email, phoneNumber, addressLine1, addressLine2, city, state, zipCode);
        when(customerService.saveOrUpdateCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform((post("/customer")
                .param("id", "1")
                .param("firstName", "Ty")
                .param("lastName", "Mony")
                .param("email", "qwerty@hot.com")
                .param("phoneNumber", "987654321")
                .param("addressLine1", "direccion 1")
                .param("addressLine2", "direccion 2")
                .param("city", "lima")
                .param("state", "arequipa")
                .param("zipCode", "054")))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("addressLine1", is(addressLine1))))
                .andExpect(model().attribute("customer", hasProperty("addressLine2", is(addressLine2))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))));

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdateCustomer(customerArgumentCaptor.capture());
        assertEquals(id, customerArgumentCaptor.getValue().getId());
        assertEquals(firstName, customerArgumentCaptor.getValue().getFirstName());
        assertEquals(lastName, customerArgumentCaptor.getValue().getLastName());
        assertEquals(email, customerArgumentCaptor.getValue().getEmail());
        assertEquals(phoneNumber, customerArgumentCaptor.getValue().getPhoneNumber());
        assertEquals(addressLine1, customerArgumentCaptor.getValue().getAddressLine1());
        assertEquals(addressLine2, customerArgumentCaptor.getValue().getAddressLine2());
        assertEquals(city, customerArgumentCaptor.getValue().getCity());
        assertEquals(state, customerArgumentCaptor.getValue().getState());
        assertEquals(zipCode, customerArgumentCaptor.getValue().getZipCode());
    }

    @Test
    public void deleteCustomers() throws Exception {
        Integer id = 1;
        mockMvc.perform(post("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers"));
        verify(customerService, times(1)).deleteCustomer(id);
    }
}
