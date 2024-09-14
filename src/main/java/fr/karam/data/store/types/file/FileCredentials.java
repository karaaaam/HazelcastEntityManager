package fr.karam.data.store.types.file;

import java.nio.file.Path;

public class FileCredentials {

    private Path path;

    public FileCredentials(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public FileCredentials setPath(Path path) {
        this.path = path;
        return this;
    }
}
