package logica;

public class GrupoProducto implements Comparable<GrupoProducto>, Almacenado{
	
	private Producto producto;
	private int cantidad;
	
	public GrupoProducto(Producto producto, int cantidad) {
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
	
	public void quitarProducto(){
		this.cantidad -= 1;
	}
	
	public String getID(){
		return producto.getId();
	}
	
	@Override
	public int compareTo(GrupoProducto arg0) {
		return this.producto.compareTo(arg0.getProducto());
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof GrupoProducto){
			GrupoProducto producto = (GrupoProducto) obj;
			return this.producto.equals(producto.getProducto());
		}
		else return false;
	}

	public String toString(){
		String string = producto.getNombre() ;
		if(cantidad > 1)
			string+=" - " + cantidad + "x";
		return string;
		
	}

	@Override
	public String getPosicion() {
		return this.producto.getLocalizacion();
	}
	
	
	
	

}
