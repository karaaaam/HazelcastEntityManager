package fr.karam.data.type.mongo;

import fr.karam.data.type.DataCredentials;

public class MongoFetcher extends DataCredentials {

    private String uri;
    private String database;

    public MongoFetcher(String uri, String database) {
        this.uri = uri;
        this.database = database;
    }

    public String getUri() {
        return uri;
    }

    public MongoFetcher setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public MongoFetcher setDatabase(String database) {
        this.database = database;
        return this;
    }
}
