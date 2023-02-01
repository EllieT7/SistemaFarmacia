package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Cliente extends Persona {

	public Cliente(String cod, String nombre, String telefono) {
		super(cod, nombre, telefono);
	}

	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String ci = getCod();
			String nombre = getNombre();
			String telf = getTelefono();
			String query = "insert into cliente" + "(ci, nombre, telefono) values " + "(?,?,?)";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, ci);
			s.setString(2, nombre);
			s.setString(3, telf);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public void modificar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			String ci = getCod();
			String nombre = getNombre();
			String telf = getTelefono();
			PreparedStatement s;
			String query = "update cliente set nombre = ?, telefono = ? where ci = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, nombre);
			s.setString(2, telf);
			s.setString(3, ci);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}

	public static void eliminar(String cod){
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "delete from cliente where ci = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, cod);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}

	}

	public boolean comprobarID(String cod) {
		boolean existe = false;
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select cliente.ci from cliente");
			while (rs.next()) {
				String cod1 = rs.getString(1);
				if (cod1.equals(cod)) {
					existe = true;
					break;
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);	
		}
		return existe;
	}
}
