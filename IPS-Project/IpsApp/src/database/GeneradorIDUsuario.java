package database;

import java.util.Random;

public class GeneradorIDUsuario extends GeneradorID{

	private String nombre;
	private String apellido;

	
	public GeneradorIDUsuario(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		
	}

	@Override
	protected String completar(String code) {
		String nombreCorto = nombre.substring(0, 2);
		String apellidoCorto = apellido.substring(0, 2);
		return nombreCorto + apellidoCorto + "#" + code;
	}
	
	

}
