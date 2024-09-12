package fr.karam.data.utils;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationUtils {

    public static <K, V> void writeMap(ObjectDataOutput out, Map<K, V> map) throws IOException {
        out.writeInt(map.size());

        for (Map.Entry<K, V> entry : map.entrySet()) {
            out.writeObject(entry.getKey());
            out.writeObject(entry.getValue());
        }
    }

    public static <K, V> Map<K, V> readMap(ObjectDataInput in) throws IOException {
        int size = in.readInt();
        Map<K, V> map = new HashMap<>(size);

        for (int i = 0; i < size; i++) {
            K key = in.readObject();
            V value = in.readObject();
            map.put(key, value);
        }
        return map;
    }

    public static <T> void writeList(ObjectDataOutput out, List<T> list) throws IOException {
        // Écrire la taille de la liste
        out.writeInt(list.size());

        // Parcourir chaque élément de la liste et écrire l'objet
        for (T item : list) {
            out.writeObject(item);
        }
    }

    public static <T> List<T> readList(ObjectDataInput in) throws IOException {
        return SerializationUtils.readList(in, new ArrayList<>());
    }

    public static <T> List<T> readList(ObjectDataInput in, List<T> list) throws IOException {
        int size = in.readInt();

        for (int i = 0; i < size; i++) {
            list.add(in.readObject());
        }
        return list;
    }
}