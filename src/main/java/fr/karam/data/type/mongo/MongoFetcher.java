package fr.karam.data.type.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.entity.bson.EntityDocument;
import fr.karam.data.type.DataFetcher;
import fr.karam.data.type.DataType;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

public class MongoFetcher extends DataFetcher<MongoCredentials> {

    private MongoClient client;

    public MongoFetcher(MongoCredentials credentials) {
        super(DataType.MONGODB, credentials);
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
    public EntityDocument get(String key, Identifiable<?> entityID) {
        Document document = this.getDatabase().getCollection(key).find(Filters.eq("id", entityID.getIdentifier())).first();

        if(document == null){
            return null;
        }

        return EntityDocument.of(document);
    }

    @Override
    public void set(String key, Identifiable<?> entityID, EntityDocument entity) {
        this.getDatabase().getCollection(key).replaceOne(new Document("id", entityID.getIdentifier()), entity);
    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        MongoCollection<Document> collection = this.getDatabase().getCollection(key);

        FindIterable<Document> documents = collection.find();

        List<T> ids = new ArrayList<>();
        for (Document document : documents) {
            ids.add(document.get("id", clazz));
        }

        return ids;
    }


    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return this.client.getDatabase(this.credentials.getDatabase());
    }


}
