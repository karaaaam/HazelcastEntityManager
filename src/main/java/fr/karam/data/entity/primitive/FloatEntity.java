package fr.karam.data.entity.primitive;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;

public class FloatEntity implements DataSerializable {

    private float value;

    public FloatEntity() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeFloat(value);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.value = in.readFloat();
    }
}
