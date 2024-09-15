package fr.karam.data.store.types.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.ReplaceOptions;
import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.document.DocumentSerializable;
import fr.karam.data.entity.document.EntityDocument;
import fr.karam.data.store.FetcherType;
import fr.karam.data.store.EntityFetcher;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

public class MongoFetcher extends EntityFetcher {

    private final MongoCredentials credentials;
    private MongoClient client;

    public final static String IDENTIFIER_KEY = "_id";

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
    public <E extends EntitySerializable> E get(String key, Object identifier, Class<E> clazz) {
        Document document = this.client.getDatabase(this.credentials.getDatabase()).getCollection(key)
                .find(new Document(IDENTIFIER_KEY, identifier.toString())).first();

        if(document == null){
            return null;
        }

        E entity = DocumentSerializable.loadSerializable(EntityDocument.fromDocument(document), clazz);
        return entity;
    }

    @Override
    public void set(String key, Object identifier, EntitySerializable entity) {
        EntityDocument document = new EntityDocument();
        entity.toDocument(document);

        this.client.getDatabase(this.credentials.getDatabase()).getCollection(key)
                .replaceOne(new Document(IDENTIFIER_KEY, identifier), document.asDocument(), new ReplaceOptions().upsert(true));
    }

    @Override
    public void remove(String key, Object identifier) {

    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        return null;
    }

}
