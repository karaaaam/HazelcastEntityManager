package fr.karam.data.type.file;

import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.type.DataFetcher;

import java.io.Serializable;
import java.util.List;

public class FileFetcher extends DataFetcher<FileCredentials> {

    protected FileFetcher(FileCredentials credentials) {
        super(credentials);
    }

    @Override
    public SerializableEntity<?> get(String key, Identifiable<?> entityID) {
        return null;
    }

    @Override
    public void set(String key, SerializableEntity<?> data) {

    }

    @Override
    public List<Serializable> getAll(String key) {
        return null;
    }


}
