package fr.karam.data;

import fr.karam.data.example.ExampleObject;
import fr.karam.data.repository.types.ReferenceRepository;
import fr.karam.data.store.FetcherType;

public class TestEntityRepository extends ReferenceRepository<ExampleObject> {

    public TestEntityRepository() {
        super("testEntity", FetcherType.MONGODB);
    }

}
