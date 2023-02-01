package Clases;

import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public class Acceso {
	
	private String user;
	private String pass;
	private boolean estado;
	
	public Acceso(String user, String pass, boolean estado) {
		super();
		this.user = user;
		this.pass = pass;
		this.estado = estado;
	}
	public Acceso(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	public boolean isEstado() {
		return estado;
	}
	
	public boolean comprobar() throws HeadlessException, IOException, SQLException, InstantiationException, IllegalAccessException {
		Conexion con=new Conexion();
		Connection conexion = (Connection) con.Conectar();
		Statement s=(Statement)conexion.createStatement();
		ResultSet rs= (ResultSet) s.executeQuery("select * from administrador where activo =  true");
		String usuario = getUser();
		String pass = getPass();
		boolean flag = false;
		while(rs.next()) {
			String user= rs.getString(2);
			String password = rs.getString(3);
			if(user.equals(usuario)&&password.equals(pass)) {
				JOptionPane.showMessageDialog(null, "Bienvenid@ "+usuario+"!","Acceso permitido",-1,new ImageIcon(Acceso.class.getResource("/icon/checked.png")));
				flag = true;
				conexion.close();
				break;
			}
		}
		conexion.close();
		return flag;
	}
	
	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String nombre = getUser();
			String pass = getPass();
			boolean estado = isEstado();
			String query = "insert into administrador" + "(usuario, password, editor, activo) values " + "(?,?,?,?)";
			try {
				s = (PreparedStatement) conexion.prepareStatement(query);
				s.setString(1, nombre);
				s.setString(2, pass);
				s.setBoolean(3, estado);
				s.setBoolean(4, true);
				s.executeUpdate();

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public void modificar(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			String nombre = getUser();
			String pass = getPass();
			boolean estado = isEstado();
			PreparedStatement s;
			String query = "update administrador set usuario = ?, password = ?, editor = ? where codAdmin = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, nombre);
			s.setString(2, pass);
			s.setBoolean(3, estado);
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
			String query = "update administrador set activo = false where codAdmin = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, cod);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}
	}
}
