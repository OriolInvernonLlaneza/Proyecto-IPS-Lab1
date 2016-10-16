package clasificadores;

import logica.Almacenado;

public class NodoAlmacen<T extends Almacenado>{
	
	private T elemento;
	private PosicionEstanteria pos;
	private boolean visited;

	public NodoAlmacen(T elemento, PosicionEstanteria pos) {
		this.elemento = elemento;
		this.pos = pos;
		this.visited = false;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public PosicionEstanteria getPos() {
		return pos;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
