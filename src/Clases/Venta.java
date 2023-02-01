package Clases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Venta {
	private String fechaVenta;
	private BigDecimal importe;
	private String codCli;
	private int cod;
	private String nombre;
	private Funciones funcion = new Funciones();
	
	public Venta(String fechaVenta, BigDecimal importe, String codCli, int cod, String nombre) {
		super();
		this.fechaVenta = fechaVenta;
		this.importe = importe;
		this.codCli = codCli;
		this.cod = cod;
		this.nombre = nombre;
	}

	public Venta(String fechaVenta, BigDecimal importe, String codCli, String nombre) {
		super();
		this.fechaVenta = fechaVenta;
		this.importe = importe;
		this.codCli = codCli;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Funciones getFuncion() {
		return funcion;
	}

	public int getCod() {
			return cod;
		}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public String getCodCli() {
		return codCli;
	}

	
	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			Date fecha = Date.valueOf(getFechaVenta());
			BigDecimal importe = getImporte();
			String cod = getCodCli();
			String query = "insert into venta" + "(fecha,importe ,cliente_ci, nombre,tiempo_actual) values " + "(?,?,?,?,current_timestamp())";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setDate(1, fecha);
			s.setString(3, cod);
			s.setBigDecimal(2, importe);
			s.setString(4, getNombre());
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public void eliminar(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "delete from venta where codVenta = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, cod);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}
	}

	@Override
	public String toString() {
		String cad = "";
		cad += "\nID: " + getCod() + "\nFecha: " + getFechaVenta() + "\nCI: " + getCodCli() + "\nNombre: "
				+ getNombre() + "\nDetalle"
				+ "\n----------------------------------------------------------------------------------------------------------------------------------------------------"
				+ "\n" + "|" + center("Cantidad", 10) + "|" + center("Tipo", 10) + "|" + center("Producto", 25) + "|"
				+ center("Presentación", 25) + "|" + center("Laboratorio", 40) + "|" + center("Precio", 15) + "|"
				+ center("Total", 15) + "|"
				+ "\n----------------------------------------------------------------------------------------------------------------------------------------------------\n"
				+ productos()
				+ "----------------------------------------------------------------------------------------------------------------------------------------------------"
				+ "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t    Importe total  |" + center(getImporte().doubleValue() + "", 15)
				+ "|\n====================================================================================================================================================\n\n";

		return cad;
	}

	public String productos() {
		String cad = "";
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery(
					"select a.cantidad, a.tipo, b.nombre, c.descripcion, d.laboratorio, a.precio, a.importetotal"
							+ " from venta_producto a, producto b, presentacion c, laboratorio d where a.venta_codventa="
							+ getCod() + " and a.producto_cod = b.cod "
							+ "and b.presentacion_codpres = c.codpres and b.laboratorio = d.codlab;");
			if (!rs.wasNull()) {
				while (rs.next()) {
					cad += "|" + center(rs.getString(1), 10) + "|" + center(funcion.getTipo(rs.getString(2)+""), 10) + "|"
							+ center(rs.getString(3), 25) + "|" + center(rs.getString(4), 25) + "|"
							+ center(rs.getString(5), 40) + "|" + center(rs.getString(6), 15) + "|"
							+ center(rs.getString(7), 15) + "|\n";
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return cad;
	}

	public static String center(String text, int longitud) {
		if (text == null) {
			text = "";
		}
		if (longitud <= text.length())
			return text.substring(0, longitud);
		int n = (longitud - text.length()) / 2;
		if (n == 0)
			return String.format("%-" + longitud + "s", text);
		int rest = longitud - n;
		return String.format("%" + n + "s%-" + rest + "s", "", text);
	}

	
}
