package fr.karam.data.store.types.file;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.store.EntityFetcher;
import fr.karam.data.store.FetcherType;

import java.io.File;
import java.util.List;

public class FileFetcher extends EntityFetcher {

    private final FileCredentials fileCredentials;

    protected FileFetcher(FileCredentials fileCredentials) {
        super(FetcherType.SYSTEM_FILE);
        this.fileCredentials = fileCredentials;
    }

    @Override
    public <E extends EntitySerializable> E get(String key, Object identifier, Class<E> clazz) {
        File directory = new File(fileCredentials.getPath().toFile(), key);
        if(directory.exists()) directory.mkdir();


        return null;
    }

    @Override
    public void set(String key, Object identifier, EntitySerializable entity) {

    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        return null;
    }
}
