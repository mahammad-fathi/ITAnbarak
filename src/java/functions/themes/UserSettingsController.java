/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions.themes;


import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * UserSettingsController
 *
 * @author  Oleg Varaksin / last modified by $Author: $
 * @version $Revision: 1.0 $
 */
@Named
@ViewScoped
public class UserSettingsController implements Serializable {

	// Stateful Switcher (AJAX)

	private Map<String, String> themes;
	private String theme;
	private UserPreferences userPreferences = new UserPreferences();

	public Map<String, String> getThemes() {
		return themes;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void saveTheme() {
		userPreferences.setTheme(theme);
	}

	@PostConstruct
	public void init() {
		theme = userPreferences.getTheme();

		themes = new TreeMap<>();
		themes.put("Afterdark", "afterdark");
		themes.put("Afternoon", "afternoon");
		themes.put("Afterwork", "afterwork");
		themes.put("Aristo", "aristo");
		themes.put("Black-Tie", "black-tie");
		themes.put("Blitzer", "blitzer");
		themes.put("Bluesky", "bluesky");
		themes.put("Casablanca", "casablanca");
		themes.put("Cupertino", "cupertino");
		themes.put("Dark-Hive", "dark-hive");
		themes.put("Dot-Luv", "dot-luv");
		themes.put("Eggplant", "eggplant");
		themes.put("Excite-Bike", "excite-bike");
		themes.put("Flick", "flick");
		themes.put("Glass-X", "glass-x");
		themes.put("Hot-Sneaks", "hot-sneaks");
		themes.put("Humanity", "humanity");
		themes.put("Le-Frog", "le-frog");
		themes.put("Midnight", "midnight");
		themes.put("Mint-Choc", "mint-choc");
		themes.put("Overcast", "overcast");
		themes.put("Pepper-Grinder", "pepper-grinder");
		themes.put("Redmond", "redmond");
		themes.put("Rocket", "rocket");
		themes.put("Sam", "sam");
		themes.put("Smoothness", "smoothness");
		themes.put("South-Street", "south-street");
		themes.put("Start", "start");
		themes.put("Sunny", "sunny");
		themes.put("Swanky-Purse", "swanky-purse");
		themes.put("Trontastic", "trontastic");
		themes.put("UI-Darkness", "ui-darkness");
		themes.put("UI-Lightness", "ui-lightness");
		themes.put("Vader", "vader");
	}

	// Stateful Switcher (Full page refresh)

	private List<Theme> availableThemes;
	private Theme currentTheme;

	public UserSettingsController() {
		currentTheme = AvailableThemes.instance().getTheme("ui-lightness");
		availableThemes = AvailableThemes.instance().getThemes();
	}

	public List<Theme> getAvailableThemes() {
		return availableThemes;
	}

	public void setAvailableThemes(List<Theme> availableThemes) {
		this.availableThemes = availableThemes;
	}

	public Theme getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(Theme currentTheme) {
		this.currentTheme = currentTheme;
	}
}

