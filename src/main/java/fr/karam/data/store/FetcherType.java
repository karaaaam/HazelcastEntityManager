package fr.karam.data.store;

public enum FetcherType {

    MONGO("mongodb")

    ;

    private final String identifier;

    FetcherType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
