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
import java.sql.Date;
import java.sql.PreparedStatement;
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
import javax.swing.JSeparator;


@SuppressWarnings("serial")
public class AgregarProControlExistente extends JFrame {

	private JPanel contentPane;
	private JTextField lab;
	private JTextField pres;
	private SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
	private Calendar cal = new GregorianCalendar();
	private Funciones funcion  = new Funciones();
	private JTextField Concentracion;
	private JTextField Origen;
	private JTextField DCI;
	
	@SuppressWarnings({ "removal", "deprecation"})
	public AgregarProControlExistente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarProControlExistente.class.getResource("/icon/agregar.png")));
		setResizable(false);
		Color color = new Color(0,163,224);
		setTitle("Controlados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 428);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el producto:");
		lblNewLabel.setForeground(color);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(34, 23, 329, 31);
		contentPane.add(lblNewLabel);
		
		lab = new JTextField();
		lab.setEditable(false);
		lab.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lab.setColumns(10);
		lab.setBounds(202, 96, 224, 20);
		contentPane.add(lab);
		
		JLabel lblNewLabel_1_2 = new JLabel("Producto:");
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(44, 65, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Laboratorio:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(44, 96, 148, 20);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Presentaci\u00F3n:");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(44, 127, 148, 20);
		contentPane.add(lblNewLabel_1_4);
		
		pres = new JTextField();
		pres.setEditable(false);
		pres.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		pres.setColumns(10);
		pres.setBounds(202, 127, 224, 20);
		contentPane.add(pres);
		
		JComboBox<Producto> prod = new JComboBox<>();
		prod.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		prod.setBounds(202, 65, 224, 20);
		//funcion.dato("producto","nombre",prod,1);
		datosComboBox(prod);
		contentPane.add(prod);
		
		setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(AgregarProControlExistente.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(10, 346, 118, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Guardar");
		btnAgregarProducto.setIcon(new ImageIcon(AgregarProControlExistente.class.getResource("/icon/save.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro = (Producto) prod.getSelectedItem();
				try {
					Conexion con = new Conexion();
					Connection conexion = (Connection) con.Conectar();
					PreparedStatement s;
					String query = "update producto set controlado = true, dci = ?, origen = ?, concentracion = ? where cod = ?";
					s = (PreparedStatement) conexion.prepareStatement(query);
					s.setString(1, DCI.getText());
					s.setString(2, Origen.getText());
					s.setString(3, Concentracion.getText());
					s.setInt(4, pro.getCod());
					s.executeUpdate();
					conexion.close();
					JOptionPane.showMessageDialog(null,"Agregado exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
					dispose();
				} catch (Exception er) {
					System.err.println("Error: " + er);
				}
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(399, 346, 118, 35);
		contentPane.add(btnAgregarProducto);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Concentraci\u00F3n:");
		lblNewLabel_1_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_4_1_1.setBounds(44, 234, 148, 20);
		contentPane.add(lblNewLabel_1_4_1_1);
		
		Concentracion = new JTextField();
		Concentracion.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		Concentracion.setColumns(10);
		Concentracion.setBounds(202, 233, 224, 20);
		contentPane.add(Concentracion);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Origen:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(44, 265, 148, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		Origen = new JTextField();
		Origen.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		Origen.setColumns(10);
		Origen.setBounds(202, 266, 224, 20);
		contentPane.add(Origen);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("DCI:");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(44, 296, 148, 20);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		DCI = new JTextField();
		DCI.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		DCI.setColumns(10);
		DCI.setBounds(202, 297, 224, 20);
		contentPane.add(DCI);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese los siguientes datos:");
		lblNewLabel_1.setForeground(new Color(0, 163, 224));
		lblNewLabel_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblNewLabel_1.setBounds(34, 192, 329, 31);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 175, 522, 20);
		contentPane.add(separator);
		
		
		
		prod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro = (Producto) prod.getSelectedItem();
				pres.setText(funcion.getDato("presentacion",2,pro.getPres()));
				lab.setText(funcion.getDato("laboratorio",4,pro.getLab()));
			}
		});

	}
	public void datosComboBox(JComboBox<Producto> cb) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			String query = "select cod, nombre, laboratorio, presentacion_codpres from producto where controlado = false order by nombre;";
			ResultSet rs = (ResultSet) s.executeQuery(query);
			while (rs.next()) {
				int cod = rs.getInt(1);
				String nombre = rs.getString(2);
				int codPres = rs.getInt(4);
				int codLab = rs.getInt(3);
				Producto dato = new Producto(cod, nombre, codPres, codLab);			
				cb.addItem(dato);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
	

}
