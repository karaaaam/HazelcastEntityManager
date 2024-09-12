package fr.karam.data.entity.primitive;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;

public class CharEntity implements DataSerializable {

    private char value;

    public CharEntity() {
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeChar(value);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.value = in.readChar();
    }
}
