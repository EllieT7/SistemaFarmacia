package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafica {
	private Funciones funcion =  new Funciones();
	Date date1 = new Date();
	@SuppressWarnings("deprecation")
	private String date = funcion.getFecha(date1.getMonth(), date1.getYear(), 0);
	@SuppressWarnings("deprecation")
	private String dateMax = funcion.getFecha(date1.getMonth(), date1.getYear(), 30);
	
	public void grafico(String tipo) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		getDatos(dataset,tipo);
		JFreeChart chart = ChartFactory.createBarChart(
                "Ventas Mensuales - "+funcion.getTipo(tipo)+"\n "+date+" a "+dateMax,
                "Productos", 
                funcion.getTipo(tipo), 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                false, 
                false
        );
		ChartFrame frame = new ChartFrame("Gráfica ventas mensuales", chart);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	public void getDatos(DefaultCategoryDataset data, String tipo) {
		
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select sum(a.cantidad),b.nombre from venta_producto a, producto b, venta c where a.producto_cod = b.cod "
					+ "and a.venta_codVenta = c.codVenta and a.tipo = "+"'"+tipo+"' "+"and c.fecha between "+"'"+date+"' and '"+dateMax+"' group by b.nombre;");
			int i=1;
			while (rs.next()) {
				data.setValue(Double.parseDouble(rs.getInt(1)+""), rs.getString(2)+" * "+rs.getInt(1), rs.getString(2));
				i++;
				if(i>8) {
					break;
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);

		}
		
	}
	
}
