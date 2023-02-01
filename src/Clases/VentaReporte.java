package Clases;

import java.math.BigDecimal;

public class VentaReporte {
	private int cantidad;
	private String tipo;
	private String producto;
	private String pres;
	private String lab;
	private BigDecimal pu;
	private BigDecimal total;
	public VentaReporte(int cantidad, String tipo, String producto, String pres, String lab, BigDecimal pu,
			BigDecimal total) {
		super();
		this.cantidad = cantidad;
		this.tipo = tipo;
		this.producto = producto;
		this.pres = pres;
		this.lab = lab;
		this.pu = pu;
		this.total = total;
	}
	public int getCantidad() {
		return cantidad;
	}
	
	public String getTipo() {
		return tipo;
	}

	public String getProducto() {
		return producto;
	}
	
	public String getPres() {
		return pres;
	}
	
	public String getLab() {
		return lab;
	}

	public BigDecimal getPu() {
		return pu;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
}
