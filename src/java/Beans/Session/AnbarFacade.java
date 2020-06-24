/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.Session;

import Beans.Entity.Anbar;
import Beans.Entity.Delivery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mahamad
 */
@Stateless
public class AnbarFacade extends AbstractFacade<Anbar> {

    @PersistenceContext(unitName = "ITAnbarakPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AnbarFacade() {
        super(Anbar.class);
    }
    ///////////////////////////////////////////////////////////////////////////

    public List<String> getDistanctDepartement() {
        List<String> resultListnum = getEntityManager().createNamedQuery("Anbar.findALLDIStinctDepartement", String.class).getResultList();
        return resultListnum;
    }

    public int GetTedad(String itemname) {
        return getEntityManager().createNamedQuery("Anbar.findTedad", Integer.class)
                .setParameter("itemname", itemname)
                .getSingleResult();
    }

    public String getitemcode(String num) {
        return getEntityManager().createNamedQuery("Anbar.findItemcode", String.class)
                .setParameter("itemname", num)
                .getSingleResult();
    }

    public List<String> getitemnams() {
        return getEntityManager().createNamedQuery("Anbar.findAllItemname", String.class).getResultList();
    }

}
