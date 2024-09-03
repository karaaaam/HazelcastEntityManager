package fr.karam.data.type;

import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;

import java.io.Serializable;
import java.util.List;

public abstract class DataFetcher<C extends DataCredentials> {

    protected final C credentials;

    protected DataFetcher(C credentials) {
        this.credentials = credentials;
    }

    public void init(){

    }

    public abstract SerializableEntity<?> get(String key, Identifiable<?> entityID);
    public abstract void set(String key, SerializableEntity<?> data);
    public abstract List<Serializable> getAll(String key);

}
