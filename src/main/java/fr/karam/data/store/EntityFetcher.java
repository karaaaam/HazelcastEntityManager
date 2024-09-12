package fr.karam.data.store;

import com.hazelcast.nio.serialization.DataSerializable;

import java.util.List;

public abstract class EntityFetcher {

    private final FetcherType fetcherType;

    protected EntityFetcher(FetcherType fetcherType) {
        this.fetcherType = fetcherType;
    }

    public FetcherType getFetcherType() {
        return fetcherType;
    }

    public abstract <E extends DataSerializable> E get(String key, Object identifier, Class<E> clazz);

    public abstract void set(String key, Object identifier, DataSerializable entity);

    public abstract <T> List<T> getAllID(String key, Class<T> clazz);

}
