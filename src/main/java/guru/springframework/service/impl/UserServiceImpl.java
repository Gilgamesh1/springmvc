package guru.springframework.service.impl;

import guru.springframework.persistence.domain.DomainObject;
import guru.springframework.persistence.domain.User;
import guru.springframework.service.AbstractMapService;
import guru.springframework.service.ProductService;
import guru.springframework.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class UserServiceImpl extends AbstractMapService implements UserService {
    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    protected void loadDomainObjects() {
        User user1 = new User(1, "Product 1", "Password");
        User user2 = new User(2, "Product 2", "Password");
        User user3 = new User(3, "Product 3", "Password");
        User user4 = new User(4, "Product 4", "Password");
        domainMap.put(1, user1);
        domainMap.put(2, user2);
        domainMap.put(3, user3);
        domainMap.put(4, user4);
    }
}
