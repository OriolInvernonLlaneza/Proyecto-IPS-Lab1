package logica;
import java.util.ArrayList;
import java.util.List;



public class OrdenDeTrabajo {
	
	private Almacenero almacenero;
	private Pedido pedido;
	private String estado;
	private List<UnidadProducto> productosRecogidos = new ArrayList<UnidadProducto>();

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

	public List<UnidadProducto> getProductosRecogidos() {
		return productosRecogidos;
	}

	public void setProductosRecogidos(List<UnidadProducto> productosRecogidos) {
		this.productosRecogidos = productosRecogidos;
	}

}
