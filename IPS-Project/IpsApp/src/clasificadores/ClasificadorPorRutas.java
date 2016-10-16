package clasificadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import logica.Almacen;
import logica.Almacenado;

/**
 * Clasificador de los elementos dados por la ruta de menor coste posible. Esta clase prioriza los caminos menos costosos. El coste se mide en la distancia que tiene que dar
 * @author Nicolás
 *
 * @param <T> Los elementos a clasificar. Debe ser posible identificarlos por un código que los posicione dentro del Almacen
 */
public class ClasificadorPorRutas<T extends Almacenado> implements Clasificador<T> {

	private final double ENTRAR_ALMACEN = 5.0;
	private final double CAMBIAR_CARA = 2.0;
	private final double CAMBIAR_ESTANTERIA = 7.0;
	private final double RECORRER_ESTANTERIA = 4.0;
	
	//Dijkstra
	private int[] pd; //Pasos que tomar
	public final static int INDEX_NOT_FOUND = -1; 
	//private static final double FLOYD_INFINITE = 99;
	
	private double[][] costes;
	private boolean[][] caminos;
	private List<NodoAlmacen<T>> elementos;
	
	private Almacen<T> almacen;
	
	/**
	 * Crea un clasificador que obtendra la mejor ruta a traves de un almacen equipado con tantos elementos (nodos)
	 * @param almacen
	 * @param tamano
	 */
	public ClasificadorPorRutas(Almacen<T> almacen, int tamano) {
		this.almacen = almacen;
		this.costes = new double[tamano][tamano];
		this.pd = new int[tamano];
		this.caminos = new boolean[tamano][tamano];
		
		this.elementos = new ArrayList<NodoAlmacen<T>>();
	}
	
	
	@Override
	public List<T> ordenar(Set<T> objetos) {
		
		for(T objeto : objetos){
			addNode(objeto);
		}
		
		for(int i = 0; i < getSize(); i++){
			for(int j = 0; j < getSize(); j++){
				NodoAlmacen<T> origen = elementos.get(i);
				NodoAlmacen<T> destino = elementos.get(j);
				if(i != j)
					addEdge(origen.getElemento(), destino.getElemento(), traducirCoste(origen, destino));
			}
		}
		
		hamiltonPath();
		List<T> resultado = new ArrayList<>(getSize());
		for(int i = 0; i< getSize(); i++)
			resultado.add(elementos.get(pd[i]).getElemento());
		
		return resultado;
	}
	
	
	private double traducirCoste(NodoAlmacen<T> origen, NodoAlmacen<T> destino){
		PosicionEstanteria posOrigen = origen.getPos();
		PosicionEstanteria posDestino = destino.getPos();
		double coste = 0;
		
		if(!posOrigen.mismaEstanteria(posDestino)){
			int espaciado = posOrigen.separacionEstanterias(posDestino);
			coste += espaciado * CAMBIAR_ESTANTERIA;
		}
		if(posOrigen.estanteriaContraria(posDestino))
			coste += CAMBIAR_CARA;
		if(!posOrigen.mismaMitadEstanteria(posDestino, getSize()/2))
			coste += RECORRER_ESTANTERIA;
		
		
		return coste;
	}
	
	private void hamiltonPath(){
		//Conseguir el nodo más cercano a almacen.getInicio()
		int start = -1;
		NodoAlmacen<T> origen = new NodoAlmacen<T>(null, almacen.getInicio());
		double coste = Double.MAX_VALUE;
		for(NodoAlmacen<T> elemento : elementos){
			double paso = traducirCoste(origen, elemento);
			if(paso < coste){
				coste = paso;
				start = elementos.indexOf(elemento);
			}
		}
		//Encontrado, marcarlo como visitado, empezar desde ahí
		elementos.get(start).setVisited(true);
		int actual = start;
		int size = getSize();
		pd[0] = start;
		int paso = 1;
		
		//Por cada iteracion, hasta que todos los nodos hayan sido tocados, buscamos el camino menos costoso
		while(!allVisited()){
			double min = Double.MAX_VALUE;
			int posible = actual;
			for(int i = 0; i < size; i++){
				if(i == actual || elementos.get(i).isVisited())
					continue;
				double costeActual = costes[actual][i];
				if(costeActual < min){
					min = costeActual;
					posible = i;
				}
			}
			elementos.get(posible).setVisited(true);
			actual = posible;
			pd[paso] = actual;
			paso++;
		}
		
	}
	
