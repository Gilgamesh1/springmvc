package guru.springframework.service.impl;

import guru.springframework.persistence.domain.DomainObject;
import guru.springframework.persistence.domain.Product;
import guru.springframework.service.AbstractMapService;
import guru.springframework.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    protected void loadDomainObjects() {
        Product product1 = new Product(1, "Product 1", new BigDecimal(15456.15), "http://example.com/product1");
        Product product2 = new Product(2, "Product 2", new BigDecimal(6854.516), "http://example.com/product2");
        Product product3 = new Product(3, "Product 3", new BigDecimal(651.51), "http://example.com/product3");
        Product product4 = new Product(4, "Product 4", new BigDecimal(16546.51), "http://example.com/product4");
        domainMap.put(1, product1);
        domainMap.put(2, product2);
        domainMap.put(3, product3);
        domainMap.put(4, product4);
    }

}
