/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.racun;

import domen.Racun;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Filip
 */
@Stateless
public class SBRacun implements SBRacunLocal {

    @PersistenceContext(unitName = "EJBMuzika2PU")
    private EntityManager em;

    @Override
    public void sacuvajRacun(Racun r) {
        try {
            em.persist(r);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Čuvanje računa", "Račun je uspešno sačuvan"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Čuvanje računa", "Došlo je do greške prilikom čuvanja računa!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            System.out.println("Greska: \n \n" + e.getMessage());
        }
    }

    @Override
    public Integer vratiMaxID() {
        Query query = em.createNamedQuery("Racun.findMaxID");
        Integer ID = (Integer) query.getResultList().get(0);
        return ID;
    }
}
