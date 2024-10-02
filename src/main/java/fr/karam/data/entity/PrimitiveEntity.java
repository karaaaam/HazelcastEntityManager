package fr.karam.data.entity;

import fr.karam.data.entity.document.EntityDocument;
import fr.karam.data.utils.ClassProvider;

public abstract class PrimitiveEntity<T> implements EntitySerializable, ClassProvider<T> {

    protected T value;

    public PrimitiveEntity() {

    }

    public PrimitiveEntity(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put(DOCUMENT_ID, value);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        document.get(DOCUMENT_ID, getGenericClazz());
    }

}
