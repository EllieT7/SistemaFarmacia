package Grafico;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Clases.Compra;
import Clases.CompraReporte;
import Clases.Conexion;
import Clases.Funciones;
import Clases.Reporte;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class CompraR extends JFrame {
	private JPanel contentPane;
	private DefaultTableModel model;
	private JList<Compra> list;
	private DefaultListModel<Compra> listModel;
	private Funciones funcion = new Funciones();
	List<CompraReporte> list1 = new ArrayList<CompraReporte>();

	@SuppressWarnings("deprecation")
	public CompraR(String user) {
		Color color = new Color(101, 166, 240);
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
		listModel = new DefaultListModel<Compra>();
		Date fecha = new Date();
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JPanel Principal = new JPanel();
		Principal.setBackground(Color.WHITE);
		scrollPane.setViewportView(Principal);
		Principal.setLayout(new BorderLayout(0, 0));

		JPanel panelIzq = new JPanel();
		Principal.add(panelIzq, BorderLayout.WEST);
		panelIzq.setBackground(color);
		panelIzq.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panelIzq.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel atras = new JLabel("");
		panel_2.add(atras);
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callCompra(user);
			}
		});
		atras.setIcon(new ImageIcon(CompraR.class.getResource("/icon/atras1.png")));

		JLabel lblNewLabel_11 = new JLabel("             ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_11, BorderLayout.NORTH);

		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_2.add(lblNewLabel_12, BorderLayout.WEST);

		JPanel panelCentral = new JPanel();
		panelCentral.setOpaque(false);
		Principal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panelCentral.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 30));

		JLabel lblNewLabel_10 = new JLabel(" ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 6));
		panel_6.add(lblNewLabel_10, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_6.add(panel_5, BorderLayout.CENTER);
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JLabel lblNewLabel_9 = new JLabel("                ");
		panel_5.add(lblNewLabel_9);

		JLabel lblNewLabel_1 = new JLabel("");
		panel_5.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(CompraR.class.getResource("/icon/compraR.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("Registro de compras");
		panel_5.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));

		JPanel panelD = new JPanel();
		panelD.setOpaque(false);
		panelD.setBounds(1050, 30, 494, 802);
		Principal.add(panelD, BorderLayout.EAST);
		panelD.setLayout(new BorderLayout(0, 0));
		JLabel img = new JLabel("");
		panelD.add(img, BorderLayout.CENTER);
		img.setIcon(new ImageIcon(CompraR.class.getResource("/icon/compra2.png")));
		img.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panelD.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel_4.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBorder(new LineBorder(color, 3, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new BorderLayout(5, 7));

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(color);
		panel_1.add(panel_9, BorderLayout.SOUTH);

		JLabel lblNewLabel_4 = new JLabel("");
		panel_9.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(CompraR.class.getResource("/icon/files.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);

		JTextPane txtpnSeleccioneUnRegistro = new JTextPane();
		panel_9.add(txtpnSeleccioneUnRegistro);
		txtpnSeleccioneUnRegistro.setText("Los archivos son almacenados en la direcci\u00F3n C:\\Reportes");
		txtpnSeleccioneUnRegistro.setFont(new Font("IBM Plex Sans", Font.ITALIC, 15));
		txtpnSeleccioneUnRegistro.setBackground(Color.WHITE);

		JLabel lblNewLabel_18 = new JLabel("Seleccione un registro y presione el bot\u00F3n.");
		lblNewLabel_18.setFont(new Font("IBM Plex Sans", Font.ITALIC, 15));
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_18, BorderLayout.CENTER);

		JLabel lblNewLabel_5 = new JLabel(" ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 99));
		panel.add(lblNewLabel_5, BorderLayout.NORTH);

		JLabel lblNewLabel_6_2 = new JLabel("                                ");
		panel.add(lblNewLabel_6_2, BorderLayout.WEST);

		JLabel lblNewLabel_6_3 = new JLabel("                            ");
		lblNewLabel_6_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_6_3.setIcon(new ImageIcon(CompraR.class.getResource("/icon/lightbulb (1).png")));
		panel.add(lblNewLabel_6_3, BorderLayout.EAST);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_4.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_3.add(lblNewLabel_3, BorderLayout.NORTH);

		JLabel lblNewLabel_6 = new JLabel("                                                                      ");
		panel_3.add(lblNewLabel_6, BorderLayout.WEST);

		JLabel lblNewLabel_7 = new JLabel(" ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_3.add(lblNewLabel_7, BorderLayout.SOUTH);

		JLabel lblNewLabel_8 = new JLabel("                                                                      ");
		panel_3.add(lblNewLabel_8, BorderLayout.EAST);

		JButton btnNewButton_1 = new JButton("Generar PDF");
		panel_3.add(btnNewButton_1, BorderLayout.CENTER);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = list.getSelectedIndex();
					Compra compra = (Compra) (listModel.getElementAt(index));
					rellenarList(compra.getCod());
					Reporte reporte = new Reporte();
					reporte.reporteCompra(compra.getFactura(), funcion.getNombreLab(compra.getCodLab(), 4),
							funcion.getNombreLab(compra.getCodLab(), 2), compra.getFechaCompra().toString(),
							compra.getImporte(), list1);
				}catch(IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione un registro de compra en el panel de la \nizquierda para generar el PDF", "Advertencia", 2, null);
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(CompraR.class.getResource("/icon/file (2).png")));
		btnNewButton_1.setSelectedIcon(new ImageIcon(CompraR.class.getResource("/icon/file (2).png")));
		btnNewButton_1.setToolTipText("Los archivos son almacenados\r\nen C:\\Reportes");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 18));
		btnNewButton_1.setBackground(color);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_7.setBounds(64, 413, 1058, 38);
		panel_6.add(panel_7, BorderLayout.SOUTH);

		JLabel lblNewLabel_13 = new JLabel("                                           ");
		panel_7.add(lblNewLabel_13);

		JLabel lblNewLabel_2 = new JLabel("Mes:");
		panel_7.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		JComboBox<String> Mes = new JComboBox<String>();
		Mes.setPreferredSize(new Dimension(250, 30));
		Mes.setMinimumSize(new Dimension(50, 22));
		panel_7.add(Mes);
		Mes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatosList(Mes.getSelectedIndex() + 1, fecha.getYear());

			}
		});
		Mes.setModel(new DefaultComboBoxModel<String>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
				"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
		Mes.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		Mes.setBackground(Color.WHITE);
		Mes.setSelectedItem(funcion.getMes(fecha.getMonth() + 1));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panelCentral.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_14 = new JLabel("   ");
		panel_8.add(lblNewLabel_14, BorderLayout.NORTH);

		JLabel lblNewLabel_15 = new JLabel("            ");
		panel_8.add(lblNewLabel_15, BorderLayout.WEST);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_8.add(scrollPane_1, BorderLayout.CENTER);

		list = new JList<Compra>();
		list.setFont(new Font("Monospaced", Font.PLAIN, 17));
		list.setModel(listModel);
		list.setVisibleRowCount(1);
		scrollPane_1.setViewportView(list);

		list.setCellRenderer(new Listm(10, 60, color));

		JLabel lblNewLabel_17 = new JLabel("  ");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 56));
		panel_8.add(lblNewLabel_17, BorderLayout.SOUTH);
		insertarDatosList(fecha.getMonth() + 1, fecha.getYear());

	}

	public void rellenarList(int cod) {
		try {
			list1.clear();
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery(
					"select a.cod, a.nombre, b.descripcion, c.cantidadUni, c.cantidadCaja, c.fechaVenc from producto a, presentacion b, compra_producto c\r\n"
							+ "where c.producto_cod = a.cod\r\n" + "and a.presentacion_codpres = b.codPres\r\n"
							+ "and c.compra_codCompra = " + cod + ";");

			while (rs.next()) {
				CompraReporte compra = new CompraReporte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getString(6));
				list1.add(compra);
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

	public void insertarDatosList(int mes, int year) {
		listModel.removeAllElements();
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();

			String date = funcion.getFecha(mes - 1, year, 0);
			String dateMax = funcion.getFecha(mes - 1, year, 30);
			ResultSet rs = (ResultSet) s.executeQuery(
					"select * from compra where fecha between '" + date + "' and '" + dateMax + "' order by fecha;");
			while (rs.next()) {
				int id = rs.getInt(1);
				String fact = rs.getString(2);
				String fecha = (rs.getDate(3).toString());
				int codLab = rs.getInt(4);
				Double imp = Double.parseDouble(rs.getBigDecimal(5).toString());
				
				Compra compra = new Compra(fact, fecha, codLab, new BigDecimal(imp), id,rs.getBigDecimal(6));
				listModel.addElement(compra);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

}
