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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class JLab extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private DefaultTableModel model;
	private JTable table;
	private Funciones funcion = new Funciones();

	public JLab(String user) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(JLab.class.getResource("/icon/prescription.png")));
		Color color = new Color(41, 173, 158);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Object[] columnas = { "CÓDIGO", "VENDEDOR", "TELÉFONO", "LABORATORIO" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JPanel Principal = new JPanel();
		scrollPane.setViewportView(Principal);
		Principal.setBackground(new Color(255, 255, 255));
		Principal.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		Principal.add(panel, BorderLayout.WEST);
		panel.setBackground(color);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JLabel atras = new JLabel("");
		panel_9.add(atras);
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callMenu(user);
			}
		});
		atras.setIcon(new ImageIcon(JLab.class.getResource("/icon/atras3.png")));

		JLabel lblNewLabel_11_1 = new JLabel("             ");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_9.add(lblNewLabel_11_1, BorderLayout.NORTH);

		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_9.add(lblNewLabel_12, BorderLayout.WEST);

		JPanel Central = new JPanel();
		Central.setOpaque(false);
		Principal.add(Central, BorderLayout.CENTER);
		Central.setLayout(new BorderLayout(0, 0));

		JPanel Superior = new JPanel();
		Superior.setOpaque(false);
		Central.add(Superior, BorderLayout.NORTH);
		Superior.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		Superior.add(panel_1, BorderLayout.WEST);
		panel_1.setOpaque(false);

		JLabel lblNewLabel_19 = new JLabel("                        ");
		panel_1.add(lblNewLabel_19);

		JLabel lblNewLabel_1 = new JLabel("");
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(JLab.class.getResource("/icon/Lab.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("Laboratorios");
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		Superior.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_4.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("  ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panel_3.add(lblNewLabel_4, BorderLayout.EAST);

		JLabel lblNewLabel_5 = new JLabel(" ");
		panel_3.add(lblNewLabel_5, BorderLayout.NORTH);

		JButton eliminados = new JButton("Recuperar laboratorios eliminados");
		panel_3.add(eliminados, BorderLayout.CENTER);
		eliminados.setIcon(new ImageIcon(JLab.class.getResource("/icon/basura.png")));
		eliminados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callRecuperarLab(user);
			}
		});
		eliminados.setForeground(Color.WHITE);
		eliminados.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		eliminados.setBackground(new Color(41, 173, 158));

		JPanel panel_14 = new JPanel();
		panel_14.setOpaque(false);
		Central.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_13 = new JLabel("                                  ");
		panel_14.add(lblNewLabel_13, BorderLayout.WEST);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_14.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_7.add(panel_8, BorderLayout.EAST);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_7.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 20));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_6.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(20);
		panel_2.setOpaque(false);
		panel_5.add(panel_2, BorderLayout.WEST);

		JLabel lblNewLabel_2 = new JLabel("Buscar por");
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		JComboBox<String> comboBox = new JComboBox<String>();
		panel_2.add(comboBox);
		comboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Laboratorio", "Vendedor", "Tel\u00E9fono" }));
		comboBox.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		comboBox.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel_16 = new JLabel("                  ");
		panel_2.add(lblNewLabel_16);

		JLabel lblNewLabel_3 = new JLabel("Introduzca su b\u00FAsqueda:");
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		textField = new JTextField();
		panel_2.add(textField);
		textField.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				repaint();
				int n = 0;
				if (comboBox.getSelectedItem().equals("Laboratorio")) {
					n = 3;
				} else if (comboBox.getSelectedItem().equals("Vendedor")) {
					n = 1;
				} else {
					n = 2;
				}
				Funciones funcion = new Funciones();
				funcion.filtro(n, table, textField);
			}
		});
		textField.setColumns(10);

		JButton btnNewButton = new JButton("");
		panel_5.add(btnNewButton, BorderLayout.EAST);
		btnNewButton.setBackground(color);
		btnNewButton.setIcon(new ImageIcon(JLab.class.getResource("/icon/refresh.png")));

		table = new JTable();
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
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
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);
		panel_6.add(pane, BorderLayout.CENTER);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		JPanel panel_13 = new JPanel();
		panel_13.setOpaque(false);
		panel_14.add(panel_13, BorderLayout.EAST);
		panel_13.setLayout(new BorderLayout(0, 0));
		JPanel botones = new JPanel();
		botones.setOpaque(false);
		panel_13.add(botones, BorderLayout.CENTER);
		botones.setLayout(new GridLayout(4, 1, 0, 30));
		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		botones.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_6 = new JLabel("                          ");
		panel_10.add(lblNewLabel_6, BorderLayout.WEST);
		JLabel lblNewLabel_7 = new JLabel("                          ");
		panel_10.add(lblNewLabel_7, BorderLayout.EAST);

		JButton agregar = new JButton("Agregar laboratorio");
		panel_10.add(agregar, BorderLayout.CENTER);
		agregar.setIcon(new ImageIcon(JLab.class.getResource("/icon/agregar.png")));
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callAgregarLab();
			}
		});
		agregar.setForeground(Color.WHITE);
		agregar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		agregar.setBackground(color);

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		botones.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_8 = new JLabel("                                                    ");
		panel_11.add(lblNewLabel_8, BorderLayout.WEST);
		JLabel lblNewLabel_9 = new JLabel("                                                    ");
		panel_11.add(lblNewLabel_9, BorderLayout.EAST);
		JButton editar = new JButton("Editar");
		panel_11.add(editar, BorderLayout.CENTER);
		editar.setIcon(new ImageIcon(JLab.class.getResource("/icon/edit.png")));
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
					funcion.callModificarLab(cod);
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el laboratorio a editar", "Advertencia", 2, null);
				}
			}
		});
		editar.setForeground(Color.WHITE);
		editar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		editar.setBackground(color);

		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		botones.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_10 = new JLabel("                                                    ");
		panel_12.add(lblNewLabel_10, BorderLayout.WEST);
		JLabel lblNewLabel_11 = new JLabel("                                                    ");
		panel_12.add(lblNewLabel_11, BorderLayout.EAST);
		
		
		JButton eliminar = new JButton("Eliminar");
		panel_12.add(eliminar, BorderLayout.CENTER);
		eliminar.setIcon(new ImageIcon(JLab.class.getResource("/icon/cancel.png")));
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0).toString());
					String lab = model.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 3).toString();
					funcion.callEliminarLab(cod, lab);
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el laboratorio a eliminar", "Advertencia", 2, null);
				}	
			}
		});

		eliminar.setBackground(new Color(250, 116, 112));
		eliminar.setForeground(new Color(255, 255, 255));
		eliminar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));

		JLabel lblNewLabel_20 = new JLabel("  ");
		botones.add(lblNewLabel_20);

		JLabel img = new JLabel();
		panel_13.add(img, BorderLayout.SOUTH);
		img.setIcon(new ImageIcon(JLab.class.getResource("/icon/labo1.png")));
		img.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_14 = new JLabel("  ");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel_14.add(lblNewLabel_14, BorderLayout.NORTH);

		JLabel lblNewLabel_15 = new JLabel(" ");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel_14.add(lblNewLabel_15, BorderLayout.SOUTH);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatos();
			}
		});

		insertarDatos();
		if (!funcion.comprobar(user)) {
			agregar.setEnabled(false);
			editar.setEnabled(false);
			eliminar.setEnabled(false);
			eliminados.setEnabled(false);
		}

	}

	public void insertarDatos() {
		Object[] row = new Object[4];
		try {
			funcion.limpiarTabla(table);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from laboratorio where activo = true");
			while (rs.next()) {
				row[0] = rs.getInt(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				model.addRow(row);
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
