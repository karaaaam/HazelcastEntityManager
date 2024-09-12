package fr.karam.data.entity.primitive;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;

public class BooleanEntity implements DataSerializable {

    private boolean value;

    public BooleanEntity() {
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeBoolean(value);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.value = in.readBoolean();
    }
}
