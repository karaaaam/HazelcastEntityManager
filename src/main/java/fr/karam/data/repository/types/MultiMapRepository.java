package fr.karam.data.repository.types;

import com.hazelcast.multimap.MultiMap;
import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.HazelcastManager;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;

public abstract class MultiMapRepository<K, V extends DataSerializable> extends Repository<V> {

    private MultiMap<K, V> multiMap;

    public MultiMapRepository(String identifier) {
        super(identifier, RepositoryType.MULTIMAP);
        this.multiMap = HazelcastManager.INSTANCE.getHazelcast().getMultiMap(identifier);
    }
}
