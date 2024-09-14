package fr.karam.data.repository.types;

import com.hazelcast.multimap.MultiMap;
import fr.karam.data.HazelcastManager;
import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;

public abstract class MultiMapRepository<K, V extends EntitySerializable> extends Repository<V> {

    private MultiMap<K, V> multiMap;

    public MultiMapRepository(String identifier) {
        super(identifier, RepositoryType.MULTIMAP);
        this.multiMap = HazelcastManager.INSTANCE.getHazelcast().getMultiMap(identifier);
    }
}
