package functions.themes;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * ThemeConverter
 *
 * @author  Oleg Varaksin / last modified by $Author: $
 * @version $Revision: 1.0 $
 */
@FacesConverter("functions.themes.ThemeConverter")
public class ThemeConverter implements Converter {

        @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return AvailableThemes.instance().getTheme(value);
	}

        @Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Theme) value).getName();
	}
}
