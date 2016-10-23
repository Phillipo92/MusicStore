/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Filip
 */
@Entity
@Table(name = "kupac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kupac.findAll", query = "SELECT k FROM Kupac k"),
    @NamedQuery(name = "Kupac.findByJmbg", query = "SELECT k FROM Kupac k WHERE k.jmbg = :jmbg"),
    @NamedQuery(name = "Kupac.findByIme", query = "SELECT k FROM Kupac k WHERE k.ime = :ime"),
    @NamedQuery(name = "Kupac.findByPrezime", query = "SELECT k FROM Kupac k WHERE k.prezime = :prezime"),
    @NamedQuery(name = "Kupac.findByTelefon", query = "SELECT k FROM Kupac k WHERE k.telefon = :telefon"),
    @NamedQuery(name = "Kupac.findByMail", query = "SELECT k FROM Kupac k WHERE k.mail = :mail"),
    @NamedQuery(name = "Kupac.findByUlica", query = "SELECT k FROM Kupac k WHERE k.ulica = :ulica"),
    @NamedQuery(name = "Kupac.findByBroj", query = "SELECT k FROM Kupac k WHERE k.broj = :broj")})
public class Kupac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JMBG")
    private Long jmbg;
    @Size(max = 20)
    @Column(name = "ime")
    private String ime;
    @Size(max = 30)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 15)
    @Column(name = "telefon")
    private String telefon;
    @Size(max = 50)
    @Column(name = "mail")
    private String mail;
    @Size(max = 50)
    @Column(name = "ulica")
    private String ulica;
    @Size(max = 20)
    @Column(name = "broj")
    private String broj;
    @JoinColumn(name = "mesto", referencedColumnName = "mestoID")
    @ManyToOne
    private Mesto mesto;
    @OneToMany(mappedBy = "kupac")
    private List<Racun> racunList;

    public Kupac() {
    }

    public Kupac(Long jmbg) {
        this.jmbg = jmbg;
    }

    public Long getJmbg() {
        return jmbg;
    }

    public void setJmbg(Long jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @XmlTransient
    public List<Racun> getRacunList() {
        return racunList;
    }

    public void setRacunList(List<Racun> racunList) {
        this.racunList = racunList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jmbg != null ? jmbg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kupac)) {
            return false;
        }
        Kupac other = (Kupac) object;
        if ((this.jmbg == null && other.jmbg != null) || (this.jmbg != null && !this.jmbg.equals(other.jmbg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Kupac[ jmbg=" + jmbg + " ]";
    }
    
}
