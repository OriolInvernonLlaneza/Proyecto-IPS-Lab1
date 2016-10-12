package database;

import java.util.Random;

public class GeneradorIDUsuario implements GeneradorID{
	
	private Random rng;
	private static final int ID_LENGTH = 100;
	private String nombre;
	private String apellido;

	
	public GeneradorIDUsuario(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		rng = new Random();
	}
	
	@Override
	public String generarID() {
		String nombreCorto = nombre.substring(0, 2);
		String apellidoCorto = apellido.substring(0, 2);
		String code = Double.toString((rng.nextInt() * ID_LENGTH) + 1);
		return nombreCorto + apellidoCorto + "#" + code;
	}

}
