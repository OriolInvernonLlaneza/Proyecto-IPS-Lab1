package logica;
import java.util.ArrayList;
import java.util.List;



public class OrdenDeTrabajo {
	
	private Almacenero almacenero;
	private Pedido pedido;
	private String estado;
	private List<GrupoProducto> productosRecogidos = new ArrayList<GrupoProducto>(); // Simula el carrito que lleva el almacenero para recoger los productos
	
	public OrdenDeTrabajo(Almacenero almacenero, Pedido pedido, String estado) {
		this.almacenero = almacenero;
		this.pedido = pedido;
		this.estado = estado;
	}

	public Almacenero getAlmacenero() {
		return almacenero;
	}

	public void setAlmacenero(Almacenero almacenero) {
		this.almacenero = almacenero;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<GrupoProducto> getProductosRecogidos() {
		return productosRecogidos;
	}

	public void setProductosRecogidos(List<GrupoProducto> productosRecogidos) {
		this.productosRecogidos = productosRecogidos;
	}
	
	//Metodo para anadir productos a la orden de trabajo.
	public void anadirProducto(GrupoProducto gProducto){
		//Miramos si el producto esta en la lista.
		if(productosRecogidos.contains(gProducto)){
			//Si lo esta entonces solo tenemos que añadir una unidad en ese producto.
			productosRecogidos.get(productosRecogidos.indexOf(gProducto)).anadirProducto();;
		}
		else{
			//Si no, tenemos que crear un nuevo GrupoProducto con el contador de unidades a 1 para no modificar el presente en los pedidos.
			productosRecogidos.add(new GrupoProducto(gProducto.getProducto(),1));
		}
	}

}
