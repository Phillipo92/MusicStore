/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.kupac;

import domen.Kupac;
import domen.Mesto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sb.kupac.SBKupacLocal;
import sb.mesto.SBMestoLocal;

/**
 *
 * @author Filip
 */
@ManagedBean
@ViewScoped
public class MBUnosKupca implements Serializable {

    Kupac kupac;
    List<Mesto> listaMesta;
    String izmenaDugme = "false";
    String kreirajDugme = "true";
    String blokirajJMBG = "false";

    @EJB
    SBKupacLocal sbKupac;
    @EJB
    SBMestoLocal sbMesto;

    public MBUnosKupca() {
        listaMesta = new ArrayList<>();
        kupac = new Kupac();
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().getFlash().get("kupac") != null) {
            kupac = (Kupac) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("kupac");
            izmenaDugme = "true";
            kreirajDugme = "false";
            blokirajJMBG = "true";
        }
        if (sbMesto != null) {
            listaMesta = sbMesto.vratiListuMesta();
        }
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public List<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    public String getIzmenaDugme() {
        return izmenaDugme;
    }

    public void setIzmenaDugme(String izmenaDugme) {
        this.izmenaDugme = izmenaDugme;
    }

    public String getKreirajDugme() {
        return kreirajDugme;
    }

    public void setKreirajDugme(String kreirajDugme) {
        this.kreirajDugme = kreirajDugme;
    }

    public String getBlokirajJMBG() {
        return blokirajJMBG;
    }

    public void setBlokirajJMBG(String blokirajJMBG) {
        this.blokirajJMBG = blokirajJMBG;
    }

    public void kreirajKupca() {
        try {
            sbKupac.kreirajKupca(kupac);
            kupac = new Kupac();
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unos kupca", "Došlo je do greške prilikom kreiranja kupca!"));
            System.out.println("Greska: \n\n" + e.getMessage());
        }
    }

    public void izmeniKupca() {
        try {
            sbKupac.izmeniKupca(kupac);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Izmena kupca", "Došlo je do greške prilikom izmene kupca!"));
            System.out.println("Greska: \n\n" + e.getMessage());
        }
    }

}
