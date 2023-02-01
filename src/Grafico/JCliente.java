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
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class JCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private DefaultTableModel model;
	private JTable table;
	private Funciones funcion = new Funciones();

	public JCliente(String user) {
		// Establecer el color principal de la interfaz
		Color color = new Color(118, 203, 222);

		// Configuraciones del JFrame
		setIconImage(Toolkit.getDefaultToolkit().getImage(JCliente.class.getResource("/icon/prescription.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Estableciendo encabezados de la tabla
		Object[] columnas = { "CI", "NOMBRE", "TELÉFONO" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(Principal);
		Principal.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		Principal.add(panel, BorderLayout.WEST);
		panel.setBackground(color);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		// Retroceder a menu
		JLabel atras = new JLabel("");
		panel_1.add(atras);
		atras.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callMenu(user);
			}
		});
		atras.setIcon(new ImageIcon(JCliente.class.getResource("/icon/atras4.png")));

		// Paneles y JLabels
		JLabel lblNewLabel_11 = new JLabel("             ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_1.add(lblNewLabel_11, BorderLayout.NORTH);
		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_1.add(lblNewLabel_12, BorderLayout.WEST);
		JPanel Central = new JPanel();
		Central.setOpaque(false);
		Principal.add(Central, BorderLayout.CENTER);
		Central.setLayout(new BorderLayout(0, 0));
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		Central.add(panel_2, BorderLayout.NORTH);
		JLabel lblNewLabel_15 = new JLabel("                 ");
		panel_2.add(lblNewLabel_15);
		JPanel Sup = new JPanel();
		Sup.setOpaque(false);
		panel_2.add(Sup);
		Sup.setLayout(new BorderLayout(0, 0));
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		Sup.add(panel_5, BorderLayout.WEST);
		JLabel lblNewLabel_1 = new JLabel("");
		panel_5.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(JCliente.class.getResource("/icon/Cli.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel = new JLabel("Clientes");
		panel_5.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setHgap(25);
		Sup.add(panel_6, BorderLayout.SOUTH);
		JLabel lblNewLabel_14 = new JLabel("                     ");
		panel_6.add(lblNewLabel_14);
		JLabel lblNewLabel_2 = new JLabel("Buscar por");
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		// ComboBox para el tipo de busqueda
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setPreferredSize(new Dimension(200, 30));
		panel_6.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "CI", "Nombre", "Tel\u00E9fono" }));
		comboBox.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		comboBox.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel_3 = new JLabel("Introduzca su b\u00FAsqueda:");
		panel_6.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		// Key Listener para la busqueda en tiempo real
		textField = new JTextField();
		panel_6.add(textField);
		textField.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				repaint();
				int n = 0;
				if (comboBox.getSelectedItem().equals("Nombre")) {
					n = 1;
				} else if (comboBox.getSelectedItem().equals("Tel\u00E9fono")) {
					n = 2;
				}
				funcion.filtro(n, table, textField);// Filtrado de la tabla
			}
		});
		textField.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("                                    ");
		panel_6.add(lblNewLabel_16);

		// Configurando el boton de actualizar datos
		JButton actualizar = new JButton("");
		panel_6.add(actualizar);
		actualizar.setBackground(color);
		actualizar.setIcon(new ImageIcon(JCliente.class.getResource("/icon/refresh.png")));

		JLabel lblNewLabel_13 = new JLabel(" ");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Sup.add(lblNewLabel_13, BorderLayout.NORTH);
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatos();
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		Central.add(panel_3, BorderLayout.CENTER);

		// Configuracion e inicializacion de la tabla principal
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
		table.getTableHeader().setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		// Configurando el tamaño de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		panel_3.setLayout(new BorderLayout(0, 0));
		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);
		panel_3.add(pane, BorderLayout.CENTER);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_3.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new BorderLayout(0, 0));
		JPanel botones = new JPanel();
		botones.setOpaque(false);
		panel_4.add(botones, BorderLayout.CENTER);
		botones.setLayout(new GridLayout(3, 1, 0, 25));
		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		botones.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		// Accion --> agregar cliente
		JButton agregar = new JButton("Agregar cliente");
		panel_9.add(agregar);
		agregar.setIcon(new ImageIcon(JCliente.class.getResource("/icon/agregar.png")));
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callAgregarCli();
			}
		});
		agregar.setForeground(Color.WHITE);
		agregar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		agregar.setBackground(color);

		JLabel lblNewLabel_9 = new JLabel("         ");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_9.add(lblNewLabel_9, BorderLayout.WEST);
		JLabel lblNewLabel_10 = new JLabel("         ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_9.add(lblNewLabel_10, BorderLayout.EAST);
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		botones.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_4 = new JLabel("            ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_7.add(lblNewLabel_4, BorderLayout.WEST);

		// Accion --> editar cliente
		JButton editar = new JButton("Editar");
		panel_7.add(editar);
		editar.setIcon(new ImageIcon(JCliente.class.getResource("/icon/edit.png")));
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cod = (model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
					funcion.callModificarCli(cod);
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el cliente a editar", "Advertencia", 2, null);
				}
			}
		});
		editar.setForeground(Color.WHITE);
		editar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		editar.setBackground(color);

		JLabel lblNewLabel_5 = new JLabel("            ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_7.add(lblNewLabel_5, BorderLayout.EAST);
		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		botones.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_6 = new JLabel("            ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_8.add(lblNewLabel_6, BorderLayout.WEST);

		// Accion --> eliminar cliente
		JButton eliminar = new JButton("Eliminar");
		panel_8.add(eliminar);
		eliminar.setIcon(new ImageIcon(JCliente.class.getResource("/icon/cancel.png")));
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cod = (model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
					String nomb = model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 1).toString();
					funcion.callEliminarCli(cod, nomb);
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el cliente a eliminar", "Advertencia", 2, null);
				}
			}
		});
		eliminar.setBackground(new Color(250, 116, 112));
		eliminar.setForeground(new Color(255, 255, 255));
		eliminar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));

		JLabel lblNewLabel_7 = new JLabel("            ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_8.add(lblNewLabel_7, BorderLayout.EAST);
		JLabel img = new JLabel("");
		panel_4.add(img, BorderLayout.SOUTH);
		img.setIcon(new ImageIcon(JCliente.class.getResource("/icon/cliente1.jpg")));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel_8 = new JLabel(" ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 99));
		panel_4.add(lblNewLabel_8, BorderLayout.NORTH);
		JLabel lblNewLabel_17 = new JLabel("                                 ");
		panel_3.add(lblNewLabel_17, BorderLayout.WEST);
		JLabel lblNewLabel_18 = new JLabel("   ");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel_3.add(lblNewLabel_18, BorderLayout.SOUTH);
		JLabel lblNewLabel_19 = new JLabel(" ");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 33));
		panel_3.add(lblNewLabel_19, BorderLayout.NORTH);

		// Ingresar datos existentes a la base de datos
		insertarDatos();

		// Comprobar el tipo de acceso del usuario
		if (!funcion.comprobar(user)) {
			agregar.setEnabled(false);
			editar.setEnabled(false);
			eliminar.setEnabled(false);
		}

	}

	public void insertarDatos() {
		Object[] row = new Object[3];
		try {
			funcion.limpiarTabla(table);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from cliente");
			while (rs.next()) {
				row[0] = rs.getString(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				model.addRow(row);
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
