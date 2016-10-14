package logica;

public class ProductoPedido extends Producto {
	 private int cantidad;

	public ProductoPedido(String id, String nombre, String descripcion, double precio, double stock,
			String localizacion, int cantidad) {
		super(id, nombre, descripcion, precio, stock, localizacion);
		this.cantidad=cantidad;
	}
	
	public int getCantidad(){
		return cantidad;
	}

}
