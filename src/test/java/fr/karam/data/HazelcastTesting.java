package fr.karam.data;

import fr.karam.data.store.types.mongo.MongoCredentials;
import fr.karam.data.store.types.mongo.MongoFetcher;
import org.junit.Test;

public class HazelcastTesting {

    private TestEntityRepository testEntityRepository;

    @Test
    public void test(){
        HazelcastManager.INSTANCE.newMasterInstance();
        HazelcastManager.INSTANCE.registerFetcher(new MongoFetcher(new MongoCredentials("mongodb+srv://karam-personal:uF0i5qBeleaPN9Ya@karam-cluster.smrrqcg.mongodb.net/?retryWrites=true&w=majority&appName=karam-cluster",
                "hazelcast")));

        this.testEntityRepository = new TestEntityRepository();


    }
}
