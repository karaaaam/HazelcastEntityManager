package fr.karam.data.repository.types;

import com.hazelcast.cp.IAtomicReference;
import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.HazelcastManager;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;

public abstract class ReferenceRepository<E extends DataSerializable> extends Repository<E> {

    private IAtomicReference<E> reference;
    private static final Object identifier = "object";

    public ReferenceRepository(String identifier) {
        super(identifier, RepositoryType.REFERENCE);
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

}
