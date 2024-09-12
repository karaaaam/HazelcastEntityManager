package fr.karam.data.store.types.mongo;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import fr.karam.data.store.EntityFetcher;
import fr.karam.data.store.FetcherType;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

public class MongoFetcher extends EntityFetcher {

    private final MongoCredentials credentials;
    private MongoClient client;

    public MongoFetcher(MongoCredentials credentials) {
        super(FetcherType.MONGO);
        this.credentials = credentials;
    }

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
    public DataSerializable get(String key, Object identifier) {
        return null;
    }

    @Override
    public void set(String key, Object identifier, DataSerializable entity) {

    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        return null;
    }
}
