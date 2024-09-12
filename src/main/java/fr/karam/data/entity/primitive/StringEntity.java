package fr.karam.data.entity.primitive;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;

public class StringEntity implements DataSerializable {

    private String value;

    public StringEntity() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(value);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.value = in.readString();
    }
}
