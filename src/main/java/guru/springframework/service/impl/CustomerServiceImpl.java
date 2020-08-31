package guru.springframework.service.impl;

import guru.springframework.persistence.domain.Customer;
import guru.springframework.persistence.domain.DomainObject;
import guru.springframework.service.AbstractMapService;
import guru.springframework.service.CustomerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    public void loadDomainObjects() {
        Customer customer1 = new Customer(1, "Nombre 1", "Apellidos", "email1@email.com", "897897897", "Direccion 1", "Direccion 2", "Ciudad 1", "Estado 1", "Zip Code 1");
        Customer customer2 = new Customer(2, "Nombre 2", "Apellidos", "email2@email.com", "897897897", "Direccion 2", "Direccion 2", "Ciudad 2", "Estado 2", "Zip Code 2");
        Customer customer3 = new Customer(3, "Nombre 3", "Apellidos", "email3@email.com", "897897897", "Direccion 3", "Direccion 3", "Ciudad 3", "Estado 3", "Zip Code 3");
        Customer customer4 = new Customer(4, "Nombre 4", "Apellidos", "email4@email.com", "897897897", "Direccion 4", "Direccion 4", "Ciudad 4", "Estado 4", "Zip Code 4");
        domainMap.put(1, customer1);
        domainMap.put(2, customer2);
        domainMap.put(3, customer3);
        domainMap.put(4, customer4);
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        return (Customer) super.saveOrUpdate(customer);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
