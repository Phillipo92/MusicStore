/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.instrument;

import domen.Instrument;
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
public class SBInstrument implements SBInstrumentLocal {

    @PersistenceContext(unitName = "EJBMuzika2PU")
    private EntityManager em;

    @Override
    public List<Instrument> vratiListuInstrumenata() {

        Query query = em.createNamedQuery("Instrument.findAll");
        return query.getResultList();
    }

    @Override
    public void sacuvajInstrument(Instrument instrument) {
        try {
            Instrument instrumentIzBaze = em.find(Instrument.class, instrument.getSerijskiBroj());

            if (instrumentIzBaze != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("poruka",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unos instrumenta", "Već postoji takav instrument"));
                context.getExternalContext().getFlash().setKeepMessages(true);
            } else {
                em.persist(instrument);

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("poruka",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Unos instrumenta", "Instrument je uspešno sačuvan"));
                context.getExternalContext().getFlash().setKeepMessages(true);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unos instrument", "Došlo je do greške prilikom unosa instrumenta"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            System.out.println("Greska: \n \n" + e.getMessage());
        }
    }

    @Override
    public void obrisiInstrument(Instrument instrument) {
        try {
            Instrument instrumentIzBaze = em.find(Instrument.class, instrument.getSerijskiBroj());

            if (instrumentIzBaze != null) {
                em.remove(instrumentIzBaze);

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("poruka",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Brisanje instrumenta", "Instrument je uspešno obrisan"));
                context.getExternalContext().getFlash().setKeepMessages(true);
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Brisanje instrumenta", "Došlo je do greške prilikom brisanja instrumenta"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            System.out.println("Greska: \n \n" + e.getMessage());
        }
    }

}
