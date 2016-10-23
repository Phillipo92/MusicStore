/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Filip
 */
@Entity
@Table(name = "stavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavka.findAll", query = "SELECT s FROM Stavka s"),
    @NamedQuery(name = "Stavka.findByStavkaID", query = "SELECT s FROM Stavka s WHERE s.stavkaID = :stavkaID"),
    @NamedQuery(name = "Stavka.findByKupac", query = "SELECT s FROM Stavka s WHERE s.racun.kupac.jmbg = :kupacJmbg")})
public class Stavka implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stavkaID")
    private Integer stavkaID;
    @JoinColumn(name = "instrument", referencedColumnName = "serijskiBroj")
    @ManyToOne
    private Instrument instrument;
    @JoinColumn(name = "racun", referencedColumnName = "racunID")
    @ManyToOne
    private Racun racun;

    public Stavka() {
    }

    public Stavka(Instrument instrument, Racun racun) {
        this.instrument = instrument;
        this.racun = racun;
    }

    public Stavka(Integer stavkaID) {
        this.stavkaID = stavkaID;
    }

    public Integer getStavkaID() {
        return stavkaID;
    }

    public void setStavkaID(Integer stavkaID) {
        this.stavkaID = stavkaID;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkaID != null ? stavkaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavka)) {
            return false;
        }
        Stavka other = (Stavka) object;
        if ((this.stavkaID == null && other.stavkaID != null) || (this.stavkaID != null && !this.stavkaID.equals(other.stavkaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Stavka[ stavkaID=" + stavkaID + " ]";
    }
    
}
