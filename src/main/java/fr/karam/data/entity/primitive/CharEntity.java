package fr.karam.data.entity.primitive;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.document.EntityDocument;

public class CharEntity implements EntitySerializable {

    private char value;

    public CharEntity() {
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put("value", value);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        this.value = document.get("value", char.class);
    }
}
