package Clases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class VentaProd {
	private BigDecimal importe;
	private int codProd;
	private int codVenta;
	private int codAdmin;
	private int cant;
	private String tipo;
	private BigDecimal precio;
	
	public VentaProd(BigDecimal importe, int codProd, int codVenta, int codAdmin, int cant, String tipo, BigDecimal precio) {
		super();
		this.importe = importe;
		this.codProd = codProd;
		this.codVenta = codVenta;
		this.codAdmin = codAdmin;
		this.cant = cant;
		this.tipo = tipo;
		this.precio = precio;
	}
	
	public String getTipo() {
		return tipo;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public int getCodProd() {
		return codProd;
	}

	public int getCodVenta() {
		return codVenta;
	}

	public int getCodAdmin() {
		return codAdmin;
	}

	public int getCant() {
		return cant;
	}

	public void agregarControlado(ArrayList<String> infoExtra) {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			BigDecimal importe = getImporte();
			int codProd = getCodProd();
			int codVenta = getCodVenta();
			int codAdmin = getCodAdmin();
			int cant = getCant();
			String tipo = getTipo();
			BigDecimal precio = getPrecio();
			System.out.println(infoExtra);
			
			String query = "insert into venta_producto" + "(importeTotal ,producto_cod, venta_codVenta, administrador_codAdmin, cantidad, tipo, precio, medico, receta, observaciones,stockUniActual, stockCajaActual) values " + "(?,?,?,?,?,?,?,?,?,?,?,?);";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setBigDecimal(1, importe);
			s.setInt(2, codProd);
			s.setInt(3, codVenta);
			s.setInt(4, codAdmin);
			s.setInt(5, cant);
			s.setString(6, tipo);
			s.setBigDecimal(7, precio);
			s.setString(8,infoExtra.get(0));
			s.setString(9,infoExtra.get(1));
			s.setString(10, infoExtra.get(2));
			s.setInt(11, getStock(codProd,"U"));
			s.setInt(12, getStock(codProd,"C"));
			s.executeUpdate();
			conexion.close();
			System.out.println("controlado");
			
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
	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			BigDecimal importe = getImporte();
			int codProd = getCodProd();
			int codVenta = getCodVenta();
			int codAdmin = getCodAdmin();
			int cant = getCant();
			String tipo = getTipo();
			BigDecimal precio = getPrecio();
			String query = "insert into venta_producto" + "(importeTotal ,producto_cod, venta_codVenta, administrador_codAdmin, cantidad, tipo, precio) values " + "(?,?,?,?,?,?,?);";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setBigDecimal(1, importe);
			s.setInt(2, codProd);
			s.setInt(3, codVenta);
			s.setInt(4, codAdmin);
			s.setInt(5, cant);
			s.setString(6, tipo);
			s.setBigDecimal(7, precio);
			s.executeUpdate();
			conexion.close();
	
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
}
