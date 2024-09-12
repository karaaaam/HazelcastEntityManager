package fr.karam.data.repository;

import fr.karam.data.store.EntityFetcher;
import fr.karam.data.store.FetcherType;

public interface IRepository {

    String getIdentifier();

    RepositoryType getType();

    FetcherType getFetcherType();

    EntityFetcher getEntityFetcher();
    
}
