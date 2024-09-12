package fr.karam.data.example;

import com.google.gson.annotations.SerializedName;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import fr.karam.data.EntityID;
import fr.karam.data.utils.SerializationUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ExampleObject implements DataSerializable {

    @SerializedName("id")
    private UUID uuid;

    private String name;
    private int credit;
    private List<String> subEntities;
    private Map<String, String> keyWords;

    public ExampleObject() {

    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeString(uuid.toString());
        out.writeString(name);
        out.writeInt(credit);
        SerializationUtils.writeList(out, subEntities);
        SerializationUtils.writeMap(out, keyWords);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        this.uuid = UUID.fromString(in.readString());
        this.name = in.readString();
        this.credit = in.readInt();
        this.subEntities = SerializationUtils.readList(in);
        this.keyWords = SerializationUtils.readMap(in);
    }

    public UUID getUuid() {
        return uuid;
    }

    public ExampleObject setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ExampleObject setName(String name) {
        this.name = name;
        return this;
    }

    public int getCredit() {
        return credit;
    }

    public ExampleObject setCredit(int credit) {
        this.credit = credit;
        return this;
    }

    public List<String> getSubEntities() {
        return subEntities;
    }

    public ExampleObject setSubEntities(List<String> subEntities) {
        this.subEntities = subEntities;
        return this;
    }

    public Map<String, String> getKeyWords() {
        return keyWords;
    }

    public ExampleObject setKeyWords(Map<String, String> keyWords) {
        this.keyWords = keyWords;
        return this;
    }
}
