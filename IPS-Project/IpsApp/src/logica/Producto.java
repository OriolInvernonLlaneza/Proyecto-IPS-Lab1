package logica;

public class Producto {
	
	private int id;
	private String nombre;
	private String localizacion;
	private double precio;
	private String descripcion;
	
	
	public Producto(int id, String nombre, double precio,String descripcion, String localizacion) {
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion=descripcion;
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


	public double getPrecio() {
		return precio;
	}


	public String getDescripcion() {
		return descripcion;
	}
	
	
	
	

}
