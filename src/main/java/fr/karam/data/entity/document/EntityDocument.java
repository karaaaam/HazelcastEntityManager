package fr.karam.data.entity.document;

import org.bson.Document;

import java.io.Serial;
import java.util.Map;
import java.util.UUID;

public class EntityDocument extends Document {

    @Serial
    private static final long serialVersionUID = 4483217778822883350L;

    public EntityDocument() {

    }

    public EntityDocument(String key, Object value) {
        super(key, value);
    }

    public EntityDocument(Map<String, Object> map) {
        super(map);
    }

    public UUID getUUID(Object key){
        return UUID.fromString(this.getString(key));
    }

    @Override
    public Long getLong(Object key) {
        return ((Number) this.get(key)).longValue();
    }

    public Float getFloat(Object key){
        return ((Number) this.get(key)).floatValue();
    }

    public Document asDocument(){
        return this;
    }

    public static EntityDocument fromDocument(Document document){
        return new EntityDocument(document);
    }

}
