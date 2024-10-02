package fr.karam.data.store.types.file;

import fr.karam.data.entity.EntitySerializable;
import fr.karam.data.entity.document.DocumentSerializable;
import fr.karam.data.entity.document.EntityDocument;
import fr.karam.data.store.EntityFetcher;
import fr.karam.data.store.FetcherType;
import fr.karam.data.utils.FileContentUtils;
import org.bson.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFetcher extends EntityFetcher {

    private final FileCredentials fileCredentials;
    private final static String FILE_EXTENSION = ".json";

    public FileFetcher(FileCredentials fileCredentials) {
        super(FetcherType.SYSTEM_FILE);
        this.fileCredentials = fileCredentials;
    }

    @Override
    public <E extends EntitySerializable> E get(String key, Object identifier, Class<E> clazz) {
        File directory = new File(fileCredentials.getPath().toFile(), key);
        if(!directory.exists()) directory.mkdir();

        File entityFile = new File(directory, identifier.toString() + FILE_EXTENSION);
        if(!entityFile.exists()) FileContentUtils.createFile(entityFile);

        String fileContent = FileContentUtils.getFileContent(entityFile);

        System.out.println("file content= " + fileContent);
        if(fileContent.equals("")) return null;
        EntityDocument entityDocument = EntityDocument.fromDocument(Document.parse(fileContent));

        return DocumentSerializable.loadSerializable(entityDocument, clazz);
    }

    @Override
    public void set(String key, Object identifier, EntitySerializable entity) {
        File directory = new File(fileCredentials.getPath().toFile(), key);
        if(!directory.exists()) directory.mkdir();

        File entityFile = new File(directory, identifier.toString() + FILE_EXTENSION);
        if(!entityFile.exists()) FileContentUtils.createFile(entityFile);

        EntityDocument entityDocument = new EntityDocument();
        entity.toDocument(entityDocument);

        FileContentUtils.setFileContent(entityFile, entityDocument.toJson());
    }

    @Override
    public void remove(String key, Object identifier) {
        File directory = new File(fileCredentials.getPath().toFile(), key);
        if(!directory.exists()) directory.mkdir();

        File entityFile = new File(directory, identifier.toString() + FILE_EXTENSION);
        if(!entityFile.exists()) return;

        entityFile.delete();
    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        List<T> idList = new ArrayList<>();

        File directory = new File(fileCredentials.getPath().toFile(), key);
        if (!directory.exists()) return idList;

        File[] files = directory.listFiles();
        if (files == null) return idList;

        for (File file : files) {
            String fileName = file.getName();
            try {
                T id = clazz.cast(fileName);
                idList.add(id);
            } catch (ClassCastException e) {
                System.err.println("Could not cast file name to type: " + clazz.getSimpleName());
            }
        }

        return null;
    }
}
