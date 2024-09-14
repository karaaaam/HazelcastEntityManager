package fr.karam.data.entity.primitive;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.document.EntityDocument;

public class LongEntity implements EntitySerializable {

    private long value;

    public LongEntity() {
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put("value", value);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        this.value = document.getLong("value");
    }
}
