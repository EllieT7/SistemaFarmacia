package Grafico;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Clases.Conexion;
import Clases.Funciones;
import Excel.Inventario;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class JProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBusqueda;
	private DefaultTableModel model;
	private JTable table;
	private Funciones funcion = new Funciones(); 

	public JProducto(String user) {
		//Estableciendo el color principal de la interfaz
		Color color = new Color(0,163,224);
		
		//Configuraciones del JFrame
		setIconImage(Toolkit.getDefaultToolkit().getImage(JProducto.class.getResource("/icon/prescription.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Encabezados de la tabla
		Object[]columnas  = {"COD","STOCK UNIDAD","STOCK CAJAS","CONTENIDO","NOMBRE","PRESENTACIÓN","LABORATORIO","PRINCIPIO ACTIVO","PRECIO UNITARIO","PRECIO CAJA","FECHA DE VENCIMIENTO"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas); 
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Declaracion del scrollPane
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JPanel panelPrincipal = new JPanel();
		scrollPane.setViewportView(panelPrincipal);
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelIzq = new JPanel();
		panelPrincipal.add(panelIzq, BorderLayout.WEST);
		panelIzq.setBackground(color);
		panelIzq.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panelIzq.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		//Retroceder a Menu
		JLabel atras = new JLabel("");
		panel.add(atras);
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callMenu(user);
			}
		});
		atras.setIcon(new ImageIcon(JProducto.class.getResource("/icon/Metro Icon 13x.png")));
		
		JLabel lblNewLabel_11 = new JLabel("             ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel.add(lblNewLabel_11, BorderLayout.NORTH);
		JLabel lblNewLabel_12 = new JLabel("    ");
		panel.add(lblNewLabel_12, BorderLayout.WEST);
		JPanel panelCentral1 = new JPanel();
		panelCentral1.setBackground(Color.WHITE);
		panelPrincipal.add(panelCentral1, BorderLayout.CENTER);
		panelCentral1.setLayout(new BorderLayout(50, 0));
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panelCentral1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_3.add(panel_4, BorderLayout.WEST);
		JLabel lblNewLabel_13 = new JLabel("            ");
		panel_4.add(lblNewLabel_13);
		JLabel lblNewLabel_1 = new JLabel("");
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(JProducto.class.getResource("/icon/Helpbag.jpg")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel = new JLabel("Productos");
		panel_4.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_3.add(panel_2, BorderLayout.NORTH);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventario inv = new Inventario();
				try {
					inv.reporte();
				} catch (InstantiationException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnControlados = new JButton("Controlados");
		btnControlados.setIcon(new ImageIcon(JProducto.class.getResource("/icon/image (1).png")));
		btnControlados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callControlados(user);
			}
		});
		btnControlados.setPreferredSize(new Dimension(200, 32));
		btnControlados.setForeground(Color.WHITE);
		btnControlados.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnControlados.setBackground(new Color(0, 163, 224));
		panel_2.add(btnControlados);
		btnInventario.setPreferredSize(new Dimension(200, 32));
		btnInventario.setIcon(new ImageIcon(JProducto.class.getResource("/icon/exc.png")));
		btnInventario.setForeground(Color.WHITE);
		btnInventario.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnInventario.setBackground(new Color(0, 163, 224));
		panel_2.add(btnInventario);
				
		//Redireccionar a la interfaz de los productos eliminados
		JButton venc = new JButton("Recuperar productos eliminados");
		panel_2.add(venc);
		venc.setIcon(new ImageIcon(JProducto.class.getResource("/icon/basura.png")));
		venc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callRecuperarPro(user);
			}
		});
		venc.setForeground(Color.WHITE);
		venc.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		venc.setBackground(new Color(0, 163, 224));
		
		//Redireccionar a la interfaz de vencimientos cercanos
		JButton vencimiento = new JButton("Ver lista de vencimientos cercanos");
		panel_2.add(vencimiento);
		vencimiento.setIcon(new ImageIcon(JProducto.class.getResource("/icon/lista.png")));
		vencimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callVencPro(user);
			}
		});
		vencimiento.setForeground(Color.WHITE);
		vencimiento.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		vencimiento.setBackground(new Color(0, 163, 224));
		
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_3.add(panel_5, BorderLayout.SOUTH);
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignOnBaseline(true);
		flowLayout_1.setHgap(10);
		flowLayout_1.setVgap(10);
		JLabel lblNewLabel_2 = new JLabel("Buscar por");
		panel_5.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));
		
		//Configurando JComboBox de busqueda
		JComboBox <String> busqueda = new JComboBox <String>();
		busqueda.setPreferredSize(new Dimension(250, 30));
		panel_5.add(busqueda);
		busqueda.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		busqueda.setModel(new DefaultComboBoxModel<String>(new String[] {"Nombre", "Laboratorio", "Principio Activo"}));
		busqueda.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_4 = new JLabel("            ");
		panel_5.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Introduzca su b\u00FAsqueda:");
		panel_5.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));
		
		textFieldBusqueda = new JTextField();
		textFieldBusqueda.setPreferredSize(new Dimension(250, 30));
		panel_5.add(textFieldBusqueda);
		textFieldBusqueda.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		textFieldBusqueda.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("                         ");
		panel_5.add(lblNewLabel_6);
		
		JLabel img = new JLabel("");
		panel_5.add(img);
		img.setIcon(new ImageIcon(JProducto.class.getResource("/icon/prod.jpg")));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_6_1 = new JLabel("                         ");
		panel_5.add(lblNewLabel_6_1);
		
		JButton btnNewButton = new JButton("");
		panel_5.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(JProducto.class.getResource("/icon/refresh.png")));
		btnNewButton.setBackground(color);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatos();
			}
		});
		
		//Busqueda en tiempo real de productos
		textFieldBusqueda.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				repaint();
				int n = 0;
				if(busqueda.getSelectedItem().equals("Nombre")) {
					n=4;
				}else if(busqueda.getSelectedItem().equals("Laboratorio")) {
					n=6;
				}
				else {
					n=7;
				}
				funcion.filtro(n,table,textFieldBusqueda); //Ejecutar el filtrado
			}
		});
		
		JPanel Central = new JPanel();
		Central.setOpaque(false);
		panelCentral1.add(Central, BorderLayout.CENTER);
		Central.setLayout(new BorderLayout(0, 0));
		
		
		//Declaracion y configuracion de la tabla
		table = new JTable();
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		table.setModel(model);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.gray);
		table.setGridColor(Color.GRAY);
		table.setSelectionForeground(Color.white);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(color);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("IBM Plex Sans",Font.BOLD,13));
		//Configurando el tama�o de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(10).setPreferredWidth(150);
		
		table.setAutoCreateRowSorter(true);
		
		JScrollPane pane =  new JScrollPane(table);
		Central.add(pane, BorderLayout.CENTER);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_5 = new JLabel("                          ");
		Central.add(lblNewLabel_5, BorderLayout.WEST);
		JLabel lblNewLabel_5_1 = new JLabel("                          ");
		Central.add(lblNewLabel_5_1, BorderLayout.EAST);
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		Central.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_7.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new GridLayout(0, 3, 30, 30));
		JLabel lblNewLabel_9 = new JLabel("");
		panel_6.add(lblNewLabel_9);
		
		
		//Accion --> eliminar producto
		JButton eliminar = new JButton("Eliminar");
		panel_6.add(eliminar);
		eliminar.setIcon(new ImageIcon(JProducto.class.getResource("/icon/cancel.png")));
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0).toString());
					String pro = model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),4 ).toString();
					funcion.callEliminarPro(cod,pro);
				} catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el producto a eliminar", "Advertencia", 2, null);
				}
			}
		});
		eliminar.setBackground(new Color(250,116,112));
		eliminar.setForeground(new Color(255, 255, 255));
		eliminar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		
		
		//Accion --> editar producto
		JButton editar = new JButton("Editar");
		panel_6.add(editar);
		editar.setIcon(new ImageIcon(JProducto.class.getResource("/icon/edit.png")));
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()),0).toString());
					funcion.callModificarPro(cod);
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el producto a editar", "Advertencia", 2, null);
				}
			}
		});
		editar.setForeground(Color.WHITE);
		editar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		editar.setBackground(color);
		
		JLabel lblNewLabel_7 = new JLabel("      ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 42));
		panel_7.add(lblNewLabel_7, BorderLayout.CENTER);
		JLabel lblNewLabel_8 = new JLabel("                   ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 27));
		panel_7.add(lblNewLabel_8, BorderLayout.NORTH);
		JLabel lblNewLabel_10 = new JLabel("  ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 27));
		panel_7.add(lblNewLabel_10, BorderLayout.SOUTH);
		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_7.add(panel_8, BorderLayout.EAST);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		//Accion --> agregar producto
		JButton agregar = new JButton("Agregar producto");
		panel_8.add(agregar);
		agregar.setIcon(new ImageIcon(JProducto.class.getResource("/icon/agregar.png")));
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callAgregarPro();
				insertarDatos();
			}
		});
		agregar.setForeground(Color.WHITE);
		agregar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		agregar.setBackground(new Color(0, 163, 224));
		
		JLabel lblNewLabel_9_1 = new JLabel("");
		panel_8.add(lblNewLabel_9_1);
	
		
		//Inserta datos existentes en la base datos a la tabla
		insertarDatos();
		
		
		//Comprobar tipo de acceso de usuario
		if(!funcion.comprobar(user)) {
			venc.setEnabled(false);
			agregar.setEnabled(false);
			editar.setEnabled(false);
			eliminar.setEnabled(false);
		}
	}
	
	
	
	public void insertarDatos() {
		Object[] row = new Object[11];
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
			funcion.limpiarTabla(table);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto where activo = true");
			while (rs.next()) {	
				row[0] = rs.getInt(1);
				row[1] = rs.getInt(7);
				row[2] = rs.getInt(8);
				row[3]  = rs.getInt(12);
				row[4] = rs.getString(2);
				row[5] = funcion.getDato("presentacion",2,rs.getInt(9));
				row[6] = funcion.getDato("laboratorio",4,rs.getInt(10));
				row[7] = rs.getString(3);
				row[8] = rs.getBigDecimal(4);
				row[9] = rs.getBigDecimal(5);
				row[10] = formato.format(rs.getDate(6));
				model.addRow(row);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
