package fr.karam.data.repository.types;

import com.hazelcast.cp.IAtomicReference;
import fr.karam.data.HazelcastManager;
import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;
import fr.karam.data.store.FetcherType;

import java.util.Optional;

public abstract class ReferenceRepository<E extends EntitySerializable> extends Repository<E> {

    private final IAtomicReference<E> reference;
    private static final Object ENTITY_IDENTIFIER = "entity";

    public ReferenceRepository(String identifier) {
        this(identifier, null);
    }

    public ReferenceRepository(String identifier, FetcherType fetcherType) {
        super(identifier, RepositoryType.REFERENCE, fetcherType);
        this.reference = HazelcastManager.INSTANCE.getHazelcast().getCPSubsystem().getAtomicReference(identifier);
    }

    public E get(){
        if(isNull() && isStore())
            reference.set(this.fetch(ENTITY_IDENTIFIER));

        return reference.get();
    }

    public Optional<E> getLocal(){
        return Optional.of(reference.get());
    }

    @Deprecated()
    public E getPersistent(){
        return this.fetch(ENTITY_IDENTIFIER);
    }

    public void set(E entity){
        reference.set(entity);
    }

    public E getAndSet(E entity){
        return reference.getAndSet(entity);
    }

    public E update(E entity){
        return this.getAndSet(entity);
    }

    public boolean contains(E entity){
        return reference.contains(entity);
    }

    public boolean isNull(){
        return reference.isNull();
    }

    public void store(E entity){
        this.store(ENTITY_IDENTIFIER, entity);
    }

    @Override
    protected E fetch(Object entityID) {
        E fetch = super.fetch(entityID);

        if(fetch == null) {
            //TODO: create new
        }

        return fetch;
    }
}