	private boolean allVisited(){
		for(NodoAlmacen<T> nodo : elementos)
			if(!nodo.isVisited())
				return false;
		return true;
	}
	
	
	//---------------Metodos del grafo
	
	/**
	 * Cantidad de elementos que visitar
	 * @return Tamaño del "grafo" implicito
	 */
	public int getSize()
	{
		return elementos.size();
	}
	
	/**
	 * Posicion del nodo solicitado
	 * @param element El nodo
	 * @return La posicion del nodo
	 */
	public int getNode(T element)
	{
		for(NodoAlmacen<T> node: elementos)
			if(node.getElemento().equals(element))
				return elementos.indexOf(node);
		return INDEX_NOT_FOUND;
	}
	
	
	/**
	 * Incorpora el elemento al grafo implicito
	 * @param element El elemento a introducir
	 * @throws Exception En el caso de que el elemento exista de antemano
	 */
	public void addNode(T element) 
	{
		int size = getSize();
		int index = getNode(element);
		if(index != INDEX_NOT_FOUND)
			throw new IllegalArgumentException("Elemento ya existente");
		elementos.add(new NodoAlmacen<T>(element, almacen.traducirIdentificador(element)));
		for(int i = 0; i<getSize(); i++)
		{
			caminos[size][i] = false;
			caminos[i][size] = false;
			costes[size][i] = 0.0;
			costes[i][size] = 0.0;
		}
		
	}
	

	/**
	 * Incorpora un camino entre dos nodos dados
	 * @param origin Nodo origen
	 * @param destination Nodo destino
	 * @param costes Coste para atravesar el camino
	 * @throws Exception En caso de que el enlace ya exista
	 */
	private void addEdge(T origin, T destination, double costes)
	{
		if(!existsEdge(origin, destination))
		{
			int start = getNode(origin);
			int end = getNode(destination);
			if(start == -1 || end == -1)
				throw new IllegalArgumentException("Uno de los dos elementos no existe");
			this.caminos[start][end] = true;
			this.costes[start][end] = costes;
		}
		else 
			throw new IllegalArgumentException("El camino ya existe");
	}
	
	/**
	 * Elimina un nodo del grafo
	 * @param element El elemento a eliminar
	 * @throws Exception En el caso de que el nodo no exista
	 */
	private void removeNode(T element) 
	{
		int pos = getNode(element);
		int size = getSize();
		if(pos == INDEX_NOT_FOUND)
			throw new IllegalArgumentException("El elemento no existe");
		
		for(int j = 0; j<size; j++)
		{
			/*
			 * Here we copy the last column 
			 * and the last row of the matrix
			 * into the row and column of the deleted node
			 */
			caminos[j][pos] = caminos[j][size-1];
			caminos[pos][j] = caminos[size-1][j];
			costes[j][pos] = costes[j][size-1];
			costes[pos][j] = costes[size-1][j];
			
			//Diagonal loop
			caminos[pos][pos] = caminos[size-1][size-1];
			costes[pos][pos] = costes[size-1][size-1];
			
			caminos[j][pos] = false;
		}
		NodoAlmacen<T> node = elementos.get(pos);
		node.setElemento(elementos.get(size-1).getElemento());
		elementos.remove(pos);
		
	}
	
	/**
	 * Elimina un enlace que conecta los dos nodos dados
	 * @param origin El nodo de inicio
	 * @param destination El nodo de destino
	 * @throws Exception Si el camino no existe entre esos dos nodos
	 */
	private void removeEdge(T origin, T destination) 
	{
		if(existsEdge(origin, destination))
		{
			int i = getNode(origin);
			int j = getNode(destination);
			caminos[i][j] = false;
			costes[i][j] = 0;
		}
		else
			throw new IllegalArgumentException("El camino no existe entre los nodos dados");
	}
	
	/**
	 * Comprueba si existe conexion entre los nodos
	 * @param origin El nodo de inicio
	 * @param destination El nodo en el que termina
	 * @return Verdadero si existe, falso en caso contrario
	 * @throws Exception En caso de que alguno de los nodos no exista
	 */
	private boolean existsEdge(T origin, T destination) 
	{
		int start = getNode(origin);
		int end = getNode(destination);
		if(start == -1 || end == -1)
			throw new IllegalArgumentException("Uno de los dos elementos no existe");
		return caminos[start][end];
	}
	
}
