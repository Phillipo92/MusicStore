/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.kupac;

import domen.Kupac;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Filip
 */
@Local
public interface SBKupacLocal {

    public void kreirajKupca(Kupac k);
    
    public void izmeniKupca(Kupac k);

    public List<Kupac> vratiListuKupaca();

    public void obrisiKupca(Kupac k);

}
