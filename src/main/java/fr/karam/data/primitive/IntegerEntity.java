package fr.karam.data.primitive;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;

public class IntegerEntity implements DataSerializable {

    private int value;

    public IntegerEntity() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeInt(value);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.value = in.readInt();
    }
}
