/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.stavka;

import domen.Stavka;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Filip
 */
@Local
public interface SBStavkaLocal {
    
    public void sacuvajStavku(Stavka s);
    
    public List<Stavka> vratiListuRacunaZaKupca(Long kupacJmbg);
}
