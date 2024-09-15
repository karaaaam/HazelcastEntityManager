package fr.karam.data.store;

import fr.karam.data.entity.EntitySerializable;

import java.util.List;

public abstract class EntityFetcher {

    private final FetcherType fetcherType;

    protected EntityFetcher(FetcherType fetcherType) {
        this.fetcherType = fetcherType;
    }

    public FetcherType getFetcherType() {
        return fetcherType;
    }

    public abstract <E extends EntitySerializable> E get(String key, Object identifier, Class<E> clazz);

    public abstract void set(String key, Object identifier, EntitySerializable entity);

    public abstract void remove(String key, Object identifier);

    public abstract <T> List<T> getAllID(String key, Class<T> clazz);

}
