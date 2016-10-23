/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "mesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesto.findAll", query = "SELECT m FROM Mesto m"),
    @NamedQuery(name = "Mesto.findByMestoID", query = "SELECT m FROM Mesto m WHERE m.mestoID = :mestoID"),
    @NamedQuery(name = "Mesto.findByPtt", query = "SELECT m FROM Mesto m WHERE m.ptt = :ptt"),
    @NamedQuery(name = "Mesto.findByNazivMesta", query = "SELECT m FROM Mesto m WHERE m.nazivMesta = :nazivMesta")})
public class Mesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mestoID")
    private Integer mestoID;
    @Column(name = "ptt")
    private BigInteger ptt;
    @Size(max = 30)
    @Column(name = "nazivMesta")
    private String nazivMesta;
    @OneToMany(mappedBy = "mesto")
    private List<Kupac> kupacList;

    public Mesto() {
    }

    public Mesto(Integer mestoID) {
        this.mestoID = mestoID;
    }

    public Integer getMestoID() {
        return mestoID;
    }

    public void setMestoID(Integer mestoID) {
        this.mestoID = mestoID;
    }

    public BigInteger getPtt() {
        return ptt;
    }

    public void setPtt(BigInteger ptt) {
        this.ptt = ptt;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    @XmlTransient
    public List<Kupac> getKupacList() {
        return kupacList;
    }

    public void setKupacList(List<Kupac> kupacList) {
        this.kupacList = kupacList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mestoID != null ? mestoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesto)) {
            return false;
        }
        Mesto other = (Mesto) object;
        if ((this.mestoID == null && other.mestoID != null) || (this.mestoID != null && !this.mestoID.equals(other.mestoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Mesto[ mestoID=" + mestoID + " ]";
    }
    
}
