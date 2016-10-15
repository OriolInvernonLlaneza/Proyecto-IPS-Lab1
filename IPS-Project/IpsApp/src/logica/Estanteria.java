package logica;

import java.util.ArrayList;
import java.util.List;

public class Estanteria {
	
	private List<Almacenado> caraAnterior;
	private List<Almacenado> caraPosterior;
	
	public Estanteria(int longitud){
		this.caraAnterior = new ArrayList<Almacenado>(longitud);
		this.caraPosterior = new ArrayList<Almacenado>(longitud);
	}

	public List<Almacenado> getCaraAnterior() {
		return caraAnterior;
	}

	public List<Almacenado> getCaraPosterior() {
		return caraPosterior;
	}

}
