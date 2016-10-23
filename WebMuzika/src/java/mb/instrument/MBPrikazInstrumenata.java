/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.instrument;

import domen.Instrument;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.instrument.SBInstrumentLocal;

/**
 *
 * @author Filip
 */
@ManagedBean
@ViewScoped
public class MBPrikazInstrumenata implements Serializable {

    Instrument instrument;
    List<String> listaKategorija;

    List<Instrument> listaInstrumenata;
    List<Instrument> listaFiltriranih;
    Instrument selektovaniInstrument;

    @EJB
    SBInstrumentLocal sBInstrument;

    public MBPrikazInstrumenata() {
        instrument = new Instrument();
        listaInstrumenata = new ArrayList<>();
        listaFiltriranih = new ArrayList<>();
        listaKategorija = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        listaInstrumenata = sBInstrument.vratiListuInstrumenata();
        listaFiltriranih = sBInstrument.vratiListuInstrumenata();
        listaKategorija.removeAll(listaKategorija);
        listaKategorija.add("Gitare");
        listaKategorija.add("Bubnjevi");
        listaKategorija.add("Klavijature");
        listaKategorija.add("Mikrofoni");
    }

    public List<Instrument> getListaInstrumenata() {
        return listaInstrumenata;
    }

    public void setListaInstrumenata(List<Instrument> listaInstrumenata) {
        this.listaInstrumenata = listaInstrumenata;
    }

    public List<Instrument> getListaFiltriranih() {
        return listaFiltriranih;
    }

    public void setListaFiltriranih(List<Instrument> listaFiltriranih) {
        this.listaFiltriranih = listaFiltriranih;
    }

    public Instrument getSelektovaniInstrument() {
        return selektovaniInstrument;
    }

    public void setSelektovaniInstrument(Instrument selektovaniInstrument) {
        this.selektovaniInstrument = selektovaniInstrument;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public List<String> getListaKategorija() {
        return listaKategorija;
    }

    public void setListaKategorija(List<String> listaKategorija) {
        this.listaKategorija = listaKategorija;
    }

    public void sacuvajInstrument() {
        try {
            sBInstrument.sacuvajInstrument(instrument);
            instrument = new Instrument();
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Čuvanje instrumenta", "Došlo je do greške prilikom čuvanja instrumenta!"));
            System.out.println("Greska: \n\n" + e.getMessage());
        }
    }

    public void obrisiInstrument() {
        try {
            sBInstrument.obrisiInstrument(selektovaniInstrument);
            listaInstrumenata.remove(selektovaniInstrument);
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Brisanje instrumenta", "Došlo je do greške prilikom brisanja instrumenta"));
            System.out.println("Greska: \n\n" + e.getMessage());
        }
    }
}

