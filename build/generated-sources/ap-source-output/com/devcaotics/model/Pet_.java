package com.devcaotics.model;

import com.devcaotics.model.Postagem;
import com.devcaotics.model.Tutor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-27T14:20:21")
@StaticMetamodel(Pet.class)
public class Pet_ { 

    public static volatile SingularAttribute<Pet, Integer> codigo;
    public static volatile SetAttribute<Pet, Tutor> tutors;
    public static volatile SingularAttribute<Pet, byte[]> imagem;
    public static volatile SingularAttribute<Pet, String> nome;
    public static volatile SingularAttribute<Pet, String> porte;
    public static volatile SingularAttribute<Pet, String> codigoFoto;
    public static volatile SetAttribute<Pet, Postagem> postagens;
    public static volatile SingularAttribute<Pet, String> mesAnoNascimento;

}