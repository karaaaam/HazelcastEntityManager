package fr.karam.data.utils;

import java.lang.reflect.ParameterizedType;

public interface BiClassProvider<A, B> {

    default Class<A> getGenericLeftClazz() {
        return (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    default Class<B> getGenericRightClazz() {
        return (Class<B>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

}
