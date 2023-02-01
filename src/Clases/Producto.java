package Clases;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Producto {
	private int cod;
	private int stockU;
	private int stockC;
	private String nombre;
	private int pres;
	private String pActivo;
	private BigDecimal precioU;
	private BigDecimal precioC;
	private String fechaV;
	private int lab;
	private int contenido;
	private String DCI;
	private String concentracion;
	private String origen;
	
	
	@Override
	public String toString() {
		return nombre;
	}

	 
	public Producto(int stockU, int stockC, String nombre, int pres, String pActivo, BigDecimal precioU,
			BigDecimal precioC, String fechaV, int lab, int contenido) {
		super();
		this.stockU = stockU;
		this.stockC = stockC;
		this.nombre = nombre;
		this.pres = pres;
		this.pActivo = pActivo;
		this.precioU = precioU;
		this.precioC = precioC;
		this.fechaV = fechaV;
		this.lab = lab;
		this.contenido = contenido;
	}

	public Producto(int cod,int stockU, int stockC, String nombre, int pres, String pActivo,
			BigDecimal precioU, BigDecimal precioC, String fechaV, int lab, int contenido) {
		super();
		this.cod = cod;
		this.stockU = stockU;
		this.stockC = stockC;
		this.nombre = nombre;
		this.pres = pres;
		this.pActivo = pActivo;
		this.precioU = precioU;
		this.precioC = precioC;
		this.fechaV = fechaV;
		this.lab = lab;
		this.contenido = contenido;
	}
	
	
	public Producto(int cod, String nombre, int pres, int lab) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.pres = pres;
		this.lab = lab;
	}
	
	
	
	public Producto(int stockU, int stockC, String nombre, int pres, String pActivo, BigDecimal precioU,
			BigDecimal precioC, String fechaV, int lab, int contenido, String dCI, String concentracion,
			String origen) {
		super();
		this.stockU = stockU;
		this.stockC = stockC;
		this.nombre = nombre;
		this.pres = pres;
		this.pActivo = pActivo;
		this.precioU = precioU;
		this.precioC = precioC;
		this.fechaV = fechaV;
		this.lab = lab;
		this.contenido = contenido;
		DCI = dCI;
		this.concentracion = concentracion;
		this.origen = origen;
	}
	public boolean isControlado() {
		boolean controlado = false;
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			String query = "select controlado from producto where cod = "+getCod();
			ResultSet rs = (ResultSet) s.executeQuery(query);
			while (rs.next()) {
				controlado = rs.getBoolean(1);
			}
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return controlado;	
	}
	
	public int getCod() {
		return cod;
	}
	public int getLab() {
		return lab;
	}
	public int getContenido() {
		return contenido;
	}
	public String getFechaV() {
		return fechaV;
	}
	public int getStockU() {
		return stockU;
	}
	public int getStockC() {
		return stockC;
	}
	public String getNombre() {
		return nombre;
	}
	public int getPres() {
		return pres;
	}
	public String getpActivo() {
		return pActivo;
	}
	public BigDecimal getPrecioU() {
		return precioU;
	}
	public BigDecimal getPrecioC() {
		return precioC;
	}
	public String getFecha() {
		return fechaV;
	}
	

	public String getDCI() {
		return DCI;
	}

	public String getConcentracion() {
		return concentracion;
	}

	public String getOrigen() {
		return origen;
	}

	public void agregar() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			Date fecha = Date.valueOf(getFecha());
			String query = "insert into producto" + "(nombre, principioActivo, precioUnitario, precioCaja, fechaVencimiento, stockUnidad, stockCaja, presentacion_codPres, laboratorio, activo, contenido) values " + "(?,?,?,?,?,?,?,?,?,?,?)";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, getNombre());
			s.setString(2, getpActivo());
			s.setBigDecimal(3, getPrecioU());
			s.setBigDecimal(4, getPrecioC());
			s.setDate(5, fecha);
			s.setInt(6, getStockU());
			s.setInt(7, getStockC());
			s.setInt(8, getPres());
			s.setInt(9,getLab());
			s.setBoolean(10, true);
			s.setInt(11, getContenido());
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
			PreparedStatement s;
			String query = "update producto set nombre = ?, principioActivo = ?, precioUnitario = ?, precioCaja = ?, fechaVencimiento = ?, stockUnidad = ?, stockCaja = ?, presentacion_codPres = ?, laboratorio = ?, contenido = ? where cod = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, getNombre());
			s.setString(2, getpActivo());
			s.setBigDecimal(3, getPrecioU());
			s.setBigDecimal(4, getPrecioC());
			s.setDate(5, fecha);
			s.setInt(6, getStockU());
			s.setInt(7, getStockC());
			s.setInt(8, getPres());
			s.setInt(9, getLab());
			s.setInt(10, getContenido());
			s.setInt(11, cod);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}
	
	public void agregarControlado() {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			Date fecha = Date.valueOf(getFecha());
			String query = "insert into producto" + "(nombre, principioActivo, precioUnitario, precioCaja, fechaVencimiento, stockUnidad, stockCaja, "
					+ "presentacion_codPres, laboratorio, activo, contenido, dci, origen, concentracion, controlado) values " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, getNombre());
			s.setString(2, getpActivo());
			s.setBigDecimal(3, getPrecioU());
			s.setBigDecimal(4, getPrecioC());
			s.setDate(5, fecha);
			s.setInt(6, getStockU());
			s.setInt(7, getStockC());
			s.setInt(8, getPres());
			s.setInt(9,getLab());
			s.setBoolean(10, true);
			s.setInt(11, getContenido());
			s.setString(12, getDCI());
			s.setString(13, getOrigen());
			s.setString(14, getConcentracion());
			s.setBoolean(15, true);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
	
	public void modificarControlado(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			Date fecha = Date.valueOf(getFecha());
			PreparedStatement s;
			String query = "update producto set nombre = ?, principioActivo = ?, precioUnitario = ?, precioCaja = ?, fechaVencimiento = ?, stockUnidad = ?, stockCaja = ?, "
					+ "presentacion_codPres = ?, laboratorio = ?, contenido = ?, dci = ?, origen = ?, concentracion = ?, controlado = true where cod = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setString(1, getNombre());
			s.setString(2, getpActivo());
			s.setBigDecimal(3, getPrecioU());
			s.setBigDecimal(4, getPrecioC());
			s.setDate(5, fecha);
			s.setInt(6, getStockU());
			s.setInt(7, getStockC());
			s.setInt(8, getPres());
			s.setInt(9, getLab());
			s.setInt(10, getContenido());
			s.setString(11,getDCI());
			s.setString(12,getOrigen());
			s.setString(13,getConcentracion());
			s.setInt(14, cod);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}
	
	
	public static void recuperar(int cod){
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "update producto set activo = true where cod = "+cod;
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}
	}
	 	
	public static void eliminar(int cod){
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "update producto set activo = false where cod = "+cod;
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.executeUpdate();
			conexion.close();
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}
	}
}
