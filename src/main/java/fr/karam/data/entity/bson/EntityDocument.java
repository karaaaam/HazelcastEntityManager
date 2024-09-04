package fr.karam.data.entity.bson;

import org.bson.Document;

import java.util.Map;

public class EntityDocument extends Document {

    public EntityDocument() {}

    public EntityDocument(String key, Object value) {
        super(key, value);
    }

    public EntityDocument(Map<String, Object> map) {
        super(map);
    }

    public static EntityDocument of(Document document) {
        return new EntityDocument(document);
    }

}
