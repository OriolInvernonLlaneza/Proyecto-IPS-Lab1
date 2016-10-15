package gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

public class ResourceManager {
	
	private static ResourceManager manager;
	private final String BUNDLE = "rcs/textos";
	
	private ResourceBundle bundle;
	private SimpleDateFormat formato;
	
	private ResourceManager(Locale locale){
		try {
			bundle = ResourceBundle.getBundle(BUNDLE, locale);
			formato = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss", locale);
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
	
	/**
	 * Dada un elemento que posee TitledBorder, le cambia el t�tulo por el texto representado por la id
	 * @param component El componente que tiene la TitledBorder
	 * @param id La id del recurso
	 */
	public void formatBorder(JComponent component, String id){
		((TitledBorder)component.getBorder()).setTitle(getString(id));
	}
	
	public String cambiarFechaAZona(Date fecha){
		return formato.format(fecha).toString();
	}

}
