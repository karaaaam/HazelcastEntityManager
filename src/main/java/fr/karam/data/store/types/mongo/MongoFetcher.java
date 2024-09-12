package fr.karam.data.store.types.mongo;

import com.hazelcast.nio.serialization.DataSerializable;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.ReplaceOptions;
import fr.karam.data.store.FetcherType;
import fr.karam.data.store.EntityFetcher;
import fr.karam.data.utils.GsonUtils;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

public class MongoFetcher extends EntityFetcher {

    private final MongoCredentials credentials;
    private MongoClient client;

    public MongoFetcher(MongoCredentials credentials) {
        super(FetcherType.MONGODB);
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
    public <E extends DataSerializable> E get(String key, Object identifier, Class<E> clazz) {
        Document id = this.client.getDatabase(this.credentials.getDatabase()).getCollection(key).find(new Document("id", identifier.toString())).first();

        return null;
    }

    @Override
    public void set(String key, Object identifier, DataSerializable entity) {
        this.client.getDatabase(this.credentials.getDatabase()).getCollection(key)
                .replaceOne(new Document("id", identifier), Document.parse(GsonUtils.get().toJson(entity)), new ReplaceOptions().upsert(true));
    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        return null;
    }
}
