package logica;

public class Producto {
	
	private String id;
	private String nombre;
	private String localizacion;
	private double precio;
	private String descripcion;
	
	
	public Producto(String id, String nombre, double precio,String descripcion, String localizacion) {
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion=descripcion;
		this.localizacion = localizacion;
	}


	public String getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public String getLocalizacion() {
		return localizacion;
	}


	public double getPrecio() {
		return precio;
	}


	public String getDescripcion() {
		return descripcion;
	}
	
	
	
	

}
