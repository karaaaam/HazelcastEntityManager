package fr.karam.data.utils;

import java.lang.reflect.ParameterizedType;

public interface ClassProvider<A> {

    default Class<A> getGenericClazz() {
        return (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
