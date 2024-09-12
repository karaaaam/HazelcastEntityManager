package fr.karam.data.primitive;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;
import java.util.UUID;

public class UUIDEntity implements DataSerializable {

    private UUID value;

    public UUIDEntity() {
    }

    public UUID getValue() {
        return value;
    }

    public void setValue(UUID value) {
        this.value = value;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(value.toString());
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.value = UUID.fromString(in.readString());
    }
}
