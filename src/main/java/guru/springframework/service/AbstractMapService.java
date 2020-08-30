package guru.springframework.service;

import guru.springframework.domain.Product;

import java.util.*;

public abstract class AbstractMapService {
    protected Map<Integer, Product> domainMap;
    protected abstract void loadDomainObjects();

    public AbstractMapService() {
        domainMap = new HashMap<>();
        loadDomainObjects();
    }

    public List<Product> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public Product getById(Integer id) {
        return domainMap.get(id);
    }

    public Product saveOrUpdate(Product domainObject) {
        if (domainObject != null){
            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);
            return domainObject;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(Integer id) {
        domainMap.remove(id);
    }

    private Integer getNextKey(){
        return Collections.max(domainMap.keySet()) + 1;
    }

}
