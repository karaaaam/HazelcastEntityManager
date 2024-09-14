package fr.karam.data.example;

import fr.karam.data.repository.types.ReferenceRepository;
import fr.karam.data.store.FetcherType;

public class ExampleRepository extends ReferenceRepository<ExampleObject> {

    public ExampleRepository() {
        super("examples", FetcherType.MONGODB);
    }

}
