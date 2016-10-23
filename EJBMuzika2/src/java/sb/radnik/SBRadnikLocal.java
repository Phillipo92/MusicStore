/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.radnik;

import domen.Radnik;
import javax.ejb.Local;

/**
 *
 * @author Filip
 */
@Local
public interface SBRadnikLocal {
    
    public Radnik vratiRadnika(String username, String password);
}
