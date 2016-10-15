package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConsultasMyShop;

public class Almacenero {
	
	private String id;
	private String password;
	private String nombre;
	private String apellidos;
	private List<OrdenDeTrabajo> ordenesDeTrabajo = new ArrayList<OrdenDeTrabajo>(); // Todas las que tiene
	private OrdenDeTrabajo ordenDeTrabajoActual; // La que esta realizando en este momento, porque puede tener otras asignadas y sin hacer
	
	public Almacenero(String id, String password, String nombre, String apellidos) {
		this.id = id;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	

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
	
	public OrdenDeTrabajo getOrdenDeTrabajoActual() {
		return ordenDeTrabajoActual;
	}



	public void setOrdenDeTrabajoActual(OrdenDeTrabajo ordenDeTrabajoActual) {
		this.ordenDeTrabajoActual = ordenDeTrabajoActual;
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
	
	
	/** El almacenero recorre el almacén preparando el paquete
	 * @param ordenDeTrabajo
	 * @return true si ha habido incidencia, false si todo ha ido bien
	 */
	public boolean prepararPaquete(OrdenDeTrabajo ordenDeTrabajo) {
		//Floyd, anotar los productos que recoge el almacenero
		return false;
	}
	
	/**Se marca para empaquetar, lo cual no significa que se vaya a empaquetar, 
	 * primero hay que comprobar que los productos recogidos son los que había en la OT
	 * @param ot
	 * @throws SQLException
	 */
	public void marcarParaEmpaquetar(OrdenDeTrabajo ot) throws SQLException {
		ConsultasMyShop.cambiarEstadoOrdenDeTrabajo("Marcada para empaquetar", ot.getPedido().getId());
	}
	
	/**Poner en la base de datos que ha habido una incidencia con esa OT
	 * @param ot
	 * @throws SQLException
	 */
	public void anotarIncidencia(OrdenDeTrabajo ot) throws SQLException {
		ConsultasMyShop.cambiarEstadoOrdenDeTrabajo("Incidencia", ot.getPedido().getId());
	}
	
	/**¿Son los productos que recogió el almacenero los que había en la orden de trabajo?
	 * @param ot
	 * @return
	 */
	public boolean comprobarProductosRecogidos(OrdenDeTrabajo ot) {
		return true;
	}
	
	/**Generar el paquete final con su etiqueta y albarán, y añadirlo a la base de datos
	 * @param ot
	 * @throws SQLException
	 */
	public void empaquetar(OrdenDeTrabajo ot) throws SQLException {
		Paquete paquete = new Paquete(ot);
		ConsultasMyShop.addPaquete(paquete);
		ConsultasMyShop.cambiarEstadoOrdenDeTrabajo("Empaquetada", ot.getPedido().getId());
	}
	
	
	


}
