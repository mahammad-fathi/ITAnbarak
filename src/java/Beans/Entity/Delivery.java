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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mahamad
 */
@Entity
@Table(name = "DELIVERY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d")
    , @NamedQuery(name = "Delivery.findByItemcode", query = "SELECT d FROM Delivery d WHERE d.itemcode = :itemcode")
    , @NamedQuery(name = "Delivery.findByItemname", query = "SELECT d FROM Delivery d WHERE d.deliveryPK.itemname = :itemname")
    , @NamedQuery(name = "Delivery.findByTedad", query = "SELECT d FROM Delivery d WHERE d.tedad = :tedad")
    , @NamedQuery(name = "Delivery.findByTarigh", query = "SELECT d FROM Delivery d WHERE d.deliveryPK.tarigh = :tarigh")
    , @NamedQuery(name = "Delivery.findByWr", query = "SELECT d FROM Delivery d WHERE d.wr = :wr")
    , @NamedQuery(name = "Delivery.findByWo", query = "SELECT d FROM Delivery d WHERE d.wo = :wo")
    , @NamedQuery(name = "Delivery.findByType", query = "SELECT d FROM Delivery d WHERE d.type = :type")
    , @NamedQuery(name = "Delivery.findByApplicantName", query = "SELECT d FROM Delivery d WHERE d.deliveryPK.applicantName = :applicantName")
    , @NamedQuery(name = "Delivery.findByApplicantDepartment", query = "SELECT d FROM Delivery d WHERE d.applicantDepartment = :applicantDepartment")
    , @NamedQuery(name = "Delivery.findByUsername", query = "SELECT d FROM Delivery d WHERE d.username = :username")
    ////////////////////////////////////////////////////////////////////////////////////////////
    , @NamedQuery(name = "Delivery.findDeliveryTedad", query = "SELECT COALESCE( SUM(d.tedad),0) FROM Delivery d WHERE d.deliveryPK.itemname = :itemname and d.type = :type")
    , @NamedQuery(name = "Delivery.findwrdistinct", query = "SELECT distinct(d.wr)  FROM Delivery d WHERE d.type = :type")
    , @NamedQuery(name = "Delivery.findALLDistanctapplicantDepartment", query = "SELECT distinct(d.applicantDepartment)  FROM Delivery d WHERE d.type = :type")
    , @NamedQuery(name = "Delivery.findALLDistanctApplicantName", query = "SELECT distinct(d.deliveryPK.applicantName)  FROM Delivery d WHERE d.type = :type")
    , @NamedQuery(name = "Delivery.findALLDistanctItemnams", query = "SELECT distinct(d.deliveryPK.itemname)  FROM Delivery d WHERE d.type = :type")
    , @NamedQuery(name = "Delivery.findDeliveryAmaniMaster", query = "SELECT distinct(d.deliveryPK.tarigh) FROM Delivery d WHERE d.type = :type")
    , @NamedQuery(name = "Delivery.CountAmaniByType", query = "SELECT COUNT(d) FROM Delivery d WHERE d.type = :type")

})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeliveryPK deliveryPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ITEMCODE")
    private String itemcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEDAD")
    private int tedad;
    @Lob
    @Size(max = 32700)
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "WR")
    private Integer wr;
    @Column(name = "WO")
    private Integer wo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE")
    private Boolean type;
    @Size(max = 20)
    @Column(name = "APPLICANT_DEPARTMENT")
    private String applicantDepartment;
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username;
    @JoinColumn(name = "ITEMNAME", referencedColumnName = "ITEMNAME", insertable = false ,updatable = false )
    @ManyToOne(optional = false)
    private Anbar anbar;

    public Delivery() {
    }

    public Delivery(DeliveryPK deliveryPK) {
        this.deliveryPK = deliveryPK;
    }

    public Delivery(DeliveryPK deliveryPK, String itemcode, int tedad, Boolean type) {
        this.deliveryPK = deliveryPK;
        this.itemcode = itemcode;
        this.tedad = tedad;
        this.type = type;
    }

    public Delivery(String itemname, Date tarigh, String applicantName) {
        this.deliveryPK = new DeliveryPK(itemname, tarigh, applicantName);
    }

    public DeliveryPK getDeliveryPK() {
        return deliveryPK;
    }

    public void setDeliveryPK(DeliveryPK deliveryPK) {
        this.deliveryPK = deliveryPK;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public int getTedad() {
        return tedad;
    }

    public void setTedad(int tedad) {
        this.tedad = tedad;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getWr() {
        return wr;
    }

    public void setWr(Integer wr) {
        this.wr = wr;
    }

    public Integer getWo() {
        return wo;
    }

    public void setWo(Integer wo) {
        this.wo = wo;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getApplicantDepartment() {
        return applicantDepartment;
    }

    public void setApplicantDepartment(String applicantDepartment) {
        this.applicantDepartment = applicantDepartment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Anbar getAnbar() {
        return anbar;
    }

    public void setAnbar(Anbar anbar) {
        this.anbar = anbar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryPK != null ? deliveryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.deliveryPK == null && other.deliveryPK != null) || (this.deliveryPK != null && !this.deliveryPK.equals(other.deliveryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Beans.Entity.Delivery[ deliveryPK=" + deliveryPK + " ]";
    }

}
