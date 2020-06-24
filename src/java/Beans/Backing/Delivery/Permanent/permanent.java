package Beans.Backing.Delivery.Permanent;

import Beans.Backing.Anbar.AnbarController;
import Beans.Backing.Delivery.General.GeneralBean;
import Beans.Entity.Anbar;
import Beans.Entity.Delivery;
import Beans.Session.AnbarFacade;
import Beans.Session.DeliveryFacade;
import functions.util.JsfUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;

/**
 *
 * @author mahamad
 */
@Named(value = "permanent")
//@SessionScoped
@ViewScoped
public class permanent implements Serializable {

    @Inject
    private GeneralBean GeneralBean;
    @Inject
    private AnbarController anbarController;
    @EJB
    private Beans.Session.DeliveryFacade ejbFacade;
    @EJB
    private Beans.Session.AnbarFacade ejbAnbarFacade;
    private List<Delivery> Detial = null;  // list of detail table
    private List<Delivery> Master = null;  // list of master table
    private Delivery Masterselected = null;
    private Delivery Detialselected = null;
    private List<Delivery> ALL = null;
    String[][] records = null;

    public String[][] getRecords() {
        return records;
    }

    public void setRecords(String[][] records) {
        this.records = records;
    }

    public permanent() {

    }

    private DeliveryFacade getFacade() {
        return ejbFacade;
    }

    private AnbarFacade getAnbarFacade() {
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

    public List<Delivery> getMaster() {
        return getFacade().MasterPermanent();
    }

    public void setMaster(List<Delivery> Master) {
        this.Master = Master;
    }

    public List<Delivery> getDetial() {
        return Detial;
    }

    public void setDetial(int wr) {
        this.Detial = getFacade().getDetialPermanent(wr);

    }

    public List<String> ApplicantDepartment() {
        return getFacade().getDistanctapplicantDepartment(true);
    }

    public List<String> ApplicantName() {
        return getFacade().getDistanctApplicantName(true);
    }

    //init&save Record for multi record with one form
    public void create() {
        Delivery newRec = GeneralBean.getNewrecord();         // fech currentmaster fields and embeded master fields in new records
        String[][] Detailrecords = GeneralBean.getRecords(); // fech current detail fields
        fillRecords(Detailrecords);
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
            getFacade().create(newRec);
            ///////////////////////////////////////
            Anbar_Master.setTedad(Anbar_Master.getTedad() - newRec.getTedad());
            getAnbarFacade().edit(Anbar_Master);
        }
        correct(newRec);
        JsfUtil.addSuccessMessage("قطعات تحویل قطعی با موفقیت ثبت شد.");
        JsfUtil.addSuccessMessage("قطعات انبار با موفقیت بروز شد.");
    }

    public void correct(Delivery tempRec) {
        tempRec.getDeliveryPK().setApplicantName("@");
        getFacade().create(tempRec);
        getFacade().remove(tempRec);

    }

    // for update permanent tedad
    public void update(Delivery EditRecord) {

        String itemname = EditRecord.getDeliveryPK().getItemname();
        int Tedad_current = EditRecord.getTedad();//current tedad
        Anbar anbar = EditRecord.getAnbar();
        int AnbarTedad = anbar.getTedad(); // anbar tedad
        int Tedad_old = getFacade().find(EditRecord.getDeliveryPK()).getTedad();
        int calculateTedad = AnbarTedad + (Tedad_old - Tedad_current);
        if (!GeneralBean.ChekTedadـu(EditRecord, Tedad_current)) {
            anbar.setTedad(calculateTedad);
            GeneralBean.persist(EditRecord, JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PermanentUpdated"));
            anbarController.persist(anbar, JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AnbarUpdated"));
        } else {
            setDetialselected(getFacade().find(EditRecord.getDeliveryPK()));

        }
    }

    public List<Delivery> getALL() {
        return getFacade().findAll();
    }

    private void fillRecords(String[][] Detailrecords) {
        //setRecords(Detailrecords);
        this.records = new String[10][4];

        for (int i = 0; i < 10; i++) {

            this.records[i][0] = Detailrecords[i][0];
            this.records[i][1] = Detailrecords[i][1];
            this.records[i][2] = Detailrecords[i][2];
            this.records[i][3] = Detailrecords[i][0];

        }
    }

}
