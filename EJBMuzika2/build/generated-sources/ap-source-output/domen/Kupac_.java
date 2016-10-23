package domen;

import domen.Mesto;
import domen.Racun;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-15T19:31:30")
@StaticMetamodel(Kupac.class)
public class Kupac_ { 

    public static volatile SingularAttribute<Kupac, String> ime;
    public static volatile SingularAttribute<Kupac, String> prezime;
    public static volatile SingularAttribute<Kupac, String> ulica;
    public static volatile SingularAttribute<Kupac, String> mail;
    public static volatile SingularAttribute<Kupac, String> broj;
    public static volatile SingularAttribute<Kupac, String> telefon;
    public static volatile ListAttribute<Kupac, Racun> racunList;
    public static volatile SingularAttribute<Kupac, Long> jmbg;
    public static volatile SingularAttribute<Kupac, Mesto> mesto;

}