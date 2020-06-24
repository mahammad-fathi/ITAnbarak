/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans.Backing.Delivery.Amani;

import Beans.Backing.Delivery.General.GeneralBean;
import Beans.Entity.Anbar;
import Beans.Entity.Delivery;
import Beans.Session.AnbarFacade;
import Beans.Session.DeliveryFacade;
import functions.util.JsfUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author mahamad
 */
@Named(value = "amani")
//@SessionScoped
@ViewScoped
public class Amani implements Serializable {

    @Inject
    private GeneralBean GeneralBean;
    @EJB
    private Beans.Session.DeliveryFacade ejbFacade;
    @EJB
    private Beans.Session.AnbarFacade ejbAnbarFacade;

    private List<Delivery> Detial = null;
    private List<Delivery> Master = null;
    private Delivery Masterselected = null;
    private Delivery Detialselected = null;
    private Delivery newrecord = null;  // for compile create record
    private List<String> itemnams;   // list of anbar item name for user select 
    private boolean showpass;

    public Amani() {
    }

    public boolean isShowpass() {
        return getFacade().IfExistAmani(false);
    }

    private DeliveryFacade getFacade() {
        return ejbFacade;
    }

    public AnbarFacade getAnbarFacade() {
        return ejbAnbarFacade;
    }

    public Delivery getMasterselected() {
        return Masterselected;
    }

    public void setMasterselected(Delivery Masterselected) {
        this.Masterselected = Masterselected;
    }

    public Delivery getDetialselected() {
        return Detialselected;
    }

    public void setDetialselected(Delivery Detialselected) {
        this.Detialselected = Detialselected;
    }

    public Delivery getNewrecord() {
        return newrecord;
    }

    public void setNewrecord(Delivery newrecord) {
        this.newrecord = newrecord;
    }

    public List<Delivery> getMaster() {
        return getFacade().findTMasterAmani();
    }

    public void setMaster(List<Delivery> Master) {
        this.Master = Master;
    }

    public List<Delivery> getDetial() {
        return Detial;
    }

    public void setDetial(Date tarigh) {
        this.Detial = getFacade().getDetialAmani(tarigh);
    }

    public List<String> applicantDepartment() {
        return getFacade().getDistanctapplicantDepartment(false);
    }

    public List<String> ApplicantName() {
        return getFacade().getDistanctApplicantName(false);
    }

    public List<String> getItemnams() {
        return getFacade().getDistanctItemnams(false);
    }

