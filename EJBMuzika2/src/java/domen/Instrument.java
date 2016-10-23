/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Filip
 */
@Entity
@Table(name = "instrument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instrument.findAll", query = "SELECT i FROM Instrument i"),
    @NamedQuery(name = "Instrument.findByKategorija", query = "SELECT i FROM Instrument i WHERE i.kategorija = :kategorija"),
    @NamedQuery(name = "Instrument.findByProizvodjac", query = "SELECT i FROM Instrument i WHERE i.proizvodjac = :proizvodjac"),
    @NamedQuery(name = "Instrument.findByModel", query = "SELECT i FROM Instrument i WHERE i.model = :model"),
    @NamedQuery(name = "Instrument.findBySerijskiBroj", query = "SELECT i FROM Instrument i WHERE i.serijskiBroj = :serijskiBroj"),
    @NamedQuery(name = "Instrument.findByCena", query = "SELECT i FROM Instrument i WHERE i.cena = :cena")})
public class Instrument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 30)
    @Column(name = "kategorija")
    private String kategorija;
    @Size(max = 20)
    @Column(name = "proizvodjac")
    private String proizvodjac;
    @Size(max = 30)
    @Column(name = "model")
    private String model;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "serijskiBroj")
    private Integer serijskiBroj;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private Double cena;

    public Instrument() {
    }

    public Instrument(String model, String proizvodjac) {
        this.model = model;
        this.proizvodjac = proizvodjac;
    }

    public Instrument(Integer serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSerijskiBroj() {
        return serijskiBroj;
    }

    public void setSerijskiBroj(Integer serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serijskiBroj != null ? serijskiBroj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instrument)) {
            return false;
        }
        Instrument other = (Instrument) object;
        if ((this.serijskiBroj == null && other.serijskiBroj != null) || (this.serijskiBroj != null && !this.serijskiBroj.equals(other.serijskiBroj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Instrument[ serijskiBroj=" + serijskiBroj + " ]";
    }

}
