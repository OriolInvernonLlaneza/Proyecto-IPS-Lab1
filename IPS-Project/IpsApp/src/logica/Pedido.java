package logica;

import java.util.Date;
//Por ahora util, cuando se vincule a la base de datos deberia ser sql?

public class Pedido {
	
	//A partir de aqui todo es un esquema para desarollar la interfaz: Almacenero Trabajo.
    private int id;
    private Date fecha;
    private float tamaño;
    private Producto[] productos;
    
    
	public Pedido(int id, Date fecha, float tamaño, Producto[] productos) {
		this.id=id;
		this.fecha=fecha;
		this.tamaño=tamaño;
		this.productos=productos;
		
	}
	public int getId() {
		return id;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getTamaño() {
		return tamaño;
	}
	public Producto[] getProductos() {
		return productos;
	}
	
    
    

}
