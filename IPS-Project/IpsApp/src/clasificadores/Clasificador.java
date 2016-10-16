package clasificadores;

import java.util.List;
import java.util.Set;

import logica.Almacenado;

public interface Clasificador<T extends Almacenado> {
	
	/**
	 * Dada una lista, devuelve los objetos ordenados dada una posición suya.
	 * @param objetos La lista para ordenar
	 * @return La lista ordenada
	 */
	public List<T> ordenar(Set<T> objetos);

}
