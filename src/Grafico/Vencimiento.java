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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Vencimiento extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private Funciones funcion = new Funciones();
	private final JPanel panel_7 = new JPanel();

	public Vencimiento(String user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Vencimiento.class.getResource("/icon/prescription.png")));
		Color color = new Color(0, 163, 224);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Object[] columnas = { "COD", "NOMBRE", "PRESENTACIÓN", "LABORATORIO", "PRINCIPIO ACTIVO",
				"FECHA DE VENCIMIENTO" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JPanel Principal = new JPanel();
		scrollPane.setViewportView(Principal);
		Principal.setBackground(Color.WHITE);
		Principal.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		Principal.add(panel, BorderLayout.WEST);
		panel.setBackground(color);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel atras_1 = new JLabel("");
		panel_2.add(atras_1);
		atras_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callProducto(user);
			}
		});
		atras_1.setIcon(new ImageIcon(Vencimiento.class.getResource("/icon/Metro Icon 13x.png")));

		JLabel lblNewLabel_11 = new JLabel("             ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_11, BorderLayout.NORTH);

		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_2.add(lblNewLabel_12, BorderLayout.WEST);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		Principal.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel img = new JLabel();
		panel_4.add(img);
		img.setIcon(new ImageIcon(Vencimiento.class.getResource("/icon/venc1.jpg")));

		JLabel lblNewLabel_8 = new JLabel("                  ");
		panel_4.add(lblNewLabel_8, BorderLayout.EAST);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		Principal.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_5.add(panel_1, BorderLayout.NORTH);
		panel_7.setOpaque(false);
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_6 = new JLabel("                ");
		panel_7.add(lblNewLabel_6, BorderLayout.WEST);

		JLabel lblNewLabel_7 = new JLabel(" ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_7.add(lblNewLabel_7, BorderLayout.NORTH);

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_7.add(panel_8);

		JLabel lblNewLabel_1 = new JLabel("");
		panel_8.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Vencimiento.class.getResource("/icon/venc.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("Vencimientos cercanos");
		panel_8.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_5.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 20));

		JLabel lblNewLabel_2 = new JLabel("Productos que tienen fecha de vencimiento dentro de los siguientes 2 meses");
		panel_3.add(lblNewLabel_2, BorderLayout.NORTH);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

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
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);

		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);
		panel_3.add(pane, BorderLayout.CENTER);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);

		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_5.add(lblNewLabel_3, BorderLayout.SOUTH);

		JLabel lblNewLabel_4 = new JLabel("                          ");
		panel_5.add(lblNewLabel_4, BorderLayout.WEST);

		JLabel lblNewLabel_5 = new JLabel("                    ");
		panel_5.add(lblNewLabel_5, BorderLayout.EAST);

		insertarDatos();

	}

	@SuppressWarnings("deprecation")
	public void insertarDatos() {
		Object[] row = new Object[6];
		try {
			SimpleDateFormat formato =  new SimpleDateFormat("dd-MM-YYYY");
			funcion.limpiarTabla(table);
			Date fecha = new Date();
			Date actual = new Date();
			String date = "yyyy-MM-dd";
			SimpleDateFormat obj = new SimpleDateFormat(date);
			fecha.setMonth(fecha.getMonth() + 2);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto WHERE fechaVencimiento between '"
					+ obj.format(actual) + "' and '" + obj.format(fecha) + "'");
			while (rs.next()) {
				row[0] = rs.getInt(1);
				row[1] = rs.getString(2);
				row[2] = funcion.getDato("presentacion", 2, rs.getInt(9));
				row[3] = funcion.getDato("laboratorio", 4, rs.getInt(10));
				row[4] = rs.getString(3);
				row[5] = formato.format(rs.getDate(6));
				model.addRow(row);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

}
