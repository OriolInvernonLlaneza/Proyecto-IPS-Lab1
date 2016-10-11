package gui;

import java.awt.Component;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

public class ResourceManager { //ComentarioP
	
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
	
	/**
	 * Devuelve un gestor de recursos
	 * @return El gestor de recursos
	 */
	public static ResourceManager getResourceManager(){
		if(manager == null)
			manager = new ResourceManager(Locale.getDefault(Locale.Category.FORMAT));
		return manager;
	}
	
	/**
	 * Devuelve el recurso identificado por <strong>id</strong>
	 * @param id La identificación del recurso
	 * @return La string recurso
	 */
	public String getString(String id){
		return bundle.getString(id);
	}
	
	/**
	 * Devuelve el mnemónico identificado por <strong>id</strong>
	 * @param id La identificación del mnemónico
	 * @return El mnemónico
	 */
	public char getChar(String id){
		return bundle.getString(id).toCharArray()[0];
	}
	
	public void formatBorder(JComponent component, String id){
		((TitledBorder)component.getBorder()).setTitle(getString(id));
	}

}
