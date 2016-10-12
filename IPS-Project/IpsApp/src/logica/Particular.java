package logica;

public class Particular implements Cliente {
	
	private String id;
	private String nombre;
	private String apellidos;
	private String direccionEnvio;

	//IDs de usuario: Nombre+Apellidos+#+RNGnumber
	
	public Particular(String id, String nombre, String apellidos, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getName() {
		return nombre;
	}

	@Override
	public String getApellidos() {
		return apellidos;
	}
	
	public String getDireccion(){
		return direccionEnvio;
	}

}
