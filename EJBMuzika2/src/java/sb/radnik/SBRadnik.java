/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.radnik;

import domen.Radnik;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Filip
 */
@Stateless
public class SBRadnik implements SBRadnikLocal {
    
    @PersistenceContext(unitName = "EJBMuzika2PU")
    private EntityManager em;
    
    @Override
    public Radnik vratiRadnika(String username, String password) {
       
        Query query = em.createNamedQuery("Radnik.pronadjiRadnika")
                .setParameter("user", username)
                .setParameter("pass", password);

        Radnik r = (Radnik) query.getResultList().get(0);
        return r;
    }

}
