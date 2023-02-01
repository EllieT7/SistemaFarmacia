package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;

import Clases.Conexion;
import Clases.Funciones;
import Clases.Producto;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JSpinner;
import java.awt.Dimension;
import javax.swing.SpinnerNumberModel;


@SuppressWarnings("serial")
public class ModificarProControlado extends JFrame {

	private JPanel contentPane;
	private JTextField nomb;
	private JTextField pa;
	private JSpinner su;
	private JTextField pu;
	private JSpinner sc;
	private JTextField pc;
	private SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
	private JDateChooser fecha;
	private Calendar cal = new GregorianCalendar();
	private Funciones funcion  = new Funciones();
	private JTextField contenido;
	private JTextField fieldConcentracion;
	private JTextField Origen;
	private JTextField DCI;
	private JComboBox<String> pres;
	private JComboBox<String> lab;
	
	@SuppressWarnings({ "removal", "deprecation"})
	public ModificarProControlado(int cod) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarProControlado.class.getResource("/icon/edit.png")));
		setResizable(false);
		Color color = new Color(118, 203, 222);
		setTitle("Editar producto controlado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese los siguientes datos:");
		lblNewLabel.setForeground(color);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(34, 23, 329, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre:");
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(44, 65, 148, 20);
		contentPane.add(lblNewLabel_1_1);
		
		nomb = new JTextField();
		nomb.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		nomb.setColumns(10);
		nomb.setBounds(202, 66, 224, 20);
		contentPane.add(nomb);
		
		JLabel lblNewLabel_1_2 = new JLabel("Presentaci\u00F3n:");
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(44, 96, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Laboratorio:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(44, 123, 148, 20);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Principio activo:");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(44, 154, 148, 20);
		contentPane.add(lblNewLabel_1_4);
		
		pa = new JTextField();
		pa.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		pa.setColumns(10);
		pa.setBounds(202, 153, 224, 20);
		contentPane.add(pa);
		
		JLabel lblNewLabel_1_5 = new JLabel("Fecha de vencimiento:");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_5.setBounds(44, 187, 148, 20);
		contentPane.add(lblNewLabel_1_5);
		
		pres = new JComboBox<String>();
		pres.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		pres.setBounds(202, 97, 224, 20);
		funcion.dato("presentacion","descripcion",pres,0);
		contentPane.add(pres);
		
		lab = new JComboBox<String>();
		lab.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lab.setBounds(202, 124, 224, 20);
		funcion.dato("laboratorio","laboratorio",lab,1);
		contentPane.add(lab);
		
		fecha = new JDateChooser();
		fecha.getCalendarButton().setBackground(Color.WHITE);
		fecha.getCalendarButton().setIcon(new ImageIcon(ModificarProControlado.class.getResource("/icon/calB.png")));
		fecha.setBounds(202, 184, 224, 23);
		fecha.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fecha.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fecha.getJCalendar().setWeekOfYearVisible(false);
		fecha.getJCalendar().setDecorationBackgroundColor(color);
		fecha.getJCalendar().setWeekdayForeground(Color.black);
		fecha.getJCalendar().setTodayButtonVisible(true);
		fecha.getJCalendar().setBackground(Color.WHITE);
		contentPane.add(fecha);
		fecha.setCalendar(cal);
		
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		panel.setBounds(62, 356, 408, 69);
		contentPane.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Stock unidades:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(lblNewLabel_3);
		
		su = new JSpinner();
		su.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		su.setPreferredSize(new Dimension(60, 25));
		su.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(su);
		
		JLabel lblNewLabel_2 = new JLabel("Precio unidad:");
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(lblNewLabel_2);
		
		pu = new JTextField();
		pu.setHorizontalAlignment(SwingConstants.RIGHT);
		pu.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(pu);
		pu.setColumns(7);
		
		JLabel lblNewLabel_4 = new JLabel("Bs.");
		lblNewLabel_4.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("         Stock cajas:");
		lblNewLabel_5.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_5);
		
		sc = new JSpinner();
		sc.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		sc.setPreferredSize(new Dimension(60, 25));
		sc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(sc);
		
		JLabel lblNewLabel_6 = new JLabel("     Precio caja:");
		lblNewLabel_6.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(lblNewLabel_6);
		
		pc = new JTextField();
		pc.setHorizontalAlignment(SwingConstants.RIGHT);
		pc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(pc);
		pc.setColumns(7);
		
		JLabel lblNewLabel_7 = new JLabel("Bs. ");
		lblNewLabel_7.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		panel.add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(ModificarProControlado.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(10, 451, 118, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Guardar");
		btnAgregarProducto.setIcon(new ImageIcon(ModificarProControlado.class.getResource("/icon/save.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String dci = DCI.getText();
					String concentracion = fieldConcentracion.getText();
					String origen = Origen.getText();
					String dato = pres.getSelectedItem().toString();
					String datoLab = lab.getSelectedItem().toString();	
					int pres = Integer.parseInt(funcion.getCodigo(dato, "presentacion",2,1));
					BigDecimal precioU = new BigDecimal(Double.parseDouble(pu.getText()));
					BigDecimal precioC =  new BigDecimal (Double.parseDouble(pc.getText()));
					Producto pro = new Producto(Integer.parseInt(su.getValue().toString()), Integer.parseInt(sc.getValue().toString()), nomb.getText(),
						pres, pa.getText(), precioU, precioC, formato.format(fecha.getDate()), Integer.parseInt(funcion.getCodigo(datoLab,"laboratorio",4,1))
						,Integer.parseInt(contenido.getText()),dci, concentracion, origen);
					pro.modificarControlado(cod);
					JOptionPane.showMessageDialog(null,"Modificado exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
					dispose();	
				}catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(null,"Los precios ingresados deben ser numéricos\n y deben llevar un punto decimal", "Advertencia", 2, null);
				}
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(361, 451, 148, 35);
		contentPane.add(btnAgregarProducto);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Unidades por caja:");
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setBounds(44, 219, 148, 20);
		contentPane.add(lblNewLabel_1_4_1);
		
		contenido = new JTextField();
		contenido.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		contenido.setColumns(10);
		contenido.setBounds(202, 218, 224, 20);
		contentPane.add(contenido);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Concentraci\u00F3n:");
		lblNewLabel_1_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_4_1_1.setBounds(44, 255, 148, 20);
		contentPane.add(lblNewLabel_1_4_1_1);
		
		fieldConcentracion = new JTextField();
		fieldConcentracion.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fieldConcentracion.setColumns(10);
		fieldConcentracion.setBounds(202, 254, 224, 20);
		contentPane.add(fieldConcentracion);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Origen:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(44, 286, 148, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		Origen = new JTextField();
		Origen.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		Origen.setColumns(10);
		Origen.setBounds(202, 287, 224, 20);
		contentPane.add(Origen);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("DCI:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(44, 317, 148, 20);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		DCI = new JTextField();
		DCI.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		DCI.setColumns(10);
		DCI.setBounds(202, 318, 224, 20);
		contentPane.add(DCI);
		
		getDatos(cod);
	}

	
	public void limpiar() {
		nomb.setText("");
		pc.setText("");
		fecha.setCalendar(cal);
		su.setValue(0);
		sc.setValue(0);
		pu.setText("");
		pc.setText("");
		pa.setText("");
		contenido.setText("");
	}
	public void getDatos(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto");
			while (rs.next()) {
				if(cod==rs.getInt(1)) {
					nomb.setText(rs.getString(2));
					pa.setText(rs.getString(3));
					pu.setText(rs.getBigDecimal(4)+"");
					pc.setText(rs.getBigDecimal(5)+"");
					fecha.setDate(rs.getDate(6));
					su.setValue(rs.getInt(7));
					sc.setValue(rs.getInt(8));
					pres.setSelectedItem(funcion.getDato("presentacion",2,rs.getInt(9)));
					lab.setSelectedItem(funcion.getDato("laboratorio",4,rs.getInt(10)));
					contenido.setText(rs.getInt(12)+"");
					DCI.setText(rs.getString(13));
					Origen.setText(rs.getString(14));
					fieldConcentracion.setText(rs.getString(15));
					break;
				}
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
