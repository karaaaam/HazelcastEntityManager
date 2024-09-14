package fr.karam.data.entity.document;


import java.lang.reflect.InvocationTargetException;

public interface DocumentSerializable {

    void toDocument(EntityDocument document);

    void fromDocument(EntityDocument document);

    static void loadSerializable(DocumentSerializable serializable, EntityDocument document) {
        serializable.fromDocument(document);
    }

    static <E extends DocumentSerializable> E loadSerializable(EntityDocument document, Class<E> clazz){
        E entity;

        try {
            entity = clazz.getConstructor().newInstance();
            entity.fromDocument(document);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return entity;
    }

    static EntityDocument saveSerializable(DocumentSerializable serializable){
        EntityDocument entityDocument = new EntityDocument();
        serializable.toDocument(entityDocument);
        return entityDocument;
    }
}
