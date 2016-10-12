package logica;

import java.util.ArrayList;
import java.util.List;

public class OrdenDeTrabajo {
	
	private Almacenero almacenero;
	private Pedido pedido;
	private String estado;
	private List<Producto> productosRecogidos = new ArrayList<Producto>();
	
	public void marcarParaEmpaquetar() {
		/* Se marca para empaquetar, lo cual no significa que se vaya a empaquetar, primero hay que comprobar
		que los productos recogidos son los que había en la OT*/
	}
	
	public void anotarIncidencia() {
		//Poner en la base de datos que ha habido una incidencia con esa OT
	}
	
	public boolean comprobarProductosRecogidos() {
		// ¿Son los productos que recogió el almacenero los que había en la orden de trabajo?
		return false;
	}
	
	public void empaquetar() {
		//Generar el paquete final con su etiqueta y albarán, y añadirlo a la base de datos
	}
	
	

}
