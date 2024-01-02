package com.devcaotics.model;

import com.devcaotics.model.Pet;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-02T16:18:00")
@StaticMetamodel(Postagem.class)
public class Postagem_ { 

    public static volatile SingularAttribute<Postagem, Integer> codigo;
    public static volatile SingularAttribute<Postagem, Date> dataPublicacao;
    public static volatile SingularAttribute<Postagem, byte[]> tutorVideo;
    public static volatile SingularAttribute<Postagem, String> textoPost;
    public static volatile SingularAttribute<Postagem, byte[]> video;
    public static volatile SingularAttribute<Postagem, Pet> pet;

}