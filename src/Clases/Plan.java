package Clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Plan {
	private String fecha;
	private String plan;
	private int admin;
	
	public Plan(String fecha, String plan, int admin) {
		super();
		this.fecha = fecha;
		this.plan = plan;
		this.admin = admin;
	}
	
	public int getAdmin() {
		return admin;
	}

	public String getFecha() {
		return fecha;
	}

	public String getPlan() {
		return plan;
	}
	
	public static String reporte(java.util.Date date) {
		String cad = "";
		try {
			SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
			String dateModif = formato.format(date);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from tarea where fecha = '"+dateModif+"';");
			while (rs.next()) {
				cad += "- "+rs.getString(3) +"\n";
			}
			if(cad.isEmpty()) {
				cad="- Nada para hoy";
			}
			conexion.close();	
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return cad;
	}

	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			Date fecha = Date.valueOf(getFecha());
			String desc = getPlan();
			int code = getAdmin();
			String query = "insert into tarea" + "(fecha, descripcion, administrador_codAdmin) values " + "(?,?,?)";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setDate(1, fecha);
			s.setString(2, desc);
			s.setInt(3, code);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public void modificar(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			Date fecha = Date.valueOf(getFecha());
			String desc = getPlan();
			int admin = getAdmin();
			PreparedStatement s;
			String query = "update tarea set fecha = ?, descripcion = ?, administrador_codAdmin = ? where codPlan = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setDate(1, fecha);
			s.setString(2, desc);
			s.setInt(3, admin);
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
			String query = "delete from tarea where codPlan = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, cod);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}
	}
}
