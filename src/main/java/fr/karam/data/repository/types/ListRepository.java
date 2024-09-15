package fr.karam.data.repository.types;

import com.hazelcast.collection.IList;
import fr.karam.data.HazelcastManager;
import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;
import fr.karam.data.store.FetcherType;

public abstract class ListRepository<E extends EntitySerializable> extends Repository<E> {

    private IList<E> list;

    public ListRepository(String identifier) {
        this(identifier, null);
    }

    public ListRepository(String identifier, FetcherType fetcherType) {
        super(identifier, RepositoryType.LIST, fetcherType);
        this.list = HazelcastManager.INSTANCE.getHazelcast().getList(identifier);
    }

    public void a(){

    }


}
