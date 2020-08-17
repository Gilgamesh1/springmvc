package guru.springframework.service;

import guru.springframework.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<Integer, Customer> customerMap;

    public CustomerServiceImpl() {
        loadCustomer();
    }

    private void loadCustomer() {
        Customer customer1 = new Customer(1, "Nombre 1", "Apellidos", "email1@email.com", "897897897", "Direccion 1", "Direccion 2", "Ciudad 1", "Estado 1", "Zip Code 1");
        Customer customer2 = new Customer(2, "Nombre 2", "Apellidos", "email2@email.com", "897897897", "Direccion 2", "Direccion 2", "Ciudad 2", "Estado 2", "Zip Code 2");
        Customer customer3 = new Customer(3, "Nombre 3", "Apellidos", "email3@email.com", "897897897", "Direccion 3", "Direccion 3", "Ciudad 3", "Estado 3", "Zip Code 3");
        Customer customer4 = new Customer(4, "Nombre 4", "Apellidos", "email4@email.com", "897897897", "Direccion 4", "Direccion 4", "Ciudad 4", "Estado 4", "Zip Code 4");
        customerMap = new HashMap<>();
        customerMap.put(1, customer1);
        customerMap.put(2, customer2);
        customerMap.put(3, customer3);
        customerMap.put(4, customer4);
    }

    @Override
    public List<Customer> listAllCustomer() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomer(Integer id) {
        return customerMap.get(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if (customer != null) {
            if (customer.getId() == null) {
                customer.setId(generedId());
            }
            return customerMap.put(customer.getId(), customer);
        } else {
            throw new RuntimeException("No datos del cliente");
        }
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerMap.remove(id);
    }

    public int generedId() {
        return Collections.max(customerMap.keySet());
    }
}
