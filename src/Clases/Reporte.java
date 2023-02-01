package Clases;
import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Grafico.VentaR;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
public class Reporte {
	public void reporteVenta(int cod, String fecha, String ci, String nombre, BigDecimal importe, List<VentaReporte> list) {
		try {
			Date date = Date.valueOf(fecha);
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
			fecha = formato.format(date);
			String filePath ="./src/Report/reporteVenta.jrxml";
			Map <String, Object> parameters =  new HashMap<String, Object>();

			parameters.put("cod", cod);
			parameters.put("fecha", fecha);
			parameters.put("ci", ci);
			parameters.put("nombre", nombre);
			parameters.put("importe", importe.doubleValue());
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			JasperPrint print = JasperFillManager.fillReport(report, parameters,dataSource);
			comprobar();
			String nombre1 = "Venta_"+cod+".pdf";
			String ruta = "C:\\Reportes\\"+nombre1;
			JasperExportManager.exportReportToPdfFile(print,ruta);
			if(new File(ruta).exists()){
				JOptionPane.showMessageDialog(null, nombre1 + " creado correctamente!","",-1,new ImageIcon(VentaR.class.getResource("/icon/file (2).png")));
				abrir(ruta);
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al generar el PDF");	
			System.out.println("Error "+e);
		}
	}

	public void reporteCompra(String factura, String lab, String vend, String fecha, BigDecimal importe, List<CompraReporte> list1 ) {
		try {
			Date date = Date.valueOf(fecha);
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
			fecha = formato.format(date);
			String filePath = "./src/Report/reporteCompra.jrxml";
			Map <String, Object> parameters =  new HashMap<String, Object>();
			parameters.put("cod", factura);
			parameters.put("lab", lab);
			parameters.put("vend", vend);
			parameters.put("fecha", fecha);
			parameters.put("importe", importe.doubleValue());
			
			JRBeanCollectionDataSource dataSource1 = new JRBeanCollectionDataSource(list1);
			
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			JasperPrint print = JasperFillManager.fillReport(report, parameters,dataSource1);
			
			String nombre = "Compra_"+factura+".pdf";
			String ruta = "C:\\Reportes\\"+nombre;
			comprobar();
			JasperExportManager.exportReportToPdfFile(print,ruta);
			if(new File(ruta).exists()){
				JOptionPane.showMessageDialog(null, nombre + " creado correctamente!","",-1,new ImageIcon(VentaR.class.getResource("/icon/file (2).png")));
				abrir(ruta);
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al generar el PDF");		
			System.out.println("Error "+e);
		}
	}
	
	public void comprobar() {
		File carpeta = new File("C:\\Reportes");
		if(!carpeta.exists()) {
			carpeta.mkdir();
		}
	}
	public void abrir(String nombre) {
		try {
			File path = new File(nombre);
			Desktop.getDesktop().open(path);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo :c "+e);
		}
	}
}
