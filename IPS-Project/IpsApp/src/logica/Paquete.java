package logica;

import java.sql.Timestamp;

public class Paquete {
	
	private String id;
	private String idPedido;
	private Timestamp fechaEnvoltura;
	private Pedido pedido;
	
	public Paquete(OrdenDeTrabajo ot) {
		// Buscar una manera de generar IDs no repetidos
		id = ot.getPedido().getId(); // Provisionalmente, para asegurar que no se repite el ID
		idPedido = ot.getPedido().getId();
		fechaEnvoltura =  new java.sql.Timestamp(System.currentTimeMillis()); // new Timestamp(System.currentTimeMillis());
	}

	public String getId() {
		return id;
	}

	public void setId(String idPaquete) {
		this.id = idPaquete;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public Timestamp getFechaEnvoltura() {
		return fechaEnvoltura;
	}

	public void setFechaEnvoltura(Timestamp fechaEnvoltura) {
		this.fechaEnvoltura = fechaEnvoltura;
	}
	
	/**Datos del pedido
	 * @return
	 */
	public String generarEtiqueta() {
		return "ID: " + idPedido + "Fecha de envoltura: " + fechaEnvoltura.toString(); 
	}
	
	/**Datos del cliente, direccion y precio
	 * @return
	 */
	public String generarAlbaran() {
		String datos = "Nombre: " + pedido.getCliente().getName() + 
				" Apellidos: " + pedido.getCliente().getApellidos() + 
				" Direccion: " + pedido.getCliente().getDireccion() +
				" Precio: " + pedido.getPrecio();
		return datos;
	}
	
	

}
