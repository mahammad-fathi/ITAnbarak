package Beans.Backing.Delivery.General;

import Beans.Entity.Delivery;
import Beans.Session.AnbarFacade;
import Beans.Session.DeliveryFacade;
import functions.util.JsfUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.view.ViewScoped;

/**
 *
 * @author mahamad
 */
@Named(value = "generalBean")
@ViewScoped
public class GeneralBean implements Serializable {

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @EJB
    private Beans.Session.DeliveryFacade ejbFacade;
    @EJB
    private Beans.Session.AnbarFacade ejbAnbarFacade;

    private boolean OverTedad;  //for disable save&edit button in form
    private String[][] records = null; //collect fileds in form with multi record
    private Delivery newrecord = null;  // for compile create record

    public GeneralBean() {
    }

    public Delivery getNewrecord() {
        return newrecord;
    }

    public void setNewrecord(Delivery newrecord) {
        this.newrecord = newrecord;
    }

    private void init() {                  //init by empty
        this.records = new String[10][4];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                this.records[i][j] = "";
            }
        }
    }

    private DeliveryFacade getFacade() {
        return ejbFacade;
    }

    public AnbarFacade getAnbarFacade() {
        return ejbAnbarFacade;
    }

    //persist with new record not defalt selected record Cooperat with multi record save
    public void persist(Delivery Recoed, JsfUtil.PersistAction persistAction, String successMessage) {
        if (Recoed != null) {
            try {
                switch (persistAction) {
                    case CREATE:
                        getFacade().create(Recoed);
                        break;
                    case UPDATE:
                        getFacade().edit(Recoed);
                        break;
                    case DELETE:
                        getFacade().remove(Recoed);
                        break;
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public boolean isOverTedad() {
        return OverTedad;
    }

    public void setOverTedad(boolean OverTedad) {
        this.OverTedad = OverTedad;
    }

    // for Ajax event for set item code
    public void onnamChange(String sel, int index) {
        String itemcode1 = getAnbarFacade().getitemcode(sel);  // get itemname for select item name
        getRecords()[index][1] = itemcode1;
    }

    protected void setEmbeddableKeys(Delivery Recoeded) {
        Recoeded.getDeliveryPK().setItemname(Recoeded.getAnbar().getItemname());
    }

    public String[][] getRecords() {
        return records;
    }

    public void setRecords(String[][] records) {
        this.records = records;
    }

    public void create(Delivery Recoed) {
        persist(Recoed, JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DeliveryCreated"));
        if (!JsfUtil.isValidationFailed()) {
        }
    }

    public void update(Delivery Recoed) {
        persist(Recoed, JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DeliveryUpdated"));
    }

    public void destroy(Delivery Recoed) {
        String messages = "AmaniDeleted";
        if (Recoed.getType()) {
            messages = "PermanentDeleted";
        }
        persist(Recoed, JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString(messages));
        if (!JsfUtil.isValidationFailed()) {
        }
    }

    public Delivery getDelivery(Beans.Entity.DeliveryPK id) {
        return getFacade().find(id);
    }

    public List<Delivery> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Delivery> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Beans.Entity.DeliveryPK getKey(String value) {
        Beans.Entity.DeliveryPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new Beans.Entity.DeliveryPK();
        key.setItemname(values[0]);
        key.setTarigh(java.sql.Date.valueOf(values[1]));
        key.setApplicantName(values[2]);
        return key;
    }

    public String getStringKey(Beans.Entity.DeliveryPK value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value.getItemname());
        sb.append(SEPARATOR);
        sb.append(value.getTarigh());
        sb.append(SEPARATOR);
        sb.append(value.getApplicantName());
        return sb.toString();
    }

    //init for master fields in create form
    public void prepareCreate() {
        this.newrecord = new Delivery(); // master field init
        newrecord.setDeliveryPK(new Beans.Entity.DeliveryPK());
        init();    // detail field init
    }

    public boolean ChekTedadـu(Delivery EditRecord, int Currenttedad) {
        String itemname = EditRecord.getDeliveryPK().getItemname();
        int Tedad_old = EditRecord.getTedad();
        int AnbarTedad = EditRecord.getAnbar().getTedad();
        int SumAmaniTedad = getFacade().GetTedad(itemname, false);
        if (Currenttedad > AnbarTedad + Tedad_old - SumAmaniTedad) {
            JsfUtil.addErrorMessage("موجودی انبار:  " + Integer.toString(AnbarTedad) + " " + "می باشد " + "و تعداد امانی : " + Integer.toString(SumAmaniTedad) + "لطفاتعداد صحیح را وارد کنید");
            setOverTedad(true); //for disable save&edit button in form
            return true; // find error
        } else {
            setOverTedad(false);
            return false;
        }
    }

    public boolean ChekTedadـC(String itemname, int Currenttedad) { //check tedad
        int AnbarTedad = getAnbarFacade().GetTedad(itemname);
        int SumAmaniTedad = getFacade().GetTedad(itemname, false);
        if (Currenttedad > AnbarTedad + -SumAmaniTedad) {
            JsfUtil.addErrorMessage("موجودی انبار:  " + Integer.toString(AnbarTedad) + " " + "می باشد " + "و تعداد امانی : " + Integer.toString(SumAmaniTedad) + "لطفاتعداد صحیح را وارد کنید");
            setOverTedad(true); //for disable save&edit button in form
            return true;
        } else {
            setOverTedad(false);
            return false;
        }
    }

    public boolean ChekTedadAnbarFORamani(String itemname, int Currenttedad) {
        int AnbatTedad = getAnbarFacade().GetTedad(itemname);
        if (Currenttedad > AnbatTedad) {
            JsfUtil.addErrorMessage("موجودی انبار:  " + Integer.toString(AnbatTedad) + " " + "لطفاتعداد صحیح را وارد کنید");
            setOverTedad(true); //for disable save&edit button in form
            return true;
        } else {
            setOverTedad(false);
            return false;
        }
    }

    public void ChekTedadPass(String itemname, int Currenttedad) {
        int AmaniTedad = getFacade().GetTedad(itemname, false);
        if (Currenttedad > AmaniTedad) {
            JsfUtil.addErrorMessage("موجودی  امانی:  " + Integer.toString(AmaniTedad) + "      " + "می باشد " + "تعداد صحیح را وارد کنید");
            setOverTedad(true); //for disable save&edit button in form

        } else {
            setOverTedad(false);

        }
    }
}
