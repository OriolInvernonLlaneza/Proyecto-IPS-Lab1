package clasificadores;

/**
 * Clase que especifica, usando 3 indicadores, la posicion de un elemento dentro de un objeto Almacen
 * Notas: el sistema distingue lo siguiente:
 *  - Una estanteria se especifica como:
		 *  - La cara: las estanterias tienen dos caras distintas, anterior y posterior
		 *  - La mitad: cada cara de una estanteria se divide en dos mitades
 * 	- El pasillo: el espacio entre estanterias
 * Los 3 indicadores son:
 * 	- Estanteria: la linea de estanterias a la que pertenece el elemento
 *  - Cara: anterior o posterior
 *  - Posicion: dentro de la estanteria
 *  
 * @author Nicolás
 *
 */
public class PosicionEstanteria {
	
	private int estanteria;
	private Cara cara;
	private int posicion;
	
	public PosicionEstanteria(int estanteria, Cara cara, int posicion) {
		this.estanteria = estanteria;
		this.cara = cara;
		this.posicion = posicion;
	}
	

	public int getEstanteria() {
		return estanteria;
	}


	public void setEstanteria(int estanteria) {
		this.estanteria = estanteria;
	}


	public Cara getCara() {
		return cara;
	}


	public void setCara(Cara cara) {
		this.cara = cara;
	}


	public int getPosicion() {
		return posicion;
	}


	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}


	/**
	 * Comprueba si las posiciones indicadas por este y el elemento dado son contrarias están en el lado contrario de una estanteria.
	 * Nota: no tiene que ser la misma estanteria, solo comprueba las caras en las que estan
	 * @param pos La otra posicion
	 * @return Verdadero si una estan en la misma cara. Falso si una esta en la cara anterior y la otra en la posterior, o viceversa
	 */
	public boolean estanteriaContraria(PosicionEstanteria pos){
		return !pos.cara.equals(this.cara);
	}
	
	/**
	 * Indica cuantos pasillos de estanterias separan ambas posiciones
	 * @param pos La otra posicion
	 * @return El entero que indica cuantos pasillos separan las estanterias
	 */
	public int separacionEstanterias(PosicionEstanteria pos){
		return Math.abs(pos.estanteria - this.estanteria);
	}
	
	/**
	 * Comprueba si estan en la misma estanteria exacta
	 * @param pos La otra posicion
	 * @return Verdadero, si comparten estanteria. Falso en caso contrario
	 */
	public boolean mismaEstanteria(PosicionEstanteria pos){
		return pos.estanteria == estanteria;
	}
	
	/**
	 * Determina si los elementos estan en la misma mitad
	 * @param pos La otra posicion
	 * @param mitad La mitad de la estanteria
	 * @return Verdadero, si estan en la misma mitad. Falso en caso contrario
	 */
	public boolean mismaMitadEstanteria(PosicionEstanteria pos, int mitad){
		if(this.posicion >= mitad)
			return pos.posicion > mitad;
		else
			return pos.posicion < mitad;
				
	}

}
