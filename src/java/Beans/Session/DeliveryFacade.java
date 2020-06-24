/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.Session;

import Beans.Entity.Delivery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mahamad
 */
@Stateless
public class DeliveryFacade extends AbstractFacade<Delivery> {

    @PersistenceContext(unitName = "ITAnbarakPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public DeliveryFacade() {
        super(Delivery.class);
    }
    //////////////////////////////////////////////////////////////////////////////////////////

    public int GetTedad(String itemname, boolean type) {
        return getEntityManager().createNamedQuery("Delivery.findDeliveryTedad", Integer.class)
                .setParameter("itemname", itemname)
                .setParameter("type", type)
                .getSingleResult();
    }

    public List<Delivery> MasterPermanent() {
        List<Delivery> Records = new ArrayList<>();

        List<Integer> WR = getEntityManager().createNamedQuery("Delivery.findwrdistinct", Integer.class)
                .setParameter("type", true) // list of permanents
                .getResultList();

        for (int i = 0; i < WR.size(); i++) {
            Delivery Delivery = getEntityManager().createNamedQuery("Delivery.findByWr", Delivery.class)
                    .setParameter("wr", (int) WR.get(i))
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();
            Records.add(Delivery);
        }
        return Records;
    }

    public List<Delivery> findTMasterAmani() {
        List<Delivery> Records = new ArrayList<>();

        List<java.sql.Date> resultList = getEntityManager().createNamedQuery("Delivery.findDeliveryAmaniMaster", java.sql.Date.class)
                .setParameter("type", false)
                .getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            List<Delivery> resultList1 = getEntityManager().createNamedQuery("Delivery.findByTarigh", Delivery.class)
                    .setParameter("tarigh", resultList.get(i))
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getResultList();
            Records.addAll(i, resultList1);
        }
        return Records;
    }

    public List<String> getDistanctApplicantName(boolean type) {
        List<String> resultListnum = getEntityManager().createNamedQuery("Delivery.findALLDistanctApplicantName", String.class)
                .setParameter("type", type)
                .getResultList();

        return resultListnum;
    }

    public List<String> getDistanctapplicantDepartment(boolean type) {
        List<String> resultListnum = getEntityManager().createNamedQuery("Delivery.findALLDistanctapplicantDepartment", String.class)
                .setParameter("type", type)
                .getResultList();

        return resultListnum;
    }
    public List<String> getDistanctItemnams(boolean type) {
        List<String> resultListnum = getEntityManager().createNamedQuery("Delivery.findALLDistanctItemnams", String.class)
                .setParameter("type", type)
                .getResultList();

        return resultListnum;
    }
    
    
    public List<Delivery> getDetialPermanent(int wr) {
        return getEntityManager().createNamedQuery("Delivery.findByWr", Delivery.class)
                .setParameter("wr", wr)
                .getResultList();
    }

    public List<Delivery> getDetialAmani(Date tarighs) {
        return getEntityManager().createNamedQuery("Delivery.findByTarigh", Delivery.class)
                .setParameter("tarigh", tarighs)
                .getResultList();
    }

    public boolean IfExistAmani(boolean type) {
        int CountAmani = getEntityManager().createNamedQuery("Delivery.CountAmaniByType", Long.class)
                .setParameter("type", type)
                .getSingleResult().intValue();
        if (CountAmani > 0) {
            return false;
        } else {
            return true;
        }
    }
}