    public void update(Delivery EditRecord) {
        String itemname = EditRecord.getDeliveryPK().getItemname();
        int Tedad_current = EditRecord.getTedad();//current tedad
        if (!GeneralBean.ChekTedadAnbarFORamani(itemname, Tedad_current)) {
            GeneralBean.persist(EditRecord, JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AmaniUpdated"));
        } else {
            setDetialselected(getFacade().find(EditRecord.getDeliveryPK()));

        }
    }

    //init&save Record for multi record with one form
    public void create_AmaniPass() {
        Delivery newRec = GeneralBean.getNewrecord(); // fech currentmaster fields and embeded master fields in new records
        String[][] Detailrecords = GeneralBean.getRecords(); // fech current detail fields
        newRec.setType(true);  // for permanent

        for (int i = 0; i < 1; i++) {     // fech detail  fields
/*
            if ("null".equals(Detailrecords[i][0]) //check empty select item name
                    || Detailrecords[i][2].isEmpty())//check empty tedad
            {
                continue;  //skip save
            }
             */
            Anbar Anbar_Master = getAnbarFacade().find(Detailrecords[i][0]); // find by item name

            // embeded detail field in new record
            newRec.setAnbar(Anbar_Master); //create relation
            newRec.getDeliveryPK().setItemname(Detailrecords[i][0]);
            newRec.setItemcode(Detailrecords[i][1]);
            newRec.setTedad(Integer.parseInt(Detailrecords[i][2]));
            newRec.setComment(Detailrecords[i][3]);

            //  getFacade().create(newRec);
/*
            int currentTedad = Integer.parseInt((GeneralBean.getRecords()[i][2]));
            Anbar findAnbar_Record = newRec.getAnbar();
            int tedadAnbar = findAnbar_Record.getTedad();
            findAnbar_Record.setTedad(tedadAnbar - currentTedad);
            getAnbarFacade().edit(findAnbar_Record);  //Anbar tedad update
            List<Delivery> findAllitemnameAmani = (List<Delivery>) findAnbar_Record.getDeliveryCollection();
            //  List<Delivery> findAllitemnameAmani = findAllitemnameAmani(getRecords()[i][0]);

            for (Delivery delivery : findAllitemnameAmani) {
                if (!delivery.getType()) {
                    if (currentTedad < delivery.getTedad()) {
                        delivery.setTedad(delivery.getTedad() - currentTedad);
                        getFacade().edit(delivery);
                        break;
                    } else {

                        currentTedad = currentTedad - delivery.getTedad();
                        getFacade().remove(delivery);
                    }
                }
            }
             */
        }
        //   correct(newRec);
        JsfUtil.addSuccessMessage("قطعات تحویل امانی با موفقیت پاس شد.");

    }

    public void createPass() {
        Delivery newRec = GeneralBean.getNewrecord();         // fech currentmaster fields and embeded master fields in new records
        String[][] Detailrecords = GeneralBean.getRecords(); // fech current detail fields
        newRec.setType(true);  // for permanent

        for (int i = 0; i < 10; i++) {     // fech detail  fields

            if ("null".equals(Detailrecords[i][0]) //check empty select item name
                    || Detailrecords[i][2].isEmpty() //check empty tedad
                    || GeneralBean.ChekTedadـC(Detailrecords[i][0], Integer.parseInt(Detailrecords[i][2]))) //check overtedad
            {
                continue;  //skip save
            }

            Anbar Anbar_Master = getAnbarFacade().find(Detailrecords[i][0]); // find by item name

            // embeded detail field in new record
            newRec.setAnbar(Anbar_Master); //create relation
            newRec.getDeliveryPK().setItemname(Detailrecords[i][0]);
            newRec.setItemcode(Detailrecords[i][1]);
            newRec.setTedad(Integer.parseInt(Detailrecords[i][2]));
            newRec.setComment(Detailrecords[i][3]);
            getFacade().create(newRec);         // insert in permanent
            ///////////////////////////////////////
            Anbar_Master.setTedad(Anbar_Master.getTedad() - newRec.getTedad());
            getAnbarFacade().edit(Anbar_Master);  // update anbar
            updateAmani_pass(newRec, i); //update Amani
        }
        correct(newRec);
        JsfUtil.addSuccessMessage("قطعات تحویل امانی با موفقیت پاس شد.");
        JsfUtil.addSuccessMessage("قطعات انبار با موفقیت بروز شد.");
    }

    public void updateAmani_pass(Delivery newRec, int i) {
        int currentTedad = newRec.getTedad();
        List<Delivery> DeliveryCollection = (List<Delivery>) newRec.getAnbar().getDeliveryCollection();

        for (Delivery delivery : DeliveryCollection) {
            if (!delivery.getType()) {  //only Amani
                if (delivery.getTedad() > currentTedad) {
                    delivery.setTedad(delivery.getTedad() - currentTedad);
                    getFacade().edit(delivery);
                    break;
                } else {
                    currentTedad = currentTedad - delivery.getTedad();
                    getFacade().remove(delivery);
                }

            }
        }

    }

    //init&save Record for multi record with one form
    public void create() {
        Delivery newRec = GeneralBean.getNewrecord();         // fech currentmaster fields and embeded master fields in new records
        String[][] Detailrecords = GeneralBean.getRecords(); // fech current detail fields
        newRec.setType(false);  // for Amani

        for (int i = 0; i < 10; i++) {     // fech detail  fields

            if ("null".equals(Detailrecords[i][0]) //check empty select item name
                    || Detailrecords[i][2].isEmpty())//check empty tedad
            {
                continue;  //skip save
            }

            Anbar Anbar_Master = getAnbarFacade().find(Detailrecords[i][0]); // find by item name

            // embeded detail field in new record
            newRec.setAnbar(Anbar_Master); //create relation
            newRec.getDeliveryPK().setItemname(Detailrecords[i][0]);
            newRec.setItemcode(Detailrecords[i][1]);
            newRec.setTedad(Integer.parseInt(Detailrecords[i][2]));
            newRec.setComment(Detailrecords[i][3]);
            getFacade().create(newRec);
        }
        correct(newRec);
        JsfUtil.addSuccessMessage("قطعات تحویل امانی با موفقیت ثبت شد.");
    }

    public String TedadAmani(String itemname) {
        int AmaniTedad = getFacade().GetTedad(itemname, false);
        if (AmaniTedad == 0) {
            return "";
        } else {
            return Integer.toString(AmaniTedad);
        }
    }

    public void correct(Delivery tempRec) {
        tempRec.getDeliveryPK().setApplicantName("@");
        getFacade().create(tempRec);
        getFacade().remove(tempRec);

    }
}
