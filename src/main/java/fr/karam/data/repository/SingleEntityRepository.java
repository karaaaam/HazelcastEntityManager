package fr.karam.data.repository;

import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.process.EntityProcessor;

public abstract class SingleEntityRepository<E extends SerializableEntity<?>, P extends EntityProcessor<E>> {
}
