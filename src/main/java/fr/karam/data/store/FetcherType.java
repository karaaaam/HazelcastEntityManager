package fr.karam.data.store;

public enum FetcherType {

    MONGODB("mongodb")

    ;

    private final String identifier;

    FetcherType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
