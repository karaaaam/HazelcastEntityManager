package fr.karam.data.entity.document;

import org.bson.Document;

import java.io.Serial;
import java.util.Map;

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

    public Document asDocument(){
        return this;
    }

    public EntityDocument fromDocument(Document document){
        return new EntityDocument(document);
    }
}
