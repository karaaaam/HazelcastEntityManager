package fr.karam.data.entity.document;


import java.lang.reflect.InvocationTargetException;

public interface DocumentSerializable {

    static final String DOCUMENT_ID = "_id";

    void toDocument(EntityDocument document);

    void fromDocument(EntityDocument document);

    static void loadSerializable(DocumentSerializable serializable, EntityDocument document) {
        serializable.fromDocument(document);
    }

    static <E extends DocumentSerializable> E loadSerializable(EntityDocument document, Class<E> clazz){
        E entity = DocumentSerializable.loadEmptyConstructorClazz(clazz);
        entity.fromDocument(document);

        return entity;
    }

    static <E extends DocumentSerializable> E loadEmptyConstructorClazz(Class<E> clazz){
        E entity;

        try {
            entity = clazz.getConstructor().newInstance();
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
