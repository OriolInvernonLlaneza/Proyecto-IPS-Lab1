package gui;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceManager {
	
	private static ResourceManager manager;
	private final String BUNDLE = "rcs/textos";
	
	private ResourceBundle bundle;
	
	private ResourceManager(Locale locale){
		try {
			bundle = ResourceBundle.getBundle(BUNDLE, locale);
		} catch (MissingResourceException e) {
			bundle = ResourceBundle.getBundle(BUNDLE, new Locale("es"));
		}
	}
	
	public static ResourceManager getResourceManager(){
		if(manager == null)
			manager = new ResourceManager(Locale.getDefault(Locale.Category.FORMAT));
		return manager;
	}
	
	
	public String getString(String id){
		return bundle.getString(id);
	}

}
