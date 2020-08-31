package guru.springframework.service;

import guru.springframework.persistence.domain.DomainObject;

import java.util.*;

public abstract class AbstractMapService {
    protected Map<Integer, DomainObject> domainMap;
    protected abstract void loadDomainObjects();

    public AbstractMapService() {
        domainMap = new HashMap<>();
        loadDomainObjects();
    }

    public List<DomainObject> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public DomainObject getById(Integer id) {
        return domainMap.get(id);
    }

    public DomainObject saveOrUpdate(DomainObject domainObject) {
        if (domainObject != null){
            if (domainObject.getId() == null){
                domainObject.setId(getNextKey());
            }
            domainMap.put(domainObject.getId(), domainObject);
            return domainObject;
        } else {
            throw new RuntimeException("El objeto no puede ser null");
        }
    }

    public void delete(Integer id) {
        domainMap.remove(id);
    }

    private Integer getNextKey(){
        return Collections.max(domainMap.keySet()) + 1;
    }

}
