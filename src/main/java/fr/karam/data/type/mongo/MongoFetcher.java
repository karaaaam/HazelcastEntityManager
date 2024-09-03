package fr.karam.data.type.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.type.DataFetcher;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.Serializable;
import java.util.List;

public class MongoFetcher extends DataFetcher<MongoCredentials> {

    private MongoClient client;

    protected MongoFetcher(MongoCredentials credentials) {
        super(credentials);
    }

    @Override
    public void init() {
        ConnectionString connectionString = new ConnectionString(this.credentials.getUri());
        CodecRegistry pojoCodecProvider = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecProvider);

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        this.client = MongoClients.create(mongoClientSettings);
    }

    @Override
    public SerializableEntity<?> get(String key, Identifiable<?> entityID) {
        return this.getDatabase().getCollection(key).find(Filters.eq("id", entityID.get()));
    }

    @Override
    public void set(String key, SerializableEntity<?> data) {

    }

    @Override
    public List<Serializable> getAll(String key) {
        return null;
    }


    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return this.client.getDatabase(this.credentials.getDatabase());
    }


}
