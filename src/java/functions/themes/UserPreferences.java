package functions.themes;

import java.io.Serializable;

/**
 * UserPreferences
 *
 * @author  Oleg Varaksin / last modified by $Author: $
 * @version $Revision: 1.0 $
 */
public class UserPreferences implements Serializable {

	private static final long serialVersionUID = 20120527L;

	private String theme = "ui-lightness";

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
