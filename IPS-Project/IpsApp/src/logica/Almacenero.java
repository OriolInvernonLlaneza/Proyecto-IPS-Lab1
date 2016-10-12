package logica;

import java.util.ArrayList;
import java.util.List;

public class Almacenero {
	
	String id;
	String password;
	String nombre;
	String apellidos;
	List<OrdenDeTrabajo> ordenesDeTrabajo = new ArrayList<OrdenDeTrabajo>();
	
	public void procesarOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo) {
		boolean incidencia = prepararPaquete(ordenDeTrabajo);
		if(!incidencia) {
			ordenDeTrabajo.marcarParaEmpaquetar();
		}
		else {
			ordenDeTrabajo.anotarIncidencia();
		}
	}
	
	
	/** El almacenero recorre el almacén preparando el paquete
	 * @param ordenDeTrabajo
	 * @return true si ha habido incidencia, false si todo ha ido bien
	 */
	public boolean prepararPaquete(OrdenDeTrabajo ordenDeTrabajo) {
		
		return false;
	}
	


}
