package logica;

public class Producto {
	
	private int id;
	private String name;
	private String localizacion;
	
	
	public Producto(int id, String name, String localizacion) {
		this.id = id;
		this.name = name;
		this.localizacion = localizacion;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getLocalizacion() {
		return localizacion;
	}
	
	
	
	

}
