package domen;

import domen.Racun;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-15T19:31:30")
@StaticMetamodel(Radnik.class)
public class Radnik_ { 

    public static volatile SingularAttribute<Radnik, Integer> radnikID;
    public static volatile SingularAttribute<Radnik, String> ime;
    public static volatile SingularAttribute<Radnik, String> prezime;
    public static volatile SingularAttribute<Radnik, String> pass;
    public static volatile ListAttribute<Radnik, Racun> racunList;
    public static volatile SingularAttribute<Radnik, String> user;

}