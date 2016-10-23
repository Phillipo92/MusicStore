/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.instrument;

import domen.Instrument;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Filip
 */
@Local
public interface SBInstrumentLocal {
    
    public List<Instrument> vratiListuInstrumenata();
    public void sacuvajInstrument(Instrument instrument);
    public void obrisiInstrument(Instrument instrument);
    
}
