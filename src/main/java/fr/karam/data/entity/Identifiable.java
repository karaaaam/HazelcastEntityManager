package fr.karam.data.entity;

import java.io.Serializable;

public interface Identifiable<ID extends Serializable> {

    ID getIdentifier();

}
