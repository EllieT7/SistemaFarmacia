package Grafico;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Clases.Conexion;
import Clases.Funciones;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class JPlan extends JFrame {
	private JPanel contentPane;
	private DefaultTableModel model;
	private Funciones funcion = new Funciones();

	public JPlan(String user) {

		// Declaracion del color principal de la interfaz
		Color color = new Color(183, 212, 97);

		// Configuraciones del Frame
		setIconImage(Toolkit.getDefaultToolkit().getImage(JPlan.class.getResource("/icon/prescription.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Modelo para deshabilitar la primera columna de la tabla
		model = new DefaultTableModel(7, 2) {
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};

		// titulos laterales de la tabla
		String dias[] = { "LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO", "DOMINGO" };
		for (int i = 0; i < 7; i++)
			model.setValueAt(dias[i], i, 0);// insertando los titulos de la tabla al modelo

		// Estableciendo fecha actual
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("EEEE-dd-MMMM-YYYY");
		String fecha = format.format(date);
		String[] fecha1 = fecha.toString().split("-");
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JPanel Principal = new JPanel();
		Principal.setBackground(Color.WHITE);
		scrollPane.setViewportView(Principal);
		Principal.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		Principal.add(panel, BorderLayout.WEST);
		panel.setBackground(color);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel atras = new JLabel("");
		panel_2.add(atras);
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callMenu(user);
			}
		});
		atras.setIcon(new ImageIcon(JPlan.class.getResource("/icon/atras5.png")));

		JLabel lblNewLabel_11_1 = new JLabel("             ");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_11_1, BorderLayout.NORTH);

		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_2.add(lblNewLabel_12, BorderLayout.WEST);

		JPanel Central = new JPanel();
		Central.setOpaque(false);
		Principal.add(Central, BorderLayout.CENTER);
		Central.setLayout(new BorderLayout(0, 0));

		JPanel Superior = new JPanel();
		Superior.setOpaque(false);
		Central.add(Superior, BorderLayout.NORTH);
		Superior.setLayout(new BorderLayout(0, 0));

		JLabel img = new JLabel();
		Superior.add(img, BorderLayout.CENTER);
		img.setIcon(new ImageIcon(JPlan.class.getResource("/icon/plan1.jpg")));

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		Superior.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_6.add(panel_5, BorderLayout.NORTH);
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_5.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel btnNewButton_2 = new JLabel("   ");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_4.add(btnNewButton_2, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_3, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("");
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel_1.setIcon(new ImageIcon(JPlan.class.getResource("/icon/Plan.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("Planificaci\u00F3n");
		panel_3.add(lblNewLabel);
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));

		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel_4.add(panel_12, BorderLayout.SOUTH);

		JLabel lblNewLabel_5_1 = new JLabel("                        ");
		panel_12.add(lblNewLabel_5_1);

		JLabel lblNewLabel_2 = new JLabel(
				"Hoy es " + fecha1[0] + " " + fecha1[1] + " de " + fecha1[2] + " de " + fecha1[3]);
		panel_12.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		JLabel lblNewLabel_4 = new JLabel("                       ");
		panel_5.add(lblNewLabel_4);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		Superior.add(panel_9, BorderLayout.EAST);

		// Accion --> redirecciona a planificacion completa
		JButton planTotal = new JButton("Ver la planificación completa");
		planTotal.setPreferredSize(new Dimension(300, 40));
		panel_9.add(planTotal);
		planTotal.setIcon(new ImageIcon(JPlan.class.getResource("/icon/lista.png")));
		planTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callPlanTotal(user);
			}
		});
		planTotal.setForeground(Color.WHITE);
		planTotal.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		planTotal.setBackground(color);

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		Central.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_14 = new JLabel("                 ");
		panel_11.add(lblNewLabel_14, BorderLayout.WEST);
		JLabel lblNewLabel_15 = new JLabel("   ");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_11.add(lblNewLabel_15, BorderLayout.SOUTH);
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_11.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 10));
		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_7.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_3 = new JLabel("Para esta semana:");
		panel_8.add(lblNewLabel_3, BorderLayout.WEST);
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 23));

		// Accion --> Actualizar datos de la tabla
		JButton actualizar = new JButton("");
		panel_8.add(actualizar, BorderLayout.EAST);
		actualizar.setBackground(color);
		actualizar.setIcon(new ImageIcon(JPlan.class.getResource("/icon/refresh.png")));

		// Declaracion y copnfiguracion de la tabla
		JTable table = new JTable(model) {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				if (columnIndex == 0)
					super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);
				else
					super.changeSelection(rowIndex, columnIndex, toggle, extend);
			}
		};
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setColumnSelectionAllowed(true);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.black);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setSelectionForeground(Color.WHITE);
		table.setRowHeight(120);
		table.getTableHeader().setBackground(color);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("IBM Plex Sans", Font.BOLD, 20));

		// Configurando el tamaño de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(600);
		table.getColumnModel().getColumn(1).setPreferredWidth(3000);
		table.getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());

		// Creación y visualización de la ventana completa.
		JScrollPane pane = new JScrollPane(table);
		panel_7.add(pane, BorderLayout.CENTER);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setViewportBorder(null);
		pane.setForeground(Color.WHITE);
		pane.setBackground(Color.WHITE);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_11.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		JPanel botones = new JPanel();
		botones.setOpaque(false);
		panel_1.add(botones);
		botones.setLayout(new GridLayout(4, 1, 0, 40));
		JLabel lblNewLabel_16 = new JLabel(
				"                                                                                                                                           ");
		botones.add(lblNewLabel_16);
		JPanel panel_10_1 = new JPanel();
		panel_10_1.setOpaque(false);
		botones.add(panel_10_1);
		panel_10_1.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_10 = new JLabel("                                       ");
		panel_10_1.add(lblNewLabel_10, BorderLayout.WEST);
		JLabel lblNewLabel_11 = new JLabel("                                       ");
		panel_10_1.add(lblNewLabel_11, BorderLayout.EAST);

		// Accion --> Agregar nueva tarea
		JButton agregar = new JButton("Agregar");
		agregar.setHorizontalTextPosition(SwingConstants.LEFT);
		panel_10_1.add(agregar, BorderLayout.CENTER);
		agregar.setIcon(new ImageIcon(JPlan.class.getResource("/icon/agregar.png")));
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callAgregarPlan(user);
			}
		});
		agregar.setForeground(Color.WHITE);
		agregar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		agregar.setBackground(color);

		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		botones.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_6 = new JLabel("                                       ");
		panel_10.add(lblNewLabel_6, BorderLayout.WEST);

		JLabel lblNewLabel_7 = new JLabel("                                       ");
		panel_10.add(lblNewLabel_7, BorderLayout.EAST);

		JButton editar = new JButton("Editar");
		editar.setHorizontalTextPosition(SwingConstants.LEFT);
		panel_10.add(editar, BorderLayout.CENTER);
		editar.setIcon(new ImageIcon(JPlan.class.getResource("/icon/edit.png")));
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callModificarPlan(user);
			}
		});
		editar.setForeground(Color.WHITE);
		editar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		editar.setBackground(color);

		JPanel panel_10_2 = new JPanel();
		panel_10_2.setOpaque(false);
		botones.add(panel_10_2);
		panel_10_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_8 = new JLabel("                                       ");
		panel_10_2.add(lblNewLabel_8, BorderLayout.WEST);

		JLabel lblNewLabel_9 = new JLabel("                                       ");
		panel_10_2.add(lblNewLabel_9, BorderLayout.EAST);

		// Accion --> eliminar tarea
		JButton eliminar = new JButton("Eliminar");
		eliminar.setHorizontalTextPosition(SwingConstants.LEFT);
		panel_10_2.add(eliminar, BorderLayout.CENTER);
		eliminar.setIcon(new ImageIcon(JPlan.class.getResource("/icon/cancel.png")));
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callEliminarPlan();
			}
		});
		eliminar.setBackground(new Color(250, 116, 112));
		eliminar.setForeground(new Color(255, 255, 255));
		eliminar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		table.getTableHeader().setPreferredSize(new Dimension(0, 0));
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatos();
			}
		});

		// Insertar tareas existentes en la base de datos a la tabla
		insertarDatos();
	}

	public void insertarDatos() {
		Object[] row = new Object[2];

		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			int i = 0;

			while (i < 7) {
				String date = getFecha(i);
				ResultSet rs = (ResultSet) s
						.executeQuery("select descripcion from tarea where fecha = '" + date + "';");
				String cad = "";
				while (rs.next()) {
					row[1] = rs.getString(1);
					cad += "<html> - " + row[1].toString() + "<br>";
				}
				model.setValueAt(cad, i, 1);
				i++;
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

	@SuppressWarnings("deprecation")

	// Devuelve la fecha de acuerdo al dia de la semana
	public String getFecha(int i) {
		String f = "";
		Date fecha = new Date();
		String date = "yyyy-MM-dd";
		SimpleDateFormat obj = new SimpleDateFormat(date);

		if (fecha.getDay() == 0) {
			fecha.setDate(fecha.getDate() - 6);
		} else {
			fecha.setDate(fecha.getDate() - fecha.getDay() + 1);
		}
		fecha.setDate(fecha.getDate() + i);
		f = obj.format(fecha);
		return f;
	}
}