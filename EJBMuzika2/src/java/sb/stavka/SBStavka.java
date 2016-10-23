/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.stavka;

import domen.Stavka;
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
public class SBStavka implements SBStavkaLocal {
    @PersistenceContext(unitName = "EJBMuzika2PU")
    private EntityManager em;

    @Override
    public void sacuvajStavku(Stavka s) {
        try {
            em.persist(s);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Čuvanje stavki", "Došlo je do greške prilikom čuvanja stavki!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            System.out.println("Greska: \n \n" + e.getMessage());
        }
    }

    @Override
    public List<Stavka> vratiListuRacunaZaKupca(Long kupacJmbg) {
        Query query = em.createNamedQuery("Stavka.findByKupac")
                .setParameter("kupacJmbg", kupacJmbg);
        return query.getResultList();
    }


}
