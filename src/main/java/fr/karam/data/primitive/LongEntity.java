package fr.karam.data.primitive;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;

public class LongEntity implements DataSerializable {

    private long value;

    public LongEntity() {
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeLong(value);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.value = in.readLong();
    }
}
