package com.onehippo.cms7.genericresource.entitybuilder;

import org.hippoecm.hst.core.request.HstRequestContext;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class GenericResourceEntityBuilder {
    public static GenericResourceEntityBuilder get(HstRequestContext requestContext) {
        return new GenericResourceEntityBuilder(requestContext);
    }

    private HstRequestContext requestContext;
    private Map<String, Object> resourceEntities = new ConcurrentHashMap<>();

    public GenericResourceEntityBuilder(HstRequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public void setResourceEntity(String key, Object value) {
        resourceEntities.put(key, value);
    }

    public void addResourceEntity(String key, Object value) {
        Collection<Object> collection = (Collection<Object>) resourceEntities.get(key);
        if (collection == null) {
            collection = new ConcurrentSkipListSet<>();
            resourceEntities.put(key, collection);
        }

        collection.add(value);
    }

    public Object getResourceEntity(String key) {
        return resourceEntities.get(key);
    }

    public Collection<Object> getCollectionResourceEntity(String key) {
        return (Collection<Object>) resourceEntities.get(key);
    }

    public Map<String, Object> getMapResourceEntity(String key) {
        return (Map<String, Object>) resourceEntities.get(key);
    }
}
