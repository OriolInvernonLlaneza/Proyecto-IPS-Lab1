package database;

import java.util.Random;

public class GeneradorContrasena implements Generador {
	
	private String param;
	
	public GeneradorContrasena(String param){
		this.param = param;
	}

	@Override
	public String generarID() {
		String result = "";
		for(int i = 0; i < param.length(); i++){
			int k = new Random().nextInt(param.length());
			char parte = param.charAt(k); 
			result += parte;
		}
		return result;
	}

}
