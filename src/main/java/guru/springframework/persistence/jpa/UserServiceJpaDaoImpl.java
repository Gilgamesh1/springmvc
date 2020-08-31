package guru.springframework.persistence.jpa;

import guru.springframework.persistence.domain.User;
import guru.springframework.service.EncryptionService;
import guru.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl implements UserService {
    private EntityManagerFactory emf;
    private EncryptionService encryptionService;

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<User> listAll() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (domainObject.getPassword() != null) {
            domainObject.setStrongPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        entityManager.merge(domainObject);
        entityManager.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class, id));
        entityManager.getTransaction().commit();
    }
}
