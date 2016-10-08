package logica;

public class Producto {
	
	private int id;
	private String nombre;
	private String localizacion;
	
	
	public Producto(int id, String nombre, String localizacion) {
		this.id = id;
		this.nombre = nombre;
		this.localizacion = localizacion;
	}


	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getLocalizacion() {
		return localizacion;
	}
	
	
	
	

}
