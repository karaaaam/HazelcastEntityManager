package fr.karam.data.entity.document;


public interface DocumentSerializable {

    void toDocument(EntityDocument document);

    void fromDocument(EntityDocument document);

}
