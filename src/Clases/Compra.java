package Clases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Compra {
	private String Factura;
	private String fechaCompra;
	private int codLab;
	private BigDecimal importe, desc;
	private int cod;
	private Funciones funcion = new Funciones();
	
	public Compra(String factura, String fechaCompra, int codLab, BigDecimal importe, BigDecimal desc) {
		super();
		Factura = factura;
		this.fechaCompra = fechaCompra;
		this.codLab = codLab;
		this.importe = importe;
		this.desc=desc;
	}

	
	public Compra(String factura, String fechaCompra, int codLab, BigDecimal importe, int cod, BigDecimal desc) {
		super();
		Factura = factura;
		this.fechaCompra = fechaCompra;
		this.codLab = codLab;
		this.importe = importe;
		this.cod = cod;
		this.desc=desc;
	}


	public BigDecimal getDesc() {
		return desc;
	}


	public Funciones getFuncion() {
		return funcion;
	}


	public int getCod() {
		return cod;
	}

	public String getFactura() {
		return Factura;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public int getCodLab() {
		return codLab;
	}

	public BigDecimal getImporte() {
		return importe;
	}


	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String nro = getFactura();
			Date fecha = Date.valueOf(getFechaCompra());
			int codLab = getCodLab();
			BigDecimal importe = getImporte();
			String query = "insert into compra" + "(nroFactura, fecha, laboratorio_codLab, importe,descuento, tiempo_actual) values " + "(?,?,?,?,?,current_timestamp())";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, nro);
			s.setDate(2, fecha);
			s.setInt(3, codLab);
			s.setBigDecimal(4, importe);
			s.setBigDecimal(5, getDesc());
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}

	public void eliminar(int cod){
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "delete from compra where codCompra = ?";
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
		Double importe = getImporte().doubleValue();
		Double descuento = getDesc().doubleValue();
		cad += "\nID: " + getCod() + "\nFecha: " + getFechaCompra() + "\nFactura: " + getFactura() + "\nLaboratorio: "
				+ funcion.getNombreLab(getCodLab(),4) +"\nVendedor: "+ funcion.getNombreLab(getCodLab(), 2)+"\nDetalle"
				+ "\n-------------------------------------------------------------------------------------------------------------------------------------"
				+ "\n" + "|" + center("Código", 7) + "|" + center("Producto", 30) + "|" + center("Presentación", 15) + "|"
				+ center("Vencimiento", 15)	+"|"+center("C. Unidad", 11) + "|" + center("C. Cajas", 11) + "|"  + center("P. Unidad", 11)+ "|"
				+center("P. Caja", 11)+"|"+center("Total", 12)+"|"				
				+ "\n-------------------------------------------------------------------------------------------------------------------------------------\n"
				+ productos()
				+ "-------------------------------------------------------------------------------------------------------------------------------------"
				+ "\n\t\t\t\t\t\t\t\t\t\t\t\t\tImporte total  |" + center(importe + " Bs.", 12)
				+ "|\n=====================================================================================================================================\n"
				+ "\t\t\t\t\t\t\t\t\t\t\t\t\tDescuento " +descuento+"% |" + center(((importe*(descuento/100)) + " Bs."), 12)
				+ "|\n=====================================================================================================================================\n"
				+ "\t\t\t\t\t\t\t\t\t\t\t\t\tImporte Final  |" + center(((importe-(importe*(descuento/100))) + " Bs."), 12)
				
				+ "|\n=====================================================================================================================================\n\n";

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
					"select a.cod, a.nombre, b.descripcion,c.fechaVenc, c.cantidadUni, c.cantidadCaja, c.preciounidad, c.preciocaja,"
					+ "((c.cantidadUni*c.preciounidad)+(c.cantidadCaja *c.precioCaja)) from producto a, presentacion b, compra_producto c\r\n"
					+ "where c.producto_cod = a.cod\r\n"
					+ "and a.presentacion_codpres = b.codPres\r\n"
					+ "and c.compra_codCompra = "+getCod()+";");
			if (!rs.wasNull()) {
				while (rs.next()) {
					cad += "|" + center(rs.getString(1), 7) + "|" + center(rs.getString(2), 30) + "|"
							+ center(rs.getString(3), 15) + "|" + center(rs.getString(4), 15) + "|"
							+ center(rs.getString(5), 11) + "|" + center(rs.getString(6), 11) +  "|"
							+ center(rs.getString(7), 11) + "|"+ center(rs.getString(8), 11) + "|"+ center(rs.getString(9), 12) + "|\n";
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
