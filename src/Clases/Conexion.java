package Clases;

import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	Connection cn=null;
	Statement st;
	ResultSet rs;
	
	public Connection Conectar() throws SQLException, InstantiationException, IllegalAccessException {
		try {
			Class.forName("org.h2.Driver");
			cn = DriverManager.getConnection("jdbc:h2:file:./base/farmacia","sa","");
			return cn;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error de conexion" +e);
		}
		return cn;	
	}
}