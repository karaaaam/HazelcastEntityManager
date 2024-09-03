package fr.karam.data.type;

import fr.karam.data.type.file.FileCredentials;
import fr.karam.data.type.file.FileFetcher;
import fr.karam.data.type.mongo.MongoFetcher;
import fr.karam.data.type.mongo.MongoCredentials;

public enum PersistentDataType {

    MONGODB(MongoCredentials.class, MongoFetcher.class),
    SYSTEM_FILE(FileCredentials.class, FileFetcher.class)

    ;

    private final Class<? extends DataCredentials> credentials;
    private final Class<? extends DataFetcher<?>> connection;

    PersistentDataType(Class<? extends DataCredentials> credentials, Class<? extends DataFetcher<?>> connection) {
        this.credentials = credentials;
        this.connection = connection;
    }

}
