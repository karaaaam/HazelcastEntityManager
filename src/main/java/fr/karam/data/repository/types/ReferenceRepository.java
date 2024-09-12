package fr.karam.data.repository.types;

import com.hazelcast.cp.IAtomicReference;
import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.HazelcastManager;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;
import fr.karam.data.store.FetcherType;

public abstract class ReferenceRepository<E extends DataSerializable> extends Repository<E> {

    private IAtomicReference<E> reference;
    private static final Object identifier = "entity";

    public ReferenceRepository(String identifier) {
        this(identifier, null);
    }

    public ReferenceRepository(String identifier, FetcherType fetcherType) {
        super(identifier, RepositoryType.REFERENCE, fetcherType);
        this.reference = HazelcastManager.INSTANCE.getHazelcast().getCPSubsystem().getAtomicReference(identifier);
    }

    public E get(){
        if(isNull()) reference.set(this.fetch(identifier));
        return reference.get();
    }

    public void set(E entity){
        reference.set(entity);
    }

    public E getAndSet(E entity){
        return reference.getAndSet(entity);
    }

    public boolean contains(E entity){
        return reference.contains(entity);
    }

    public boolean isNull(){
        return reference.isNull();
    }

    public void store(E entity){
        this.store(identifier, entity);
    }

}
