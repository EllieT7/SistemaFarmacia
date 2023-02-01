package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Grafico.*;

public class Funciones {
	
	public void filtro(int n, JTable table, JTextField textField) {
		TableRowSorter<TableModel> trsfiltro = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(trsfiltro);
		trsfiltro.setRowFilter(RowFilter.regexFilter("^(?i)"+textField.getText(), n));
	}
	public void callProducto(String user) {
		JProducto obj = new JProducto(user);
		obj.setLocationRelativeTo(null);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setVisible(true);
	}
	public void callRecuperarPro(String user) {
		RecuperarPro obj1 = new RecuperarPro(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callMenu(String user) {
		Menu obj1 = new Menu(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callVencPro(String user) {
		Vencimiento obj1 = new Vencimiento(user);
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callModificarPro(int cod) {
		ModificarPro obj1 = new ModificarPro(cod); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callControlados(String user) {
		JControlados obj1 = new JControlados(user); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callInfoControlados(int cod, DefaultTableModel model, Object datos[],Double montoTotal, JTextField campoTotal ) {
		AgregarInfoControlado obj1 = new AgregarInfoControlado(cod, model, datos, montoTotal, campoTotal);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callOpcionControlados() {
		OpcionesControlados obj1 =  new OpcionesControlados();
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callGenerarControlados() {
		GenerarReporteControlados obj1 =  new GenerarReporteControlados();
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callOpcionNuevoControlados() {
		AgregarProControlado obj1 =  new AgregarProControlado();
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callOpcionExistenteControlados() {
		AgregarProControlExistente obj1 =  new AgregarProControlExistente();
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callEliminarPro(int cod, String pro) {
		EliminarPro obj1 = new EliminarPro(cod, pro);
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callEliminarProControlado(int cod, String pro) {
		EliminarProControlado obj1 = new EliminarProControlado(cod, pro);
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callAgregarPro() {
		AgregarPro obj1 = new AgregarPro(); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callLogin() {
		Login obj1 = new Login(); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callCliente(String user) {
		JCliente obj1 = new JCliente(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callLab(String user) {
		JLab obj1 = new JLab(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callCompra(String user) {
		JCompra obj1 = new JCompra(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callVenta(String user) {
		JVenta obj1 = new JVenta(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callPlan(String user) {
		JPlan obj1 = new JPlan(user);
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callEliminarCli(String nombre ,String cli) {
		EliminarCli obj1 = new EliminarCli(nombre, cli);
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callAgregarCli() {
		AgregarCli obj1 = new AgregarCli(); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callModificarCli(String cod) {
		ModificarCli obj1 = new ModificarCli(cod); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	
	public void callModificarProControlado(int cod) {
		ModificarProControlado obj1 = new ModificarProControlado(cod); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	
	public void callRecuperarLab(String user) {
		RecuperarLab obj1 = new RecuperarLab(user); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callAgregarLab() {
		AgregarLab obj1 = new AgregarLab(); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callEliminarLab(int cod, String lab) {
		EliminarLab obj1 = new EliminarLab(cod, lab); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}

	public void callModificarLab(int cod) {
		ModificarLab obj1 = new ModificarLab(cod); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callRegistroVenta(String user) {
		VentaR obj1 = new VentaR(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}

	public void callRegistroCompra(String user) {
		CompraR obj1 = new CompraR(user); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callPlanTotal(String user) {
		PlanTotal obj1 = new PlanTotal(user); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callAgregarPlan(String user) {
		AgregarPlan obj1 = new AgregarPlan(user); 
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callEliminarPlan() {
		EliminarPlan obj1 = new EliminarPlan(); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callModificarPlan(String user) {
		ModificarPlan obj1 = new ModificarPlan(user);
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callFechaExcel() {
		FechaExcel obj1 = new FechaExcel();
		obj1.setLocationRelativeTo(null);
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callModificarPlan(int cod, String user) {
		Modif obj1 = new Modif(cod, user); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callAgregarUsuario() {
		AgregarUsuario obj1 = new AgregarUsuario(); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callModificarUsuario(int cod) {
		ModificarUsuario obj1 = new ModificarUsuario(cod); 
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	public void callAdminUsuarios() {
		Usuarios obj1 = new Usuarios();
		obj1.setLocationRelativeTo(null); 
		obj1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		obj1.setVisible(true);
	}
	
	//Retorna el nombre de un mes dependiendo de un entero
	public String getMes(int i) {
		String mes = "";
		switch (i) {
		case 1:
			mes = "Enero";
			break;
		case 2:
			mes = "Febrero";
			break;
		case 3:
			mes = "Marzo";
			break;
		case 4:
			mes = "Abril";
			break;
		case 5:
			mes = "Mayo";
			break;
		case 6:
			mes = "Junio";
			break;
		case 7:
			mes = "Julio";
			break;
		case 8:
			mes = "Agosto";
			break;
		case 9:
			mes = "Septiembre";
			break;
		case 10:
			mes = "Octubre";
			break;
		case 11:
			mes = "Noviembre";
			break;
		case 12:
			mes = "Diciembre";
			break;
		}
		return mes;
	}
	
	//Devuelve la fecha en formato YYYY-MM-dd
	@SuppressWarnings("deprecation")
	public String getFecha(int mes, int year, int n) {
		String f = "";
		Date fecha = new Date(year, mes, 1);
		String date = "yyyy-MM-dd";
		SimpleDateFormat obj = new SimpleDateFormat(date);
		fecha.setDate(fecha.getDate() + n);
		f = obj.format(fecha);
		return f;
	}

	public String getCodigo(String dato, String tabla, int c, int cc ) {
		String n = "";
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from "+tabla);
			while (rs.next()) {
				if(rs.getString(c).equals(dato)) {
					n = rs.getString(cc);
					break;
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return n;
	}
	
	//Ingresa datos en un JComboBox
	public void datoProducto(JComboBox<Producto> cb) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			String query = "select * from producto where activo = true order by nombre";
			ResultSet rs = (ResultSet) s.executeQuery(query);
			while (rs.next()) {
				Producto dato = new Producto(rs.getInt(1), rs.getInt(7), rs.getInt(8), rs.getString(2), rs.getInt(9),
						rs.getString(3), rs.getBigDecimal(4), rs.getBigDecimal(5), rs.getDate(6)+"", rs.getInt(10), rs.getInt(12));
				cb.addItem(dato);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	public void datoLab(JComboBox<Laboratorio> cb) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			String query = "select * from laboratorio where activo = true order by laboratorio";
			ResultSet rs = (ResultSet) s.executeQuery(query);
			while (rs.next()) {
				Laboratorio dato = new Laboratorio(rs.getInt(1)+"",rs.getString(2),rs.getString(3),rs.getString(4));
				cb.addItem(dato);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
	//Ingresa datos en un JComboBox
		public void dato(String tabla, String columna, JComboBox<String> cb, int cod) {
			try {
				Conexion con = new Conexion();
				Connection conexion;
				conexion = (Connection) con.Conectar();
				Statement s = (Statement) conexion.createStatement();
				String query = "select distinct "+tabla+"."+columna+" from "+tabla;
				if(cod==1) {
					query += " where activo = true";
				}
				ResultSet rs = (ResultSet) s.executeQuery(query);
				while (rs.next()) {
					String dato = rs.getString(1);
					cb.addItem(dato);
				}
				conexion.close();
			} catch (InstantiationException | SQLException | IllegalAccessException e) {
				JOptionPane.showMessageDialog(null, "Error: " + e);
			}
		}
	
	//Obtiene el ultimo codigo de la tabla
	public int getLastCod(String tabla) {
		int c = 0;
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from " + tabla);
			while (rs.next()) {
				c = rs.getInt(1);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return c;
	}
	
	//Elimina los registros de un JTable
	public void limpiarTabla(JTable tabla){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
	
	//Retorna una cadena de cualquier tabla
	public String getDato(String tabla, int c, int cod ) {
		String cad="";
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from "+tabla);
			while (rs.next()) {
				if(rs.getInt(1)==cod) {
					cad = rs.getString(c);
					break;
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return cad;
	}
	
	//Regresa una cadena de la tabla laboratorio, dependiendo de columna y codigo
	public String getNombreLab(int cod, int column) {
		String nombre = "";
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from laboratorio");
			while (rs.next()) {
				if (rs.getInt(1)==cod) {
					nombre = rs.getString(column);
					break;
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return nombre;
	}
	
	//Retorna cadena dependiendo del tipo de producto
	public String getTipo (String t) {
		String cad="";
		if(t.equals("U")) cad = "Unidades";
		else cad = "Cajas";
		return cad;
	}
	
	//Comprueba tipo de usuario
	public boolean comprobar(String user) {
		boolean flag = false;
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from administrador;");
			while (rs.next()) {
				if(rs.getString(2).equals(user)) {
					if(rs.getBoolean(4)==true) {
						flag = true;
					}
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return flag;
	}
}
