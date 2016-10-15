package logica;

import java.util.List;

public class ClasificadorPorRutas implements Clasificador<Producto> {

	private final double ENTRAR_ALMACEN = 5.0;
	private final double CAMBIAR_CARA = 2.0;
	private final double CAMBIAR_ESTANTERIA = 7.0;
	private final double RECORRER_ESTANTERIA = 4.0;
	
	private Almacen almacen;
	
	public ClasificadorPorRutas(Almacen almacen) {
		this.almacen = almacen;
	}
	
	@Override
	public List<Producto> ordenar(List<Producto> objetos) {
		// TODO Auto-generated method stub
		return null;
	}

}
