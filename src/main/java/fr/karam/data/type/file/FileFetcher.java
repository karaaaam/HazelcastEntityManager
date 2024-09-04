package fr.karam.data.type.file;

import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.entity.bson.EntityDocument;
import fr.karam.data.type.DataFetcher;
import fr.karam.data.type.DataType;
import fr.karam.data.utils.FileContentUtils;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFetcher extends DataFetcher<FileCredentials> {

    public FileFetcher(FileCredentials credentials) {
        super(DataType.SYSTEM_FILE, credentials);
    }

    @Override
    public EntityDocument get(String key, Identifiable<?> entityID) {
        File directory = new File(credentials.getPath().toFile(), key);

        if(!directory.exists()) {
            return null;
        }

        File entityFile = new File(directory, entityID.getIdentifier().toString());

        if(!entityFile.exists()){
            return null;
        }

        String fileContent = FileContentUtils.getFileContent(entityFile);
        return EntityDocument.of(Document.parse(fileContent));
    }


    @Override
    public void set(String key, Identifiable<?> entityID, EntityDocument entity) {
        File directory = new File(credentials.getPath().toFile(), key);

        if(!directory.exists()) {
            return;
        }

        File entityFile = new File(directory, entityID.getIdentifier().toString());

        if(!entityFile.exists()){
            try {
                boolean ignored = entityFile.createNewFile();
            } catch (IOException ignored) {

            }
            return;
        }

        FileContentUtils.setFileContent(entityFile, entity.toJson());
    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        File directory = new File(credentials.getPath().toFile(), key);

        if(!directory.exists()) {
            return null;
        }

        List<T> entityList = new ArrayList<>();
        File[] files = directory.listFiles();

        for (File file : files) {
            entityList.add((T) file.getName());
        }

        return entityList;
    }


}
