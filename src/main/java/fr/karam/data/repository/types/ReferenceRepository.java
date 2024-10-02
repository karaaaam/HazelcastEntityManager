package fr.karam.data.repository.types;

import com.hazelcast.map.IMap;
import fr.karam.data.HazelcastManager;
import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.repository.Repository;
import fr.karam.data.repository.RepositoryType;
import fr.karam.data.store.FetcherType;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class ReferenceRepository<E extends EntitySerializable> extends Repository<E> {

    private final IMap<String, E> reference;
    private static final String ENTITY_IDENTIFIER = "entity";

    public ReferenceRepository(String identifier) {
        this(identifier, HazelcastManager.INSTANCE.getDefaultFetcher());
    }

    public ReferenceRepository(String identifier, FetcherType fetcherType) {
        super(identifier, RepositoryType.REFERENCE, fetcherType);
        this.reference = HazelcastManager.INSTANCE.getHazelcast().getMap(identifier);
    }

    public E get(){
        if(isNull() && isStore())
            reference.put(ENTITY_IDENTIFIER, this.fetch(ENTITY_IDENTIFIER));

        return reference.get(ENTITY_IDENTIFIER);
    }

    public Optional<E> getLocal(){
        return Optional.of(reference.get(ENTITY_IDENTIFIER));
    }

    @Deprecated()
    public E getPersistent(){
        return this.fetch(ENTITY_IDENTIFIER);
    }

    public void set(E entity){
        reference.put(ENTITY_IDENTIFIER, entity);
    }

    /*public E getAndSet(E entity){
        return reference.getAndSet(entity);
    }*/

    public boolean contains(E entity){
        return reference.get(ENTITY_IDENTIFIER) == entity;
    }

    public boolean isNull(){
        return reference.get(ENTITY_IDENTIFIER) == null;
    }

    public void store(E entity){
        this.store(ENTITY_IDENTIFIER, entity);
    }

    @Override
    public E fetch(Object entityID) {
        if(!isStore()) return null;

        E fetch = super.fetch(entityID);

        if(fetch == null) {
            E e = defaultEntity().get();
            this.store(e);
            return e;
        }

        return fetch;
    }

    public abstract Supplier<E> defaultEntity();

}
