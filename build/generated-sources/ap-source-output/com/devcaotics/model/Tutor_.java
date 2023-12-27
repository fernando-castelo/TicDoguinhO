package com.devcaotics.model;

import com.devcaotics.model.Pet;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-27T14:20:21")
@StaticMetamodel(Tutor.class)
public class Tutor_ { 

    public static volatile SingularAttribute<Tutor, Boolean> mamae;
    public static volatile SetAttribute<Tutor, Pet> pets;
    public static volatile SingularAttribute<Tutor, String> senha;
    public static volatile SingularAttribute<Tutor, Integer> codigo;
    public static volatile SingularAttribute<Tutor, byte[]> imagem;
    public static volatile SingularAttribute<Tutor, String> usuario;
    public static volatile SingularAttribute<Tutor, String> email;

}