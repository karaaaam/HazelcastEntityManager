package fr.karam.data.example;

import fr.karam.data.repository.types.ReferenceRepository;
import fr.karam.data.store.FetcherType;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;

public class ExampleRepository extends ReferenceRepository<ExampleObject> {

    public ExampleRepository() {
        super("examples", FetcherType.MONGODB);
    }

    @Override
    public Supplier<ExampleObject> defaultEntity() {
        return () -> new ExampleObject(UUID.randomUUID(), "", 0, Collections.emptyList(), new HashMap<>());
    }
}
