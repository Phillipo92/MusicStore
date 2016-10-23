/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.racun;

import domen.Instrument;
import domen.Kupac;
import domen.Racun;
import domen.Radnik;
import domen.Stavka;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mb.instrument.MBPrikazInstrumenata;
import mb.kupac.MBPrikazKupaca;
import mb.radnik.MBRadnik;
import org.primefaces.context.RequestContext;
import sb.racun.SBRacunLocal;
import sb.stavka.SBStavkaLocal;

/**
 *
 * @author Filip
 */
@ManagedBean
@ViewScoped
public class MBUnosRacuna implements Serializable {

    @ManagedProperty(value = "#{mBPrikazKupaca}")
    MBPrikazKupaca prikazKupacaBean;

    @ManagedProperty(value = "#{mBPrikazInstrumenata}")
    MBPrikazInstrumenata prikazInstrumenataBean;

    @ManagedProperty(value = "#{mBRadnik}")
    MBRadnik radnikBean;

    @EJB
    SBRacunLocal sbRacun;

    @EJB
    SBStavkaLocal sbStavka;

    Radnik radnik;

    List<Kupac> listaKupacaRacun;
    List<Kupac> listaFiltriranihRacun;
    Kupac selectedKupac;

    List<Instrument> listaInstrumenataRacun;
    List<Instrument> listaFiltriranihInstrumenataRacun;
    Instrument selectedInstrument;

    Stavka stavka;
    Stavka selectedStavka;
    List<Stavka> listaStavki;
    Racun racun;
    Integer racunID;
    int kolicina = 1;
    double iznos;
    double iznosSaPopustom;
    Date datum;
    boolean popust = false;
    boolean prikaziDialog = true;

    public MBUnosRacuna() {
        racun = new Racun();
        listaKupacaRacun = new ArrayList<>();
        listaFiltriranihRacun = new ArrayList<>();
        listaInstrumenataRacun = new ArrayList<>();
        listaFiltriranihInstrumenataRacun = new ArrayList<>();
        listaStavki = new ArrayList<>();
        radnik = new Radnik();
        datum = new Date();
    }

    @PostConstruct
    public void init() {
        postaviRacunID();
        
        if (prikazKupacaBean != null) {
            listaFiltriranihRacun = prikazKupacaBean.getListaFiltriranihKupaca();
            listaKupacaRacun = prikazKupacaBean.getListaKupaca();
        }
        if (prikazInstrumenataBean != null) {
            listaInstrumenataRacun = prikazInstrumenataBean.getListaInstrumenata();
            listaFiltriranihInstrumenataRacun = prikazInstrumenataBean.getListaFiltriranih();
        }
        if (radnikBean != null) {
            radnik = radnikBean.getRadnik();
        }
    }

    public List<Kupac> getListaKupacaRacun() {
        return listaKupacaRacun;
    }

    public void setListaKupacaRacun(List<Kupac> listaKupacaRacun) {
        this.listaKupacaRacun = listaKupacaRacun;
    }

    public List<Kupac> getListaFiltriranihRacun() {
        return listaFiltriranihRacun;
    }

    public void setListaFiltriranihRacun(List<Kupac> listaFiltriranihRacun) {
        this.listaFiltriranihRacun = listaFiltriranihRacun;
    }

    public List<Instrument> getListaInstrumenataRacun() {
        return listaInstrumenataRacun;
    }

    public void setListaInstrumenataRacun(List<Instrument> listaInstrumenataRacun) {
        this.listaInstrumenataRacun = listaInstrumenataRacun;
    }

    public List<Instrument> getListaFiltriranihInstrumenataRacun() {
        return listaFiltriranihInstrumenataRacun;
    }

    public void setListaFiltriranihInstrumenataRacun(List<Instrument> listaFiltriranihInstrumenataRacun) {
        this.listaFiltriranihInstrumenataRacun = listaFiltriranihInstrumenataRacun;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public Instrument getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(Instrument selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }

    public Kupac getSelectedKupac() {
        return selectedKupac;
    }

    public void setSelectedKupac(Kupac selectedKupac) {
        this.selectedKupac = selectedKupac;
    }

    public Stavka getSelectedStavka() {
        return selectedStavka;
    }

    public void setSelectedStavka(Stavka selectedStavka) {
        this.selectedStavka = selectedStavka;
    }

    public void setPrikazKupacaBean(MBPrikazKupaca prikazKupacaBean) {
        this.prikazKupacaBean = prikazKupacaBean;
    }

    public void setPrikazInstrumenataBean(MBPrikazInstrumenata prikazInstrumenataBean) {
        this.prikazInstrumenataBean = prikazInstrumenataBean;
    }

    public void setRadnikBean(MBRadnik radnikBean) {
        this.radnikBean = radnikBean;
    }

    public List<Stavka> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<Stavka> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznosSaPopustom() {
        return iznosSaPopustom;
    }

    public void setIznosSaPopustom(double iznosSaPopustom) {
        this.iznosSaPopustom = iznosSaPopustom;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public boolean isPopust() {
        return popust;
    }

    public void setPopust(boolean popust) {
        this.popust = popust;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public boolean isPrikaziDialog() {
        return prikaziDialog;
    }

    public void setPrikaziDialog(boolean prikaziDialog) {
        this.prikaziDialog = prikaziDialog;
    }

    public String formatirajDatum() {
        return new SimpleDateFormat("dd.MM.yyyy").format(datum);
    }

    public String popustToString() {
        String tekst;
        if (popust == false) {
            tekst = "Ne";
        } else {
            tekst = "Da";
        }
        return tekst;
    }

    public void izracunajIznos() {
        for (Stavka s : listaStavki) {
            iznos += s.getInstrument().getCena();
        }

        if (iznos >= 200000 && iznos < 300000) {
            iznosSaPopustom = iznos - iznos * 0.1;
            popust = true;
            return;
        }

        if (iznos >= 300000 && iznos < 500000) {
            iznosSaPopustom = iznos - iznos * 0.2;
            popust = true;
            return;
        }

        if (iznos >= 500000) {
            iznosSaPopustom = iznos - iznos * 0.3;
            popust = true;
            return;
        }

        iznosSaPopustom = iznos;
    }

    public void resetujIznos() {
        iznos = 0;
        popust = false;
    }

    public void dodajStavku() {
        stavka = new Stavka();
        stavka.setInstrument(selectedInstrument);

        for (int i = 0; i < kolicina; i++) {
            listaStavki.add(stavka);
        }
        kolicina = 1;
        validacija();
    }

    public void sacuvajRacun() {
        try {
            racun.setPopust(popust);
            racun.setProdao(radnik);
            racun.setKupac(selectedKupac);
            racun.setUkupnaVrednost(iznosSaPopustom);
            racun.setDatumKupovine(datum);

            sbRacun.sacuvajRacun(racun);

            for (Stavka s : listaStavki) {
                s.setRacun(racun);
                sbStavka.sacuvajStavku(s);
            }
            
            postaviRacunID();
            listaStavki.removeAll(listaStavki);
            prikaziDialog = true;
            resetujIznos();
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Čuvanje računa", "Došlo je do greške prilikom čuvanja računa!"));
            System.out.println("Greska: \n\n" + e.getMessage());
        }
    }

    public void postaviRacunID() {
        if (sbRacun != null) {
            racunID = sbRacun.vratiMaxID() + 1;
            racun.setRacunID(racunID);
        }
    }

    public void validacija() {
        if (selectedKupac != null && !listaStavki.isEmpty()) {
            prikaziDialog = false;
        }
    }

}
