package fr.karam.data.entity;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.entity.document.DocumentByteSerializer;
import fr.karam.data.entity.document.DocumentSerializable;
import fr.karam.data.entity.document.EntityDocument;

import java.io.IOException;

public interface EntitySerializable extends DocumentSerializable, DataSerializable {
    
    @Override
    default void readData(ObjectDataInput input) throws IOException {
        byte[] bytes = input.readByteArray();
        EntityDocument document = DocumentByteSerializer.deserialize(bytes);
        DocumentSerializable.loadSerializable(this, document);
    }

    @Override
    default void writeData(ObjectDataOutput out) throws IOException {
        EntityDocument entityDocument = DocumentSerializable.saveSerializable(this);
        out.writeByteArray(DocumentByteSerializer.serialize(entityDocument));
    }

}
