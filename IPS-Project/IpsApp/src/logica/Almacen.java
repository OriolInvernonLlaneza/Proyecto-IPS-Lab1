package logica;

import java.util.ArrayList;
import java.util.List;

public class Almacen {
	
	//Estanterias organizadas por cercania
	private List<Estanteria> estanterias;
	private int numeroEstanterias;
	
	public Almacen(int numeroEstanterias, int longitud){
		this.estanterias = new ArrayList<Estanteria>(numeroEstanterias);
		for(int i = 0; i<numeroEstanterias; i++){
			estanterias.add(new Estanteria(longitud));
		}
	}
	
	

}
