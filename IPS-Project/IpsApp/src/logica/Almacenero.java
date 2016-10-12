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
			boolean coincidenProductosRecogidos = ordenDeTrabajo.comprobarProductosRecogidos();
			if(coincidenProductosRecogidos) {
				ordenDeTrabajo.empaquetar(); // Todo ha ido bien
			}
			else {
				ordenDeTrabajo.anotarIncidencia(); // Incidencia: No coinciden los productos recogidos con los de la OT
			}
		}
		else {
			ordenDeTrabajo.anotarIncidencia(); // Incidencia: Problema al intentar preparar el paquete
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
	


}
