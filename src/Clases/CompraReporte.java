package Clases;
public class CompraReporte {
	private int cod;
	private String producto;
	private String presentacion;
	private int cantU;
	private int cantC;
	private String venc;
	public CompraReporte(int cod, String producto, String presentacion, int cantU, int cantC, String venc) {
		super();
		this.cod = cod;
		this.producto = producto;
		this.presentacion = presentacion;
		this.cantU = cantU;
		this.cantC = cantC;
		this.venc = venc;
	}
	public int getCod() {
		return cod;
	}
	public String getProducto() {
		return producto;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public int getCantU() {
		return cantU;
	}
	public int getCantC() {
		return cantC;
	}
	public String getVenc() {
		return venc;
	}	
}
