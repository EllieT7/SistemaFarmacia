package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Laboratorio extends Persona {
	private String Laboratorio;
	public Laboratorio(String cod, String nombre, String telefono, String laboratorio) {
		super(cod, nombre, telefono);
		Laboratorio = laboratorio;
	}

	public String getLaboratorio() {
		return Laboratorio;
	}

	@Override
	public String toString() {
		return Laboratorio;
	}

	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String vendedor = getNombre();
			String labo = getLaboratorio();
			String telf = getTelefono();
			String query = "insert into laboratorio" + "(vendedor, telefono, laboratorio, activo) values " + "(?,?,?,?)";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, vendedor);
			s.setString(2, telf);
			s.setString(3, labo);
			s.setBoolean(4, true);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ocurrió un error, intente nuevamente");
		}
	}

	public void modificar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			int cod = Integer.parseInt(getCod());
			String vendedor = getNombre();
			String lab = getLaboratorio();
			String telf = getTelefono();
			PreparedStatement s;
			String query = "update laboratorio set vendedor = ?, telefono = ?, laboratorio = ? where codLab = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, vendedor);
			s.setString(2, telf);
			s.setString(3, lab);
			s.setInt(4, cod);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}

	public static void eliminar(int cod){
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "update laboratorio set activo = false where codLab="+cod;
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}

	}
	public static void recuperar(int cod){
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "update laboratorio set activo = true where codLab= "+cod;
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e+"hola");
		}

	}
	 
}
