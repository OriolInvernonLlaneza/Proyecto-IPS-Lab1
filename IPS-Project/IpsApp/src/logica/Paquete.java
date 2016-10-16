package logica;

import java.sql.Timestamp;

public class Paquete {
	
	private String id;
	private String idPedido;
	private Timestamp fechaEnvoltura;
	
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
	
	

}
