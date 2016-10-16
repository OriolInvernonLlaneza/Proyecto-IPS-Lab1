package logica;

public class UnidadProducto implements Comparable<UnidadProducto>{
	
	private Producto producto;
	private int cantidad;
	
	public UnidadProducto(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void anadirProducto(){
		this.cantidad += 1;
	}
	
	@Override
	public int compareTo(UnidadProducto arg0) {
		return this.producto.compareTo(arg0.getProducto());
	}
	
	
	
	

}
