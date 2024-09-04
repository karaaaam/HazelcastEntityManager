package fr.karam.data.entity;

import fr.karam.data.entity.bson.EntityDocument;

import java.io.Serializable;

public interface SerializableEntity<ID extends Serializable> extends Identifiable<ID> {


    void toDocument(EntityDocument document);

    void fromDocument(EntityDocument document);

}
