package fr.karam.data;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import fr.karam.data.store.EntityFetcher;
import fr.karam.data.store.FetcherType;

import java.util.Map;

public enum HazelcastManager {

    INSTANCE;

    private HazelcastInstance hazelcast;
    private Map<FetcherType, EntityFetcher> registeredFetchers;

    public HazelcastInstance getHazelcast(){
        if(hazelcast == null) throw new RuntimeException("hazelcast was never instanced");
        return hazelcast;
    }

    public void newInstance(){
        this.hazelcast = Hazelcast.newHazelcastInstance();
    }

    public void newInstance(Config config){
        this.hazelcast = Hazelcast.newHazelcastInstance(config);
    }

    public void registerFetcher(EntityFetcher entityFetcher){
        registeredFetchers.put(entityFetcher.getFetcherType(), entityFetcher);
    }

    public EntityFetcher getFetcher(FetcherType type){
        EntityFetcher entityFetcher = registeredFetchers.get(type);
        if(entityFetcher == null) throw new RuntimeException(type.getIdentifier() + " fetcher type was never instanced");
        return entityFetcher;
    }
}
