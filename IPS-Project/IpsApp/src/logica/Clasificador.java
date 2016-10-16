package logica;

import java.util.List;

public interface Clasificador<T extends Almacenado> {
	
	/**
	 * Dada una lista, devuelve los objetos ordenados dada una posición suya 
	 * @param objetos La lista para ordenar
	 * @return La lista ordenada
	 */
	public List<T> ordenar(List<T> objetos);

}
