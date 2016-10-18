package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


//Por ahora util, cuando se vincule a la base de datos deberia ser sql?

public class Pedido {
	
	//A partir de aqui todo es un esquema para desarollar la interfaz: Almacenero Trabajo.
	// La id del pedido es un numero ascendente. De ahi el metodo "getUltimaIDPedido" de Consultas
    private String id;
    private Date fecha;
    private int tamano;
    private double precio;
    
    private Cliente cliente; //Infraestructura para cuando manejemos mas cosas de los clientes
    private String idUsuario;
    
    private HashMap<String, GrupoProducto> agrupacion;
    
    
	public Pedido(String id, String idUsuario, Date fecha, int tamano, double precio, String direccion, List<GrupoProducto> productos) {
		this.id=id;
		this.fecha=fecha;
		this.tamano=tamano;
		this.precio=precio;
		this.idUsuario = idUsuario;
		this.agrupacion = new HashMap<String, GrupoProducto>();
		
		for(GrupoProducto producto : productos){
			agrupacion.put(producto.getID(), producto);
			this.precio += producto.getCantidad() * producto.getProducto().getPrecio();
		}
		
	}
	
	public void setAgrupacion(HashMap<String, GrupoProducto> agrupacion) {
		this.agrupacion = agrupacion;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public HashMap<String, GrupoProducto> getAgrupacion() {
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
	
	public List<String> getCodigos(){
		List<String> codigos= new ArrayList<String>();
		for (GrupoProducto gProducto: agrupacion.values()) {
			   codigos.add(gProducto.getProducto().getCodigoBarras());
			}
		
		return codigos;
	}
	
    

}
