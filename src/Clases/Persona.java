package Clases;

public class Persona {
	private String cod;
	private String nombre;
	private String telefono;

	public Persona(String cod, String nombre, String telefono) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getCod() {
		return cod;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}	
}
