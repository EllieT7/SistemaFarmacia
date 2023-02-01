package Grafico;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Clases.Conexion;
import Clases.Funciones;
import Clases.Reporte;
import Clases.Venta;
import Clases.VentaReporte;

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
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class VentaR extends JFrame {
	private JPanel contentPane;
	private JList<Venta> list;
	private DefaultListModel<Venta> listModel;
	private Funciones funcion = new Funciones();
	List<VentaReporte> list1 = new ArrayList<VentaReporte>();

	@SuppressWarnings("deprecation")
	public VentaR(String user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentaR.class.getResource("/icon/prescription.png")));
		Color color = new Color(89, 217, 206);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		listModel = new DefaultListModel<Venta>();
		Date fecha = new Date();
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
				funcion.callVenta(user);
			}
		});
		atras.setIcon(new ImageIcon(VentaR.class.getResource("/icon/atras2.png")));

		JLabel lblNewLabel_11 = new JLabel("             ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_11, BorderLayout.NORTH);

		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_2.add(lblNewLabel_12, BorderLayout.WEST);

		JPanel panel_D = new JPanel();
		panel_D.setOpaque(false);
		Principal.add(panel_D, BorderLayout.EAST);
		panel_D.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_D.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_8.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_5_1 = new JLabel(" ");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 99));
		panel_6.add(lblNewLabel_5_1, BorderLayout.NORTH);

		JLabel lblNewLabel_6_3 = new JLabel("  ");
		lblNewLabel_6_3.setIcon(new ImageIcon(VentaR.class.getResource("/icon/lightbulb.png")));
		lblNewLabel_6_3.setVerticalAlignment(SwingConstants.TOP);
		panel_6.add(lblNewLabel_6_3, BorderLayout.EAST);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setBorder(new LineBorder(new Color(89, 217, 206), 3, true));
		panel_6.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9, BorderLayout.SOUTH);
		panel_9.setBackground(color);

		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon(VentaR.class.getResource("/icon/files.png")));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_4_1);

		JTextPane txtpnSeleccioneUnRegistro_1 = new JTextPane();
		txtpnSeleccioneUnRegistro_1.setText("Los archivos son almacenados en la direcci\u00F3n C:\\Reportes");
		txtpnSeleccioneUnRegistro_1.setFont(new Font("IBM Plex Sans", Font.ITALIC, 15));
		txtpnSeleccioneUnRegistro_1.setBackground(Color.WHITE);
		panel_9.add(txtpnSeleccioneUnRegistro_1);

		JLabel lblNewLabel_18 = new JLabel("Seleccione un registro y presione el bot\u00F3n.");
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18.setFont(new Font("IBM Plex Sans", Font.ITALIC, 15));
		panel_7.add(lblNewLabel_18, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_8.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_6_1 = new JLabel("                                               ");
		panel_1.add(lblNewLabel_6_1, BorderLayout.WEST);

		JLabel lblNewLabel_8_1 = new JLabel("                                                 ");
		panel_1.add(lblNewLabel_8_1, BorderLayout.EAST);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new GridLayout(2, 1, 0, 20));
		
				JButton btnNewButton = new JButton("Generar PDF");
				panel_10.add(btnNewButton);
				btnNewButton.setToolTipText("Los archivos son almacenados en C:\\Reportes");
				btnNewButton.setIcon(new ImageIcon(VentaR.class.getResource("/icon/file (2).png")));
				btnNewButton.setBackground(color);
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 18));
				
				JButton btnGenerarExcel = new JButton("Generar Excel");
				btnGenerarExcel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						funcion.callFechaExcel();
					}
				});
				btnGenerarExcel.setIcon(new ImageIcon(VentaR.class.getResource("/icon/excel (1).png")));
				btnGenerarExcel.setToolTipText("Los archivos son almacenados en C:\\Reportes\\VentasDiarias");
				btnGenerarExcel.setForeground(Color.WHITE);
				btnGenerarExcel.setFont(new Font("IBM Plex Sans", Font.BOLD, 18));
				btnGenerarExcel.setBackground(new Color(89, 217, 206));
				panel_10.add(btnGenerarExcel);
				
				JLabel lblNewLabel_3 = new JLabel(" ");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
				panel_1.add(lblNewLabel_3, BorderLayout.NORTH);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int index = list.getSelectedIndex();
							Venta venta = (Venta) (listModel.getElementAt(index));
							rellenarList(venta.getCod());
							Reporte reporte = new Reporte();
							reporte.reporteVenta(venta.getCod(), venta.getFechaVenta(), venta.getCodCli(),venta.getNombre(), venta.getImporte(), list1);
						}catch(IndexOutOfBoundsException error) {
							JOptionPane.showMessageDialog(null,"Seleccione un registro de venta en el panel de la \nizquierda para generar el PDF", "Advertencia", 2, null);	
						}
					}
				});
		JLabel img = new JLabel("");
		panel_D.add(img, BorderLayout.CENTER);
		img.setIcon(new ImageIcon(VentaR.class.getResource("/icon/venta1.jpg")));
		img.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_Central = new JPanel();
		panel_Central.setOpaque(false);
		Principal.add(panel_Central, BorderLayout.CENTER);
		panel_Central.setLayout(new BorderLayout(0, 0));

		JPanel Superior = new JPanel();
		Superior.setOpaque(false);
		panel_Central.add(Superior, BorderLayout.NORTH);
		Superior.setLayout(new BorderLayout(0, 30));

		JLabel lblNewLabel_6 = new JLabel(" ");
		Superior.add(lblNewLabel_6, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		Superior.add(panel_4, BorderLayout.SOUTH);
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setAlignment(FlowLayout.LEFT);

		JLabel lblNewLabel_13 = new JLabel("                                           ");
		panel_4.add(lblNewLabel_13);

		JLabel lblNewLabel_2 = new JLabel("Mes:");
		panel_4.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));
		JComboBox<String> Mes = new JComboBox<String>();
		Mes.setPreferredSize(new Dimension(250, 30));
		panel_4.add(Mes);
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

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		Superior.add(panel_3, BorderLayout.CENTER);
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JLabel lblNewLabel_5 = new JLabel("                ");
		panel_3.add(lblNewLabel_5);

		JLabel lblNewLabel_1 = new JLabel("");
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(VentaR.class.getResource("/icon/ventaR.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("Registro de ventas");
		panel_3.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_Central.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_7 = new JLabel("              ");
		panel_5.add(lblNewLabel_7, BorderLayout.WEST);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1, BorderLayout.CENTER);

		list = new JList<Venta>();
		list.setVisibleRowCount(2);
		list.setFont(new Font("Monospaced", Font.PLAIN, 17));
		list.setModel(listModel);
		scrollPane_1.setViewportView(list);

		list.setCellRenderer(new Listm(10, 60, color));

		JLabel lblNewLabel_14 = new JLabel("   ");
		panel_5.add(lblNewLabel_14, BorderLayout.NORTH);

		JLabel lblNewLabel_17 = new JLabel("  ");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 56));
		panel_5.add(lblNewLabel_17, BorderLayout.SOUTH);
		insertarDatosList(fecha.getMonth() + 1, fecha.getYear());

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
					"select * from venta where fecha between '" + date + "' and '" + dateMax + "' order by fecha;");
			while (rs.next()) {
				int id = rs.getInt(1);
				String fecha = (rs.getDate(2).toString());
				Double imp = Double.parseDouble(rs.getBigDecimal(3).toString());
				String ci = rs.getString(4);
				Venta venta = new Venta(fecha, new BigDecimal(imp), ci, id, rs.getString(5));
				listModel.addElement(venta);

			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

	public void rellenarList(int cod) {
		try {
			list1.clear();
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery(
					"select a.cantidad, a.tipo, b.nombre, c.descripcion, d.laboratorio, a.precio, a.importetotal"
							+ " from venta_producto a, producto b, presentacion c, laboratorio d where a.venta_codventa="
							+ cod + " and a.producto_cod = b.cod "
							+ "and b.presentacion_codpres = c.codpres and b.laboratorio = d.codlab;");

			while (rs.next()) {
				VentaReporte venta = new VentaReporte(rs.getInt(1), funcion.getTipo(rs.getString(2)+""), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getBigDecimal(7));
				list1.add(venta);
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
