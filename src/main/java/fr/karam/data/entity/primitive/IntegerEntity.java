package fr.karam.data.entity.primitive;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.document.EntityDocument;

public class IntegerEntity implements EntitySerializable {

    private int value;

    public IntegerEntity() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put("value", value);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        this.value = document.getInteger("value");
    }
}
