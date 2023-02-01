package Clases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CompraProd {
	private int codProd;
	private int codCompra;
	private int admin;
	private int cu;
	private int ca;
	private Date fechaVenc;
	private BigDecimal pu,pc;

	
	public CompraProd(int codProd, int codCompra, int admin, int cu, int ca, Date fechaVenc, BigDecimal pu, BigDecimal pc) {
		super();
		this.codProd = codProd;
		this.codCompra = codCompra;
		this.admin = admin;
		this.cu = cu;
		this.ca = ca;
		this.fechaVenc = fechaVenc;
		this.pu = pu;
		this.pc = pc;
	}
	

	public BigDecimal getPu() {
		return pu;
	}

	public BigDecimal getPc() {
		return pc;
	}
	public int getCodProd() {
		return codProd;
	}

	public int getCodCompra() {
		return codCompra;
	}

	public int getAdmin() {
		return admin;
	}

	public int getCu() {
		return cu;
	}

	public int getCa() {
		return ca;
	}

	public Date getFechaVenc() {
		return fechaVenc;
	}


	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			int codProd = getCodProd();
			int codCompra = getCodCompra();
			int admin = getAdmin();
			int cu = getCu();
			int ca = getCa();
			Date fechaVenc = getFechaVenc();
			String query = "insert into compra_producto"
					+ "(producto_cod,compra_codCompra, administrador_codAdmin, cantidadUni, cantidadCaja, fechaVenc, preciounidad, preciocaja, stockUniActual, stockCajaActual) values "
					+ "(?,?,?,?,?,?,?,?,?,?)";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, codProd);
			s.setInt(2, codCompra);
			s.setInt(3, admin);
			s.setInt(4, cu);
			s.setInt(5, ca);
			s.setDate(6, fechaVenc);
			s.setBigDecimal(7, getPu());
			s.setBigDecimal(8, getPc());
			s.setInt(9, getStock(codProd,"U"));
			s.setInt(10, getStock(codProd,"C"));
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
	public int getStock(int cod, String tipo) {
		int n = 0, aux;
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			if (tipo.equals("U")) {
				aux = 7;
			} else {
				aux = 8;
			}
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto");
			while (rs.next()) {
				if (cod == rs.getInt(1)) {
					n = rs.getInt(aux);
					break;
				}
			}
			conexion.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error " + e);
		}
		return n;

	}
	public void actualizarVenc() {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto;");
			while (rs.next()) {
				if(rs.getInt(1)==getCodProd()) {
					Date fecha = rs.getDate(6);
					if (getFechaVenc().before(fecha)) actualizar();
				}
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			System.out.println("Error: "+e);
		}
	}

	public void actualizar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "update producto set fechaVencimiento = ? where cod = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setDate(1, getFechaVenc());
			s.setInt(2, getCodProd());
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
	}

}
