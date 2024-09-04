package fr.karam.data.type;

import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.entity.bson.EntityDocument;

import java.util.List;

public abstract class DataFetcher<C extends DataCredentials> {

    private final DataType type;
    protected final C credentials;

    public DataFetcher(DataType type, C credentials) {
        this.type = type;
        this.credentials = credentials;
    }

    public void init(){

    }

    public abstract EntityDocument get(String key, Identifiable<?> entityID);

    public abstract void set(String key, Identifiable<?> entityID, EntityDocument entity);

    public abstract <T> List<T> getAllID(String key, Class<T> clazz);

    public DataType getType() {
        return type;
    }

    public C getCredentials() {
        return credentials;
    }
}
