/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.racun;

import domen.Racun;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Filip
 */
@Local
public interface SBRacunLocal {
    
    public void sacuvajRacun(Racun r);
    
    public Integer vratiMaxID();
    
}
