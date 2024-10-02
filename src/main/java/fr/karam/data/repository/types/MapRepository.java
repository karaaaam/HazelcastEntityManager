package fr.karam.data.repository.types;

import com.hazelcast.map.IMap;
import fr.karam.data.HazelcastManager;
import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.Identifiable;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;
import fr.karam.data.store.FetcherType;
import fr.karam.data.utils.BiClassProvider;
import fr.karam.data.utils.TTLDuration;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public abstract class MapRepository<K, V extends EntitySerializable & Identifiable<K>> extends Repository<V> implements BiClassProvider<K, V> {

    private final IMap<K, V> map;
    private TTLDuration ttlDuration;

    public MapRepository(String identifier) {
        this(identifier, HazelcastManager.INSTANCE.getDefaultFetcher());
    }

    public MapRepository(String identifier, FetcherType fetcherType) {
        super(identifier, RepositoryType.MAP, fetcherType);
        this.map = HazelcastManager.INSTANCE.getHazelcast().getMap(identifier);
        this.ttlDuration = new TTLDuration(5, TimeUnit.HOURS);
    }

    public Optional<V> get(K key){
        if(!map.containsKey(key) && isStore()){
            V fetch = this.fetch(key);
            if(fetch != null) map.set(key, fetch);
        }

        return Optional.of(map.get(key));
    }

    public Optional<V> getCached(K key){
        return Optional.of(map.get(key));
    }

    @Deprecated()
    public Optional<V> getPersistent(K key){
        if(!isStore()) return Optional.empty();
        return Optional.of(this.fetch(key));
    }

    public void set(V entity){
        if(ttlDuration == null) this.map.set(entity.getID(), entity);
        this.map.set(entity.getID(), entity, ttlDuration.ttlLong(), ttlDuration.ttlUnit());
    }

    public boolean containsKey(K key){
        return this.get(key).isPresent();
    }

    public boolean containsValue(V entity){
        return this.get(entity.getID()).isPresent();
    }

    public void delete(K key){
        this.map.delete(key);
        this.eradicate(key);
    }

    public void delete(V entity){
        this.delete(entity.getID());
    }

    public void create(V entity){
        this.store(entity);
    }

    public void store(V entity){
        this.store(entity.getID(), entity);
    }

    public TTLDuration getTTL() {
        return ttlDuration;
    }

    public void setTTL(TTLDuration ttlDuration) {
        this.ttlDuration = ttlDuration;
    }
}
