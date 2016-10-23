/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.mesto;

import domen.Mesto;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class SBMesto implements SBMestoLocal {
    
    @PersistenceContext(unitName = "EJBMuzika2PU")
    private EntityManager em;

    @Override
    public List<Mesto> vratiListuMesta() {
       return em.createNamedQuery("Mesto.findAll").getResultList();
    }

    @Override
    public Mesto vratiMestoPoID(Integer mestoID) {
        Mesto mesto = em.find(Mesto.class, mestoID);
        return mesto;
    }

}
