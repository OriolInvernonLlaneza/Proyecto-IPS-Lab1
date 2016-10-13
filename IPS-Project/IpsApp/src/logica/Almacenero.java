package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConsultasMyShop;

public class Almacenero {
	
	String id;
	String password;
	String nombre;
	String apellidos;
	List<OrdenDeTrabajo> ordenesDeTrabajo = new ArrayList<OrdenDeTrabajo>();
	
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public List<OrdenDeTrabajo> getOrdenesDeTrabajo() {
		return ordenesDeTrabajo;
	}


	public void setOrdenesDeTrabajo(List<OrdenDeTrabajo> ordenesDeTrabajo) {
		this.ordenesDeTrabajo = ordenesDeTrabajo;
	}


	public void procesarOrdenDeTrabajo(OrdenDeTrabajo ot) throws SQLException {
		boolean incidencia = prepararPaquete(ot);
		if(!incidencia) {
			marcarParaEmpaquetar(ot);
			boolean coincidenProductosRecogidos = comprobarProductosRecogidos(ot);
			if(coincidenProductosRecogidos) {
				empaquetar(ot); // Todo ha ido bien
			}
			else {
				anotarIncidencia(ot); // Incidencia: No coinciden los productos recogidos con los de la OT
			}
		}
		else {
			anotarIncidencia(ot); // Incidencia: Problema al intentar preparar el paquete
		}
	}
	
	
	/** El almacenero recorre el almac�n preparando el paquete
	 * @param ordenDeTrabajo
	 * @return true si ha habido incidencia, false si todo ha ido bien
	 */
	public boolean prepararPaquete(OrdenDeTrabajo ordenDeTrabajo) {
		//Floyd, anotar los productos que recoge el almacenero
		return false;
	}
	
	public void marcarParaEmpaquetar(OrdenDeTrabajo ot) throws SQLException {
		//Se marca para empaquetar, lo cual no significa que se vaya a empaquetar, primero hay que comprobar que los productos recogidos son los que hab�a en la OT
		ConsultasMyShop.cambiarEstadoOrdenDeTrabajo("Marcada para empaquetar", ot.getPedido().getId());
	}
	
	public void anotarIncidencia(OrdenDeTrabajo ot) throws SQLException {
		//Poner en la base de datos que ha habido una incidencia con esa OT
		ConsultasMyShop.cambiarEstadoOrdenDeTrabajo("Incidencia", ot.getPedido().getId());
	}
	
	public boolean comprobarProductosRecogidos(OrdenDeTrabajo ot) {
		// �Son los productos que recogi� el almacenero los que hab�a en la orden de trabajo?
		return false;
	}
	
	public void empaquetar(OrdenDeTrabajo ot) throws SQLException {
		//Generar el paquete final con su etiqueta y albar�n, y �a�adirlo a la base de datos?
		ConsultasMyShop.cambiarEstadoOrdenDeTrabajo("Empaquetada", ot.getPedido().getId());
	}
	
	
	


}
