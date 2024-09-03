package fr.karam.data.type.file;

import fr.karam.data.type.DataCredentials;

import java.nio.file.Path;

public class FileCredentials extends DataCredentials {

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
