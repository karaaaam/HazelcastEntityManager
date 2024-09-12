package fr.karam.data.repository.types;

import com.hazelcast.collection.IList;
import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.HazelcastManager;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;

public abstract class ListRepository<E extends DataSerializable> extends Repository<E> {

    private IList<E> list;

    public ListRepository(String identifier) {
        super(identifier, RepositoryType.LIST);
        this.list = HazelcastManager.INSTANCE.getHazelcast().getList(identifier);
    }


}
