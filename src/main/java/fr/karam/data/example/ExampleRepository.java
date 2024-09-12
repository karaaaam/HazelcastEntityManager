package fr.karam.data.example;

import fr.karam.data.repository.types.MapRepository;

import java.util.UUID;

public class ExampleRepository extends MapRepository<UUID, ExampleObject> {

    public ExampleRepository() {
        super("examples");
    }

}
