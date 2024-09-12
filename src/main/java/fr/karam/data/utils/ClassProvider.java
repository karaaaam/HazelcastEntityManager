package fr.karam.data.utils;

import java.lang.reflect.ParameterizedType;

public interface ClassProvider<V> {

    default Class<V> getClazz() {
        return (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
