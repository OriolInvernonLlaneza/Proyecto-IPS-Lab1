package logica;

import java.util.Date;
import java.util.List;
//Por ahora util, cuando se vincule a la base de datos deberia ser sql?

public class Pedido {
	
	//A partir de aqui todo es un esquema para desarollar la interfaz: Almacenero Trabajo.
    private String id;
    private Date fecha;
    private float tamano;
    private double precio;
    private List<Producto> productos;
    
    
	public Pedido(String id, Date fecha, float tamano, double precio, List<Producto> productos) {
		this.id=id;
		this.fecha=fecha;
		this.tamano=tamano;
		this.precio=precio;
		this.productos=productos;
		
	}
	public String getId() {
		return id;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getTamano() {
		return tamano;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	
	public String toString(){
		return "#" + id + fecha;
	}
    
    

}
