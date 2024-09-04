package fr.karam.data;

import fr.karam.data.type.DataFetcher;
import fr.karam.data.type.DataType;

import java.util.HashMap;
import java.util.Map;

public class EntityManager {

    private final Map<DataType, DataFetcher<?>> registeredDataTypes = new HashMap<>();

    public void registerDataFetcher(DataFetcher<?> dataFetcher){
        this.registeredDataTypes.put(dataFetcher.getType(), dataFetcher);
    }

    public DataFetcher<?> getDataFetcher(DataType dataType){
        return this.registeredDataTypes.get(dataType);
    }

}
