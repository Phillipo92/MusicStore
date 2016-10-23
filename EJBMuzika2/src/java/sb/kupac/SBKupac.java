/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.kupac;

import domen.Kupac;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Filip
 */
@Stateless
public class SBKupac implements SBKupacLocal {

    @PersistenceContext(unitName = "EJBMuzika2PU")
    private EntityManager em;

    @Override
    public void kreirajKupca(Kupac k) {
        try {
            Kupac kupacIzBaze = em.find(Kupac.class, k.getJmbg());

            if (kupacIzBaze != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("poruka",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unos kupca", "Već postoji takav kupac"));
                context.getExternalContext().getFlash().setKeepMessages(true);
            } else {
                em.persist(k);
                
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("poruka",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Unos kupca", "Kupac je uspešno sačuvan"));
                context.getExternalContext().getFlash().setKeepMessages(true);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unos kupca", "Došlo je do greške prilikom unosa kupca"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            System.out.println("Greska: \n \n" + e.getMessage());
        }
    }

    @Override
    public List<Kupac> vratiListuKupaca() {
        return em.createNamedQuery("Kupac.findAll").getResultList();
    }

    @Override
    public void obrisiKupca(Kupac k) {
        try {
            Kupac kupac = em.find(Kupac.class, k.getJmbg());

            if (kupac != null) {
                em.remove(kupac);

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("poruka",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Brisanje kupca", "Kupac je uspešno obrisan"));
                context.getExternalContext().getFlash().setKeepMessages(true);
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Brisanje kupca", "Došlo je do greške prilikom brisanja kupca"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            System.out.println("Greska: \n \n" + e.getMessage());
        }
    }

    @Override
    public void izmeniKupca(Kupac k) {
        try {
            em.merge(k);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Izmena kupca", "Kupac je uspešno izmenjen"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Izmena kupca", "Došlo je do greške prilikom izmene kupca"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            System.out.println("Greska: \n \n" + e.getMessage());
        }
    }

}
