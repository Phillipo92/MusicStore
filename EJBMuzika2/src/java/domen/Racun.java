/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Filip
 */
@Entity
@Table(name = "racun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racun.findAll", query = "SELECT r FROM Racun r"),
    @NamedQuery(name = "Racun.findByRacunID", query = "SELECT r FROM Racun r WHERE r.racunID = :racunID"),
    @NamedQuery(name = "Racun.findByUkupnaVrednost", query = "SELECT r FROM Racun r WHERE r.ukupnaVrednost = :ukupnaVrednost"),
    @NamedQuery(name = "Racun.findByPopust", query = "SELECT r FROM Racun r WHERE r.popust = :popust"),
    @NamedQuery(name = "Racun.findByDatumKupovine", query = "SELECT r FROM Racun r WHERE r.datumKupovine = :datumKupovine"),
    @NamedQuery(name = "Racun.findMaxID", query = "SELECT MAX (r.racunID) FROM Racun r"),
    @NamedQuery(name = "Racun.findByKupac", query = "SELECT r FROM Racun r WHERE r.kupac.jmbg = :kupacJmbg")})
public class Racun implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "racunID")
    private Integer racunID;
    @Column(name = "ukupnaVrednost")
    private Double ukupnaVrednost;
    @Column(name = "popust")
    private Boolean popust;
    @Column(name = "datumKupovine")
    @Temporal(TemporalType.DATE)
    private Date datumKupovine;
    @OneToMany(mappedBy = "racun")
    private List<Stavka> stavkaList;
    @JoinColumn(name = "kupac", referencedColumnName = "JMBG")
    @ManyToOne
    private Kupac kupac;
    @JoinColumn(name = "prodao", referencedColumnName = "radnikID")
    @ManyToOne
    private Radnik prodao;

    public Racun() {
    }

    public Racun(Integer racunID, Double ukupnaVrednost, Date datumKupovine, Radnik prodao) {
        this.racunID = racunID;
        this.ukupnaVrednost = ukupnaVrednost;
        this.datumKupovine = datumKupovine;
        this.prodao = prodao;
    }

    public Racun(Integer racunID) {
        this.racunID = racunID;
    }

    public Integer getRacunID() {
        return racunID;
    }

    public void setRacunID(Integer racunID) {
        this.racunID = racunID;
    }

    public Double getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(Double ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
    }

    public Boolean getPopust() {
        return popust;
    }

    public void setPopust(Boolean popust) {
        this.popust = popust;
    }

    public Date getDatumKupovine() {
        return datumKupovine;
    }

    public void setDatumKupovine(Date datumKupovine) {
        this.datumKupovine = datumKupovine;
    }

    @XmlTransient
    public List<Stavka> getStavkaList() {
        return stavkaList;
    }

    public void setStavkaList(List<Stavka> stavkaList) {
        this.stavkaList = stavkaList;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Radnik getProdao() {
        return prodao;
    }

    public void setProdao(Radnik prodao) {
        this.prodao = prodao;
    }
    
    public String formatirajDatum() {
        return new SimpleDateFormat("dd.MM.yyyy").format(datumKupovine);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (racunID != null ? racunID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racun)) {
            return false;
        }
        Racun other = (Racun) object;
        if ((this.racunID == null && other.racunID != null) || (this.racunID != null && !this.racunID.equals(other.racunID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Racun[ racunID=" + racunID + " ]";
    }
    
}
