package guru.springframework.service;

import guru.springframework.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listAllCustomer();

    Customer getCustomer(Integer id);

    Customer saveOrUpdateCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
