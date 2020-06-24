package functions.util;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mahamad
 */
@FacesConverter("farsidigit")
public class FarsiDigit implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {

        return PersianDigit(String.valueOf(value));       // change to farsi string for show
    } 
     public String PersianDigit(String s) {
        StringBuilder ss = new StringBuilder();
        char[] d = new char[s.length()];

        for (int i = 0; i < d.length; i++) {
            d[i] = s.charAt(i);
            if (d[i] >= '0' && d[i] <= '9') {
                d[i] = ((char) (d[i] - 48 + 1632));
            }
            ss.append(d[i]);
        }
        return ss.toString();
    }
}
