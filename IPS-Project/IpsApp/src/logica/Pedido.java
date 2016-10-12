package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.activity.InvalidActivityException;
//Por ahora util, cuando se vincule a la base de datos deberia ser sql?

public class Pedido {
	
	//A partir de aqui todo es un esquema para desarollar la interfaz: Almacenero Trabajo.
	// La id del pedido es un n�mero ascendente. De ah� el metodo "getUltimaIDPedido" de Consultas
    private String id;
    private Date fecha;
    private int tamano;
    private double precio;
    
    private Cliente cliente; //Infraestructura para cuando manejemos m�s cosas de los clientes
    private String idUsuario;
    
    private List<Producto> productos;
    private HashMap<String, UnidadProducto> agrupacion;
    
    
	public Pedido(String id, String idUsuario, Date fecha, int tamano, double precio, String direccion, List<Producto> productos) {
		this.id=id;
		this.fecha=fecha;
		this.tamano=tamano;
		this.precio=precio;
		this.productos=productos;
		this.idUsuario = idUsuario;
		
		//Necesitamos una construcción que guarde los productos y sus cantidades, de momento dejaremos "productos" por aqu�, pero no creo que la necesitemos mucho más
		agrupacion = convertirAgrupacion(productos);
		
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public HashMap<String, UnidadProducto> getAgrupacion() {
		return agrupacion;
	}
	
	public String getId() {
		return id;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public double getPrecio() {
		return precio;
	}
	public String toString(){
		return "#" + id + "--" + fecha;
	}
	
	public int getCantidad(String idProducto){
		if(agrupacion.containsKey(idProducto))
			return agrupacion.get(idProducto).getCantidad();
		else return 0;
	}
	
    public static HashMap<String, UnidadProducto> convertirAgrupacion(List<Producto> productos){
    	HashMap<String, UnidadProducto> agrupacion = new HashMap<String, UnidadProducto>();
    	for(Producto producto : productos){
			if(agrupacion.containsKey(producto.getId())){
				agrupacion.get(producto.getId()).anadirProducto();
			}
			else{
				agrupacion.put(producto.getId(), new UnidadProducto(producto, 1));
			}	
		}
    	return agrupacion;
    	
    }
    

}
