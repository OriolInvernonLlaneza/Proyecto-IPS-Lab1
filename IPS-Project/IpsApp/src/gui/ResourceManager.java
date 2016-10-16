package gui;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

public class ResourceManager {
	
	private static ResourceManager manager;
	private final String BUNDLE = "rcs/textos";
	
	private ResourceBundle bundle;
	private SimpleDateFormat formato;
	private Locale locale;
	
	private ResourceManager(Locale locale){
		try {
			bundle = ResourceBundle.getBundle(BUNDLE, locale);
			formato = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss", locale);
			this.locale = locale;
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
	 * Devuelve el mnemonico identificado por <strong>id</strong>
	 * @param id La identificacion del mnemonico
	 * @return El mnemonico
	 */
	public char getChar(String id){
		return bundle.getString(id).toCharArray()[0];
	}
	
	/**
	 * Dada un elemento que posee TitledBorder, le cambia el titulo por el texto representado por la id
	 * @param component El componente que tiene la TitledBorder
	 * @param id La id del recurso
	 */
	public void formatBorder(JComponent component, String id){
		((TitledBorder)component.getBorder()).setTitle(getString(id));
	}
	
	/**
	 * Convierte la fecha usando el Locale correspondiente
	 * @param fecha
	 * @return
	 */
	public String cambiarFechaAZona(Date fecha){
		return formato.format(fecha).toString();
	}
	
	public String formatearNumeros(Number numero){
		return NumberFormat.getCurrencyInstance().format(numero);
	}

}
