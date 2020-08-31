package guru.springframework.persistence.jpa;

import guru.springframework.persistence.domain.Product;
import guru.springframework.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl implements ProductService {
    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Product> listAll() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Product productSave = entityManager.merge(product);
        entityManager.getTransaction().commit();
        return productSave;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Product.class, id));
        entityManager.getTransaction().commit();
    }
}
