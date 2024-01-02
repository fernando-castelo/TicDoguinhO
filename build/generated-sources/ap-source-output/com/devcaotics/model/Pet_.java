package com.devcaotics.model;

import com.devcaotics.model.Pet;
import com.devcaotics.model.Postagem;
import com.devcaotics.model.Tutor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-02T16:18:00")
@StaticMetamodel(Pet.class)
public class Pet_ { 

    public static volatile ListAttribute<Pet, Pet> seguindo;
    public static volatile SingularAttribute<Pet, Integer> codigo;
    public static volatile SetAttribute<Pet, Tutor> tutors;
    public static volatile SingularAttribute<Pet, byte[]> imagem;
    public static volatile SingularAttribute<Pet, String> nome;
    public static volatile SingularAttribute<Pet, String> porte;
    public static volatile SingularAttribute<Pet, String> codigoFoto;
    public static volatile SetAttribute<Pet, Postagem> postagens;
    public static volatile ListAttribute<Pet, Pet> seguidores;
    public static volatile SingularAttribute<Pet, String> mesAnoNascimento;

}