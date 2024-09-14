package fr.karam.data.entity.document;


public interface DocumentSerializable {

    void toDocument(EntityDocument document);

    void fromDocument(EntityDocument document);

    static void loadSerializable(DocumentSerializable serializable, EntityDocument document) {
        serializable.fromDocument(document);
    }

    static EntityDocument saveSerializable(DocumentSerializable serializable){
        EntityDocument entityDocument = new EntityDocument();
        serializable.toDocument(entityDocument);
        return entityDocument;
    }
}
