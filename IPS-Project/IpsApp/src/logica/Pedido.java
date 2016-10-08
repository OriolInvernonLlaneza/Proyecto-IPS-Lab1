package logica;

import java.util.Date;
//Por ahora util, cuando se vincule a la base de datos deberia ser sql?

public class Pedido {
	
	//A partir de aqui todo es un esquema para desarollar la interfaz: Almacenero Trabajo.
    private int id;
    private Date fecha;
    private float tama�o;
    private Producto[] productos;
    
    
	public Pedido(int id, Date fecha, float tama�o, Producto[] productos) {
		this.id=id;
		this.fecha=fecha;
		this.tama�o=tama�o;
		this.productos=productos;
		
	}
	public int getId() {
		return id;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getTama�o() {
		return tama�o;
	}
	public Producto[] getProductos() {
		return productos;
	}
	
    
    

}
