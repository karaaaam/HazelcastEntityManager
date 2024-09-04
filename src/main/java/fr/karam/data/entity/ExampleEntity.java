package fr.karam.data.entity;

import fr.karam.data.entity.bson.EntityDocument;

import java.util.UUID;

public class ExampleEntity implements SerializableEntity<UUID> {

    @EntityID
    private UUID uuid;

    private String username;
    private float credit;

    public ExampleEntity() {
    }

    @Override
    public void toDocument(EntityDocument document) {
        document.put("id", uuid);
        document.put("username", username);
        document.put("credit", credit);
    }

    @Override
    public void fromDocument(EntityDocument document) {
        ExampleEntity exampleEntity = new ExampleEntity();

        exampleEntity.setUuid(document.get("id", UUID.class));
        exampleEntity.setUsername(document.get("username", String.class));
        exampleEntity.setCredit(document.get("credit", Integer.class));
    }

    @Override
    public UUID getIdentifier() {
        return uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ExampleEntity setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ExampleEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public float getCredit() {
        return credit;
    }

    public ExampleEntity setCredit(float credit) {
        this.credit = credit;
        return this;
    }
}
