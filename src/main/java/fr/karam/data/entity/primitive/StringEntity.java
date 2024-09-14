package fr.karam.data.entity.primitive;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.document.EntityDocument;

public class StringEntity implements EntitySerializable {

    private String value;

    public StringEntity() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put("value", value);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        this.value = document.getString("value");
    }
}
