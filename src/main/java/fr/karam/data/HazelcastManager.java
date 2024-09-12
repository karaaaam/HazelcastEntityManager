package fr.karam.data;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
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

    public void newMasterInstance(){
        this.hazelcast = Hazelcast.newHazelcastInstance();
    }

    public void newMasterInstance(Config config){
        this.hazelcast = Hazelcast.newHazelcastInstance(config);
    }

    public void newClientInstance(ClientConfig config){
        this.hazelcast = HazelcastClient.newHazelcastClient(config);
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
