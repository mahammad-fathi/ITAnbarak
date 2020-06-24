package Beans.Backing.Anbar;

import Beans.Entity.Anbar;
import functions.util.JsfUtil;
import functions.util.JsfUtil.PersistAction;
import Beans.Session.AnbarFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("anbarController")
@SessionScoped
public class AnbarController implements Serializable {

    @EJB
    private Beans.Session.AnbarFacade ejbFacade;
    private List<Anbar> items = null;
    private Anbar selected;
    private List<String> itemnams;   // list of anbar item name for user select 

    public AnbarController() {
    }

    public Anbar getSelected() {
        return selected;
    }

    public void setSelected(Anbar selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AnbarFacade getFacade() {
        return ejbFacade;
    }

    public Anbar prepareCreate() {
        selected = new Anbar();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AnbarCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update(Anbar selected) {
        persist(selected,PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AnbarUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AnbarDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Anbar> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
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
    
    public void persist(Anbar Recoed, JsfUtil.PersistAction persistAction, String successMessage) {
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

    public Anbar getAnbar(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Anbar> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Anbar> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Anbar.class)
    public static class AnbarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AnbarController controller = (AnbarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "anbarController");
            return controller.getAnbar(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Anbar) {
                Anbar o = (Anbar) object;
                return getStringKey(o.getItemname());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Anbar.class.getName()});
                return null;
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //for update table
    public void refresh() {
        this.items = getFacade().findAll();
    }

    public List<String> getDistanctDepartement() {   // for filter Departement columen in Anbar table and Anabr data Entry
        return getFacade().getDistanctDepartement();
    }
    //return Anber item names for selectBox

    public List<String> getItemnams() {
        this.itemnams = getFacade().getitemnams();
        return itemnams;
    }


}
