/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.kupac;

import domen.Instrument;
import domen.Kupac;
import domen.Racun;
import domen.Stavka;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import sb.kupac.SBKupacLocal;
import sb.racun.SBRacunLocal;
import sb.stavka.SBStavkaLocal;

/**
 *
 * @author Filip
 */
@ManagedBean
@ViewScoped
public class MBPrikazKupaca implements Serializable {

    List<Kupac> listaKupaca;
    List<Kupac> listaFiltriranihKupaca;
    Kupac selektovaniKupac;

    List<Stavka> listaStavki;

    @EJB
    SBKupacLocal sbKupac;
    
    @EJB
    SBStavkaLocal sbStavka;

    TreeNode rootNode;

    public MBPrikazKupaca() {
        listaKupaca = new ArrayList<>();
        listaFiltriranihKupaca = new ArrayList<>();
        listaStavki = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        if (sbKupac != null) {
            listaFiltriranihKupaca = sbKupac.vratiListuKupaca();
            listaKupaca = sbKupac.vratiListuKupaca();
        }
    }

    public List<Kupac> getListaKupaca() {
        return listaKupaca;
    }

    public void setListaKupaca(List<Kupac> listaKupaca) {
        this.listaKupaca = listaKupaca;
    }

    public List<Kupac> getListaFiltriranihKupaca() {
        return listaFiltriranihKupaca;
    }

    public void setListaFiltriranihKupaca(List<Kupac> listaFiltriranihKupaca) {
        this.listaFiltriranihKupaca = listaFiltriranihKupaca;
    }

    public List<Stavka> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<Stavka> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public Kupac getSelektovaniKupac() {
        return selektovaniKupac;
    }

    public void setSelektovaniKupac(Kupac selektovaniKupac) {
        this.selektovaniKupac = selektovaniKupac;
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public TreeNode vratiRacune() {
        if (sbStavka != null) {
            listaStavki = sbStavka.vratiListuRacunaZaKupca(selektovaniKupac.getJmbg());
        }

        List<Stavka> novaLista = new ArrayList<>();
        int id;
        rootNode = new DefaultTreeNode(new Stavka(new Instrument(), new Racun()), null);

        for (Stavka s : listaStavki) {
            id = s.getRacun().getRacunID();

            if (!novaLista.contains(s)) {
                novaLista.clear();
                TreeNode racuni = new DefaultTreeNode(new Stavka(new Instrument(), s.getRacun()), rootNode);

                for (Stavka s1 : listaStavki) {
                    if (s1.getRacun().getRacunID() == id) {
                        novaLista.add(s1);
                    }
                }

                for (Stavka s2 : novaLista) {
                    TreeNode stavke = new DefaultTreeNode(new Stavka(s2.getInstrument(), null), racuni);
                }
            }
        }
        return rootNode;
    }

    public String pokreniIzmenu() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("kupac", selektovaniKupac);
        return "novi_kupac?faces-redirect=true";
    }

    public void obrisiKupca() {
        try {
            sbKupac.obrisiKupca(selektovaniKupac);
            listaKupaca.remove(selektovaniKupac);
            init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Brisanje kupca", "Došlo je do greške prilikom brisanja kupca!"));
            System.out.println("Greska: \n\n" + e.getMessage());
        }
    }
}
