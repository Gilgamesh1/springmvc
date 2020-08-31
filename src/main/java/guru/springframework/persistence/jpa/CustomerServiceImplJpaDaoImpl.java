package guru.springframework.persistence.jpa;

import guru.springframework.persistence.domain.Customer;
import guru.springframework.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceImplJpaDaoImpl implements CustomerService {
    private EntityManagerFactory emf;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Customer customer = entityManager.merge(domainObject);
        entityManager.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Customer.class, id));
        entityManager.getTransaction().commit();
    }
}
