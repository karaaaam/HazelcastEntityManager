package fr.karam.data.type.mongo;

import fr.karam.data.type.DataCredentials;

public class MongoCredentials extends DataCredentials {

    private String uri;
    private String database;

    public MongoCredentials(String uri, String database) {
        this.uri = uri;
        this.database = database;
    }

    public String getUri() {
        return uri;
    }

    public MongoCredentials setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public MongoCredentials setDatabase(String database) {
        this.database = database;
        return this;
    }
}
