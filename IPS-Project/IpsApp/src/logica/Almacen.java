package logica;

import java.util.ArrayList;
import java.util.List;

import clasificadores.Cara;
import clasificadores.PosicionEstanteria;

/**
 * Estructura que define la estructura interna de un almacen. Un almacen es un edificio cuadrado o rectangular que posee una seccion interior de estanterias con diversos
 * objetos. Las estanterias son de doble cara, una tras otra, en sucesion a lo largo del almacen.
 * @author Nicolás
 *
 */
public class Almacen<T extends Almacenado> {
	
	//Estanterias organizadas por cercania
	private List<Estanteria> estanterias;
	
	private final PosicionEstanteria inicio = new PosicionEstanteria(0, Cara.Anterior, 0);
	
	/**
	 * Recrea una estructura simulando un almacen con estanterias.
	 * @param numeroEstanterias Cuantas estanterias/hileras tendra el almacen
	 * @param longitud La longitud de las estanterias
	 */
	public Almacen(int numeroEstanterias, int longitud){
		this.estanterias = new ArrayList<Estanteria>(numeroEstanterias);
		for(int i = 0; i<numeroEstanterias; i++){
			estanterias.add(new Estanteria(longitud));
		}
	}
	
	/**
	 * Dado un elemento, convierte su identificador en una posicion dentro de este almacen
	 * @param elemento El elemento a traducir
	 * @return Un objeto PosicionEstanteria con toda la informacion
	 */
	public PosicionEstanteria traducirIdentificador(Almacenado elemento){
		String id = elemento.getPosicion(); // Numero + 0(cara anterior)/1(cara posterior) + Numero
		int estanteria = Character.getNumericValue(id.charAt(0));
		Cara cara = Cara.values()[Character.getNumericValue(id.charAt(1))];
		int posicion = Character.getNumericValue(id.charAt(2));
		return new PosicionEstanteria(estanteria, cara, posicion);
		
	}

	/**
	 * Retorna la posicion mas proxima al inicio del recorrido.
	 * @return
	 */
	public PosicionEstanteria getInicio() {
		return inicio;
	}

}
