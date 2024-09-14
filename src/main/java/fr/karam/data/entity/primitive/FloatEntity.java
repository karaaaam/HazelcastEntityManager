package fr.karam.data.entity.primitive;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.document.EntityDocument;

public class FloatEntity implements EntitySerializable {

    private float value;

    public FloatEntity() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put("value", value);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        this.value = document.getFloat("value");
    }

}
