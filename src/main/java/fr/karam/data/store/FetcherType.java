package fr.karam.data.store;

public enum FetcherType {

    MONGODB("mongodb"),
    SYSTEM_FILE("system_file")

    ;

    private final String identifier;

    FetcherType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
