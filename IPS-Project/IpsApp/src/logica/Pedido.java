package logica;

import java.util.Date;
//Por ahora util, cuando se vincule a la base de datos deberia ser sql?

public class Pedido {
	
	//A partir de aqui todo es un esquema para desarollar la interfaz: Almacenero Trabajo.
    private int id;
    private Date fecha;
    private float tamano;
    private Producto[] productos;
    
    
	public Pedido(int id, Date fecha, float tamano, Producto[] productos) {
		this.id=id;
		this.fecha=fecha;
		this.tamano=tamano;
		this.productos=productos;
		
	}
	public int getId() {
		return id;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getTamano() {
		return tamano;
	}
	public Producto[] getProductos() {
		return productos;
	}
	
    
    

}
