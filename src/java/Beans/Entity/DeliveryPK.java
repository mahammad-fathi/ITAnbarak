/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mahamad
 */
@Embeddable
public class DeliveryPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ITEMNAME")
    private String itemname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TARIGH")
    @Temporal(TemporalType.DATE)
    private Date tarigh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "APPLICANT_NAME")
    private String applicantName;

    public DeliveryPK() {
    }

    public DeliveryPK(String itemname, Date tarigh, String applicantName) {
        this.itemname = itemname;
        this.tarigh = tarigh;
        this.applicantName = applicantName;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Date getTarigh() {
        return tarigh;
    }

    public void setTarigh(Date tarigh) {
        this.tarigh = tarigh;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemname != null ? itemname.hashCode() : 0);
        hash += (tarigh != null ? tarigh.hashCode() : 0);
        hash += (applicantName != null ? applicantName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryPK)) {
            return false;
        }
        DeliveryPK other = (DeliveryPK) object;
        if ((this.itemname == null && other.itemname != null) || (this.itemname != null && !this.itemname.equals(other.itemname))) {
            return false;
        }
        if ((this.tarigh == null && other.tarigh != null) || (this.tarigh != null && !this.tarigh.equals(other.tarigh))) {
            return false;
        }
        if ((this.applicantName == null && other.applicantName != null) || (this.applicantName != null && !this.applicantName.equals(other.applicantName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Entity.DeliveryPK[ itemname=" + itemname + ", tarigh=" + tarigh + ", applicantName=" + applicantName + " ]";
    }
    
}
