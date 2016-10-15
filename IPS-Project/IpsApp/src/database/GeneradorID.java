package database;

import java.util.Random;

public abstract class GeneradorID implements Generador {
	
	private Random rng;
	private static final int ID_LENGTH = 100;
	
	public GeneradorID(){
		rng = new Random();
	}

	@Override
	public String generarID() {
		String code = Integer.toString((rng.nextInt() * ID_LENGTH) + 1);
		return completar(code);
	}
	
	protected abstract String completar(String code);

}
