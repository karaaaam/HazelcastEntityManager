package fr.karam.data.process;

import fr.karam.data.EntityManager;
import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.entity.bson.EntityDocument;
import fr.karam.data.type.DataFetcher;
import fr.karam.data.type.DataType;
import fr.karam.data.utils.ClassProvider;

import java.io.Serializable;
import java.util.List;

public abstract class EntityProcessor<E extends SerializableEntity<?>> implements ClassProvider<E> {

    private final EntityManager entityManager;
    private final List<DataType> fetchers;

    protected EntityProcessor(EntityManager entityManager, List<DataType> fetchers) {
        this.entityManager = entityManager;
        this.fetchers = fetchers;
    }

    public E process(Identifiable<?> identifiable){

        for (DataType type : fetchers) {
            DataFetcher<?> dataFetcher = entityManager.getDataFetcher(type);
            if(dataFetcher == null) throw new RuntimeException("Some datafetcher used in processor does not exist");

            EntityDocument entityDocument = dataFetcher.get("a", identifiable);
            if(entityDocument == null){
                continue;
            }

        }
    }

}
