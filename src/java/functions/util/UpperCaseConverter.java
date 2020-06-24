/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions.util;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.convert.FacesConverter;
/**
 *
 * @author mahamad
 */

@FacesConverter("toUpperCaseConverter")
public class UpperCaseConverter implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        return (submittedValue != null) ? submittedValue.toUpperCase() : null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        return (modelValue != null) ? modelValue.toString() : "";
    }

}