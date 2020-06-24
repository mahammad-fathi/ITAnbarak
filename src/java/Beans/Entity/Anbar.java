/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mahamad
 */
@Entity
@Table(name = "ANBAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anbar.findAll", query = "SELECT a FROM Anbar a")
    , @NamedQuery(name = "Anbar.findByItemcode", query = "SELECT a FROM Anbar a WHERE a.itemcode = :itemcode")
    , @NamedQuery(name = "Anbar.findByItemname", query = "SELECT a FROM Anbar a WHERE a.itemname = :itemname")
    , @NamedQuery(name = "Anbar.findByTedad", query = "SELECT a FROM Anbar a WHERE a.tedad = :tedad")
    , @NamedQuery(name = "Anbar.findByTarigh", query = "SELECT a FROM Anbar a WHERE a.tarigh = :tarigh")
    , @NamedQuery(name = "Anbar.findByDepartement", query = "SELECT a FROM Anbar a WHERE a.departement = :departement")
    ///////////////////////////////////////////////////////////////
    , @NamedQuery(name = "Anbar.findALLDIStinctDepartement", query = "SELECT DISTINCT a.departement FROM Anbar a")
    , @NamedQuery(name = "Anbar.findTedad", query = "SELECT a.tedad FROM Anbar a WHERE a.itemname = :itemname")
    , @NamedQuery(name = "Anbar.findAllItemname", query = "SELECT a.itemname FROM Anbar a")
    , @NamedQuery(name = "Anbar.findItemcode", query = "SELECT a.itemcode FROM Anbar a WHERE a.itemname = :itemname")

})

public class Anbar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ITEMCODE")
    private String itemcode;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ITEMNAME")
    private String itemname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEDAD")
    private int tedad;
    @Column(name = "TARIGH")
    @Temporal(TemporalType.DATE)
    private Date tarigh;
    @Size(max = 20)
    @Column(name = "DEPARTEMENT")
    private String departement;
    @Lob
    @Size(max = 32700)
    @Column(name = "COMMENT")
    private String comment;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "anbar")
    private Collection<Delivery> deliveryCollection;

    public Anbar() {
    }

    public Anbar(String itemname) {
        this.itemname = itemname;
    }

    public Anbar(String itemname, String itemcode, int tedad) {
        this.itemname = itemname;
        this.itemcode = itemcode;
        this.tedad = tedad;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getTedad() {
        return tedad;
    }

    public void setTedad(int tedad) {
        this.tedad = tedad;
    }

    public Date getTarigh() {
        return tarigh;
    }

    public void setTarigh(Date tarigh) {
        this.tarigh = tarigh;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @XmlTransient
    public Collection<Delivery> getDeliveryCollection() {
        return deliveryCollection;
    }

    public void setDeliveryCollection(Collection<Delivery> deliveryCollection) {
        this.deliveryCollection = deliveryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemname != null ? itemname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anbar)) {
            return false;
        }
        Anbar other = (Anbar) object;
        if ((this.itemname == null && other.itemname != null) || (this.itemname != null && !this.itemname.equals(other.itemname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Entity.Anbar[ itemname=" + itemname + " ]";
    }

}
