package fr.karam.data.type.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.bson.EntityDocument;
import fr.karam.data.type.DataFetcher;
import fr.karam.data.type.DataType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalFetcher extends DataFetcher<LocalCredentials> {

    private final Map<String, Cache<Serializable, EntityDocument>> objectCache = new HashMap<>();

    public LocalFetcher(LocalCredentials credentials) {
        super(DataType.LOCAL, credentials);
    }

    @Override
    public EntityDocument get(String key, Identifiable<?> entityID) {
        return getCache(key).getIfPresent(entityID.getIdentifier());
    }

    @Override
    public void set(String key, Identifiable<?> entityID, EntityDocument entity) {
        getCache(key).put(entityID.getIdentifier(), entity);
    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        return getCache(key).asMap().keySet().stream().map(clazz::cast).toList();
    }

    public Cache<Serializable, EntityDocument> getCache(String key){
        return objectCache.computeIfAbsent(key, k -> Caffeine.newBuilder().expireAfterAccess(credentials.getDuration()).build());
    }

}
