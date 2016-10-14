package logica;

public class Producto implements Comparable<Producto>{
	
	private String id;
	private String nombre;
	private String localizacion;
	private double precio;
	private double stock;
	private String descripcion;
	
	
	public Producto(String id, String nombre, String descripcion, double precio, double stock, String localizacion) {
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion=descripcion;
		this.localizacion = localizacion;
		this.stock = stock;
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


	public double getStock() {
		return stock;
	}


	public void setStock(double stock) {
		this.stock = stock;
	}
	
	public String toString(){
		return nombre;
	}
	
	@Override
	public int compareTo(Producto producto){
		return producto.getId().toLowerCase().compareTo(this.getId().toLowerCase());
	}
	
	public boolean equals(Producto producto){
		return this.id.toLowerCase().equals(producto.getId().toLowerCase());
	}
	
	

}
