package domen;

import domen.Kupac;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-15T19:31:30")
@StaticMetamodel(Mesto.class)
public class Mesto_ { 

    public static volatile SingularAttribute<Mesto, BigInteger> ptt;
    public static volatile SingularAttribute<Mesto, String> nazivMesta;
    public static volatile SingularAttribute<Mesto, Integer> mestoID;
    public static volatile ListAttribute<Mesto, Kupac> kupacList;

}