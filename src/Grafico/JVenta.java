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
import Clases.Acceso;
import Clases.Cliente;
import Clases.Conexion;
import Clases.Funciones;
import Clases.Producto;
import Clases.Venta;
import Clases.VentaProd;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseMotionAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Component;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class JVenta extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JLabel lblNewLabel_4;
	private JTextField CI, precio, stock, pres, labo, cli;
	private JComboBox<Producto> Prod;
	private JTextField total;
	private JRadioButton unidad;
	private JRadioButton caja = new JRadioButton("Caja");;
	private Double montoTotal = 0.0;
	private JSpinner cant;
	private SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
	private JDateChooser fecha;
	private Calendar cal = new GregorianCalendar();
	private Funciones funcion = new Funciones();
	private SpinnerNumberModel modelo;
	DecimalFormatSymbols separador = new DecimalFormatSymbols();
	private DecimalFormat formatoN;
	private Color color = new Color(89, 217, 206);
	private JButton btnNewButton_1_1_1_1, btnNewButton_3, btnNewButton_1, btnNewButton, btnNewButton_1_1_1_2, Generar,
			btnNewButton_2, cliente;

	public JVenta(String user) {
		separador.setDecimalSeparator('.');
		formatoN = new DecimalFormat("#.00", separador);
		InicializarElementos();
		leer();
		UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
		UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));

		setIconImage(Toolkit.getDefaultToolkit().getImage(JVenta.class.getResource("/icon/prescription.png")));

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setBounds(100, 100, 1875, 1085);
		this.setExtendedState(NORMAL);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!total.getText().isEmpty() || !CI.getText().isEmpty()) {
					ImageIcon icono = new ImageIcon(EliminarPlan.class.getResource("/icon/megaphone.png"));
					int c = JOptionPane.showOptionDialog(null, "Desea guardar los cambios?", // contenido de la ventana
							"Guardar cambios", // titulo de la ventana
							JOptionPane.YES_NO_CANCEL_OPTION, // para 3 botones si/no/cancel
							JOptionPane.QUESTION_MESSAGE, // tipo de ícono
							icono, // null para icono por defecto.
							new Object[] { "SI", "NO" }, // objeto para las opciones
							// null para YES, NO y CANCEL
							"SI"); // selección predeterminada
					if (c == 0) {
						try {
							escribeDatos();
							escribeDatosProd();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						File a = new File("datos.dat");
						File b = new File("datosP.dat");

						b.delete();
						a.delete();
					}
				}
				dispose();
				funcion.callMenu(user);

			}
		});
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callRegistroVenta(user);
			}
		});

		cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getCliente(CI.getText())) {
					JOptionPane.showMessageDialog(null,
							"El cliente no se encuentra registrado, ingrese el nombre manualmente");
					cli.setText("");
				}
			}
		});
		Producto pro = (Producto) Prod.getSelectedItem();
		labo.setText(funcion.getDato("laboratorio", 4, pro.getLab()));
		precio.setText(pro.getPrecioU() + "");
		stock.setText(pro.getStockU() + "");
		pres.setText(funcion.getDato("presentacion", 2, pro.getPres()));
		color(Integer.parseInt(stock.getText()), stock);

		unidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro = (Producto) Prod.getSelectedItem();
				cant.setValue(0);
				precio.setText(funcion.getCodigo(pro.getCod() + "", "producto", 1, 4));
				stock.setText(funcion.getCodigo(pro.getCod() + "", "producto", 1, 7));
				color(Integer.parseInt(stock.getText()), stock);
				modelo.setMaximum(Integer.parseInt(stock.getText()));

			}
		});

		caja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro = (Producto) Prod.getSelectedItem();
				cant.setValue(0);
				precio.setText(funcion.getCodigo(pro.getCod() + "", "producto", 1, 5));
				stock.setText(funcion.getCodigo(pro.getCod() + "", "producto", 1, 8));
				color(Integer.parseInt(stock.getText()), stock);
				modelo.setMaximum(Integer.parseInt(stock.getText()));

			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icono = new ImageIcon(EliminarPlan.class.getResource("/icon/megaphone.png"));
				int c = JOptionPane.showOptionDialog(null, "Está seguro de que quiere abrir una caja nueva?", // contenido
																												// de la
																												// ventana
						"Abrir caja", // titulo de la ventana
						JOptionPane.YES_NO_CANCEL_OPTION, // para 3 botones si/no/cancel
						JOptionPane.QUESTION_MESSAGE, // tipo de ícono
						icono, // null para icono por defecto.
						new Object[] { "SI", "NO" }, // objeto para las opciones
						// null para YES, NO y CANCEL
						"SI"); // selección predeterminada
				if (c == 0) {
					Producto pro = (Producto) Prod.getSelectedItem();
					abrirCaja(pro.getCod());
				}

			}
		});

		Prod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro = (Producto) Prod.getSelectedItem();
				unidad.setSelected(true);
				cant.setValue(0);
				pres.setText(funcion.getDato("presentacion", 2, pro.getPres()));
				labo.setText(funcion.getDato("laboratorio", 4, pro.getLab()));
				precio.setText(pro.getPrecioU() + "");
				stock.setText(pro.getStockU() + "");
				color(Integer.parseInt(stock.getText()), stock);
				modelo.setMaximum(Integer.parseInt(stock.getText()));
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro = (Producto) Prod.getSelectedItem();
				if (Integer.parseInt(cant.getValue().toString()) == 0) {
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad mayor a 0", "Advertencia", 2, null);
				} else {
					Object[] datosVenta = new Object[8];
					datosVenta[0] = pro.getCod();
					datosVenta[1] = pro.getNombre();
					datosVenta[2] = pres.getText();
					datosVenta[3] = pro.getFechaV();
					datosVenta[4] = cant.getValue().toString();
					datosVenta[5] = getTipo();
					datosVenta[6] = precio.getText();
					Double totalImp = Double.parseDouble(datosVenta[4].toString())
							* Double.parseDouble(datosVenta[6].toString());
					datosVenta[7] = formatoN.format(totalImp);

					if (pro.isControlado()) {
						funcion.callInfoControlados(pro.getCod(), model, datosVenta, montoTotal, total);
					} else {
						Object[] row = new Object[8];
						for (int i = 0; i < 8; i++) {
							row[i] = datosVenta[i];
						}
						model.addRow(row);
						cant.setValue(0);
						montoTotal = Double.parseDouble(row[7].toString());
						if (total.getText() != null && !total.getText().isEmpty()) {
							total.setText((Double.parseDouble(total.getText()) + montoTotal) + "");
						} else {
							total.setText(montoTotal + "");
						}
					}

				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callMenu(user);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					Double montoTotal = Double.parseDouble(total.getText())
							- Double.parseDouble(table.getValueAt(index, 7).toString());
					Double imp = Double.parseDouble(formatoN.format(montoTotal));
					total.setText(imp + "");
					model.removeRow(index);
				} catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla para eliminar", "Advertencia",
							2, null);
				}

			}
		});
		btnNewButton_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.limpiarTabla(table);
				total.setText("");
				montoTotal = 0.0;
				cant.setValue(0);
			}
		});
		Generar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobar()) {
					String f = formato.format(fecha.getDate());
					Double iTotal = Double.parseDouble(total.getText());
					String ci = CI.getText();
					Venta venta = new Venta(f, new BigDecimal(iTotal), ci, cli.getText());
					venta.agregar();
					int cod = funcion.getLastCod("venta");
					ingresarProducto(cod, user);
					if (!getCliente(ci)) {
						ImageIcon icono = new ImageIcon(EliminarPlan.class.getResource("/icon/megaphone.png"));
						int c = JOptionPane.showOptionDialog(null, "Desea registrar los datos del cliente?", // contenido
																												// de la
																												// ventana
								"Nuevo cliente", // titulo de la ventana
								JOptionPane.YES_NO_CANCEL_OPTION, // para 3 botones si/no/cancel
								JOptionPane.QUESTION_MESSAGE, // tipo de ícono
								icono, // null para icono por defecto.
								new Object[] { "SI", "NO" }, // objeto para las opciones
								// null para YES, NO y CANCEL
								"SI"); // selección predeterminada
						if (c == 0) {
							Cliente cliente = new Cliente(ci, cli.getText(), "-");
							cliente.agregar();
						}
					}

					JOptionPane.showMessageDialog(null, "Venta realizada correctamente!", "", -1,
							new ImageIcon(Acceso.class.getResource("/icon/checked.png")));
					cant.setValue(0);
					actualizar();
					limpiar();
					File a = new File("datos.dat");
					a.delete();
				}
			}
		});

		modelo.setMaximum(Integer.parseInt(stock.getText()));

	}

	@SuppressWarnings({ "removal", "deprecation" })
	public void InicializarElementos() {

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Object[] columnas = { "COD", "PRODUCTO", "PRESENTACIÓN", "VENCIMIENTO", "CANTIDAD", "TIPO", "PRECIO", "TOTAL" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		modelo = new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1));

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

		JPanel panel_21 = new JPanel();
		panel_21.setOpaque(false);
		panel.add(panel_21);
		panel_21.setLayout(new BorderLayout(0, 0));

		lblNewLabel_4 = new JLabel("");
		panel_21.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(JVenta.class.getResource("/icon/atras2.png")));

		JLabel lblNewLabel_11_1 = new JLabel("             ");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_21.add(lblNewLabel_11_1, BorderLayout.NORTH);

		JLabel lblNewLabel_12_1 = new JLabel("    ");
		panel_21.add(lblNewLabel_12_1, BorderLayout.WEST);

		JPanel panel_22 = new JPanel();
		panel_22.setOpaque(false);
		Principal.add(panel_22, BorderLayout.CENTER);
		panel_22.setLayout(new BorderLayout(0, 10));

		JPanel superior = new JPanel();
		superior.setOpaque(false);
		panel_22.add(superior, BorderLayout.NORTH);
		superior.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_15.setOpaque(false);
		superior.add(panel_15, BorderLayout.NORTH);
		panel_15.setLayout(new BorderLayout(0, 0));

		JPanel panel_16 = new JPanel();
		panel_16.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_16.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_15.add(panel_16, BorderLayout.NORTH);

		JPanel panel_17 = new JPanel();
		panel_17.setOpaque(false);
		panel_16.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_32 = new JLabel("  ");
		lblNewLabel_32.setFont(new Font("Tahoma", Font.PLAIN, 6));
		panel_17.add(lblNewLabel_32, BorderLayout.NORTH);

		JLabel lblNewLabel_33 = new JLabel("  ");
		lblNewLabel_33.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_17.add(lblNewLabel_33, BorderLayout.EAST);

		btnNewButton_1_1_1_1 = new JButton("Ver registro de ventas");
		panel_17.add(btnNewButton_1_1_1_1, BorderLayout.CENTER);
		btnNewButton_1_1_1_1.setIcon(new ImageIcon(JVenta.class.getResource("/icon/lista.png")));
		btnNewButton_1_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnNewButton_1_1_1_1.setBackground(color);

		JPanel panel_14 = new JPanel();
		panel_14.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_14.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_15.add(panel_14, BorderLayout.WEST);

		JLabel lblNewLabel_12 = new JLabel("                     ");
		panel_14.add(lblNewLabel_12);
		JLabel lblNewLabel_1 = new JLabel("");
		panel_14.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(JVenta.class.getResource("/icon/ventaR.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel = new JLabel("Registrar venta");
		panel_14.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_8.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_8.setOpaque(false);
		superior.add(panel_8, BorderLayout.CENTER);

		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel_8.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel_10.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_30 = new JLabel(" ");
		panel_11.add(lblNewLabel_30, BorderLayout.NORTH);
		JLabel lblNewLabel_29 = new JLabel(" ");
		panel_11.add(lblNewLabel_29, BorderLayout.SOUTH);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_11.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 204, 307, 0, 144, 207, 240, 0 };
		gbl_panel_3.rowHeights = new int[] { 35, 0, 35, 0, 35, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JLabel lblNewLabel_2_1 = new JLabel("Fecha:");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 0;
		panel_3.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		fecha = new JDateChooser();
		GridBagConstraints gbc_fecha = new GridBagConstraints();
		gbc_fecha.fill = GridBagConstraints.BOTH;
		gbc_fecha.insets = new Insets(0, 0, 5, 5);
		gbc_fecha.gridx = 1;
		gbc_fecha.gridy = 0;
		panel_3.add(fecha, gbc_fecha);
		fecha.getCalendarButton().setIcon(new ImageIcon(JVenta.class.getResource("/icon/calBG.png")));
		fecha.getCalendarButton().setBackground(Color.WHITE);
		fecha.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		fecha.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fecha.getJCalendar().setWeekOfYearVisible(false);
		fecha.getJCalendar().setDecorationBackgroundColor(color);
		fecha.getJCalendar().setWeekdayForeground(Color.black);
		fecha.getJCalendar().setTodayButtonVisible(true);
		fecha.getJCalendar().setBackground(Color.WHITE);
		fecha.setCalendar(cal);

		JLabel lblNewLabel_21 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_21 = new GridBagConstraints();
		gbc_lblNewLabel_21.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_21.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_21.gridx = 3;
		gbc_lblNewLabel_21.gridy = 0;
		panel_3.add(lblNewLabel_21, gbc_lblNewLabel_21);

		JLabel lblNewLabel_23 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_23 = new GridBagConstraints();
		gbc_lblNewLabel_23.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_23.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_23.gridx = 4;
		gbc_lblNewLabel_23.gridy = 0;
		panel_3.add(lblNewLabel_23, gbc_lblNewLabel_23);

		JLabel lblNewLabel_24 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_24 = new GridBagConstraints();
		gbc_lblNewLabel_24.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_24.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_24.gridx = 5;
		gbc_lblNewLabel_24.gridy = 0;
		panel_3.add(lblNewLabel_24, gbc_lblNewLabel_24);

		JLabel lblNewLabel_14 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_14.gridx = 1;
		gbc_lblNewLabel_14.gridy = 1;
		panel_3.add(lblNewLabel_14, gbc_lblNewLabel_14);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Ingrese el CI:");
		GridBagConstraints gbc_lblNewLabel_2_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1_1.gridy = 2;
		panel_3.add(lblNewLabel_2_1_1_1, gbc_lblNewLabel_2_1_1_1);
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		CI = new JTextField();
		GridBagConstraints gbc_CI = new GridBagConstraints();
		gbc_CI.fill = GridBagConstraints.BOTH;
		gbc_CI.insets = new Insets(0, 0, 5, 5);
		gbc_CI.gridx = 1;
		gbc_CI.gridy = 2;
		panel_3.add(CI, gbc_CI);
		CI.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		CI.setColumns(10);

		JLabel lblNewLabel_20 = new JLabel("     ");
		GridBagConstraints gbc_lblNewLabel_20 = new GridBagConstraints();
		gbc_lblNewLabel_20.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_20.gridx = 2;
		gbc_lblNewLabel_20.gridy = 2;
		panel_3.add(lblNewLabel_20, gbc_lblNewLabel_20);

		cliente = new JButton("Buscar");
		GridBagConstraints gbc_cliente = new GridBagConstraints();
		gbc_cliente.fill = GridBagConstraints.BOTH;
		gbc_cliente.insets = new Insets(0, 0, 5, 5);
		gbc_cliente.gridx = 3;
		gbc_cliente.gridy = 2;
		panel_3.add(cliente, gbc_cliente);
		cliente.setBackground(color);
		cliente.setForeground(Color.WHITE);
		cliente.setIcon(new ImageIcon(JVenta.class.getResource("/icon/buscar (1).png")));
		cliente.setFont(new Font("IBM Plex Sans", Font.BOLD, 14));
		JLabel lblNewLabel_3 = new JLabel("Nombre del cliente:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 2;
		panel_3.add(lblNewLabel_3, gbc_lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		cli = new JTextField();
		GridBagConstraints gbc_cli = new GridBagConstraints();
		gbc_cli.fill = GridBagConstraints.BOTH;
		gbc_cli.insets = new Insets(0, 0, 5, 0);
		gbc_cli.gridx = 5;
		gbc_cli.gridy = 2;
		panel_3.add(cli, gbc_cli);
		cli.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		cli.setColumns(10);

		JLabel lblNewLabel_19 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_19.gridx = 1;
		gbc_lblNewLabel_19.gridy = 3;
		panel_3.add(lblNewLabel_19, gbc_lblNewLabel_19);

		JLabel lblNewLabel_2_1_1 = new JLabel("Producto:");
		GridBagConstraints gbc_lblNewLabel_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2_1_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1.gridy = 4;
		panel_3.add(lblNewLabel_2_1_1, gbc_lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		Prod = new JComboBox<Producto>();
		Prod.setPreferredSize(new Dimension(200, 22));
		funcion.datoProducto(Prod);
		AutoCompleteDecorator.decorate(Prod);

		GridBagConstraints gbc_Prod = new GridBagConstraints();
		gbc_Prod.fill = GridBagConstraints.BOTH;
		gbc_Prod.insets = new Insets(0, 0, 0, 5);
		gbc_Prod.gridx = 1;
		gbc_Prod.gridy = 4;
		panel_3.add(Prod, gbc_Prod);

		JLabel lblNewLabel_22 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints();
		gbc_lblNewLabel_22.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_22.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_22.gridx = 3;
		gbc_lblNewLabel_22.gridy = 4;
		panel_3.add(lblNewLabel_22, gbc_lblNewLabel_22);

		JLabel text = new JLabel("Presentaci\u00F3n:");
		text.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_text = new GridBagConstraints();
		gbc_text.fill = GridBagConstraints.BOTH;
		gbc_text.insets = new Insets(0, 0, 0, 5);
		gbc_text.gridx = 4;
		gbc_text.gridy = 4;
		panel_3.add(text, gbc_text);
		text.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		pres = new JTextField();
		GridBagConstraints gbc_pres = new GridBagConstraints();
		gbc_pres.fill = GridBagConstraints.BOTH;
		gbc_pres.gridx = 5;
		gbc_pres.gridy = 4;
		panel_3.add(pres, gbc_pres);
		pres.setEditable(false);
		pres.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		pres.setColumns(10);

		JLabel lblNewLabel_31 = new JLabel("                                         ");
		panel_11.add(lblNewLabel_31, BorderLayout.EAST);
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_10.add(panel_4, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 206, 289, 404, 190, 0 };
		gbl_panel_4.rowHeights = new int[] { 126, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_4.add(label, gbc_label);

		JPanel panel_26 = new JPanel();
		panel_26.setOpaque(false);
		panel_26.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		GridBagConstraints gbc_panel_26 = new GridBagConstraints();
		gbc_panel_26.insets = new Insets(0, 0, 0, 5);
		gbc_panel_26.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_26.gridx = 1;
		gbc_panel_26.gridy = 0;
		panel_4.add(panel_26, gbc_panel_26);
		panel_26.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_26.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new GridLayout(3, 1, 0, 0));

		JLabel lblNewLabel_2 = new JLabel(" Laboratorio: ");
		panel_5.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));

		JLabel lblNewLabel_5 = new JLabel("Precio: ");
		panel_5.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));

		JLabel lblNewLabel_6 = new JLabel("Stock: ");
		panel_5.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		JPanel panel_1 = new JPanel();
		panel_26.add(panel_1, BorderLayout.CENTER);
		panel_1.setOpaque(false);
		panel_1.setBorder(null);
		panel_1.setBackground(SystemColor.text);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));

		JPanel panel_25 = new JPanel();
		panel_25.setOpaque(false);
		FlowLayout flowLayout_4 = (FlowLayout) panel_25.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_25);

		labo = new JTextField();
		panel_25.add(labo);
		labo.setPreferredSize(new Dimension(175, 30));
		labo.setEditable(false);
		labo.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		labo.setBackground(new Color(255, 255, 255));
		JPanel panel_23 = new JPanel();
		panel_23.setOpaque(false);
		FlowLayout flowLayout_2 = (FlowLayout) panel_23.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_23);

		precio = new JTextField();
		panel_23.add(precio);
		precio.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		precio.setEditable(false);
		precio.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Bs.");
		panel_23.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));

		JPanel panel_24 = new JPanel();
		panel_24.setOpaque(false);
		FlowLayout flowLayout_3 = (FlowLayout) panel_24.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_24);

		stock = new JTextField();
		panel_24.add(stock);
		stock.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		stock.setEditable(false);
		stock.setColumns(10);
		JLabel lblNewLabel_9 = new JLabel("     ");
		panel_24.add(lblNewLabel_9);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_7.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7.gridx = 2;
		gbc_panel_7.gridy = 0;
		panel_4.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_11 = new JLabel("                        ");
		panel_7.add(lblNewLabel_11, BorderLayout.WEST);
		JLabel lblNewLabel_13 = new JLabel("  ");
		panel_7.add(lblNewLabel_13, BorderLayout.NORTH);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setOpaque(false);
		panel_7.add(panel_1_1, BorderLayout.CENTER);
		panel_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel_1_1.setBackground(Color.WHITE);
		GridBagLayout gbl_panel_1_1 = new GridBagLayout();
		gbl_panel_1_1.columnWidths = new int[] { 158, 50 };
		gbl_panel_1_1.rowHeights = new int[] { 72, 72 };
		panel_1_1.setLayout(gbl_panel_1_1);
		JLabel lblNewLabel_2_2 = new JLabel("Cantidad:");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_2_2 = new GridBagConstraints();
		gbc_lblNewLabel_2_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_2.gridx = 0;
		gbc_lblNewLabel_2_2.gridy = 0;
		panel_1_1.add(lblNewLabel_2_2, gbc_lblNewLabel_2_2);
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 0;
		panel_1_1.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));

		cant = new JSpinner();
		cant.setPreferredSize(new Dimension(60, 30));
		panel_6.add(cant);
		cant.setToolTipText("Ingrese la cantidad, no debe sobrepasar el stock");
		cant.setModel(modelo);
		cant.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));

		JPanel panel_27 = new JPanel();
		panel_27.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_27.setBackground(Color.WHITE);

		GridBagConstraints gbc_panel_27 = new GridBagConstraints();
		gbc_panel_27.fill = GridBagConstraints.BOTH;
		gbc_panel_27.insets = new Insets(0, 0, 0, 5);
		gbc_panel_27.gridx = 0;
		gbc_panel_27.gridy = 1;
		panel_1_1.add(panel_27, gbc_panel_27);
		panel_27.setLayout(new GridLayout(0, 2, 0, 0));

		unidad = new JRadioButton("Unidad");
		unidad.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_27.add(unidad);
		unidad.setHorizontalAlignment(SwingConstants.RIGHT);
		unidad.setBackground(Color.WHITE);
		unidad.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		unidad.setSelected(true);
		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(unidad);
		grupo1.add(caja);
		panel_27.add(caja);
		caja.setBackground(Color.WHITE);
		caja.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		JPanel panel_28 = new JPanel();
		panel_28.setOpaque(false);
		GridBagConstraints gbc_panel_28 = new GridBagConstraints();
		gbc_panel_28.fill = GridBagConstraints.BOTH;
		gbc_panel_28.gridx = 1;
		gbc_panel_28.gridy = 1;
		panel_1_1.add(panel_28, gbc_panel_28);
		panel_28.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_25 = new JLabel("      ");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.PLAIN, 5));
		panel_28.add(lblNewLabel_25, BorderLayout.NORTH);

		JLabel lblNewLabel_28 = new JLabel("                 ");
		panel_28.add(lblNewLabel_28, BorderLayout.EAST);

		JLabel lblNewLabel_281 = new JLabel("        ");
		panel_28.add(lblNewLabel_281, BorderLayout.WEST);

		JLabel lblNewLabel_291 = new JLabel("                    ");
		lblNewLabel_291.setFont(new Font("Tahoma", Font.PLAIN, 5));
		panel_28.add(lblNewLabel_291, BorderLayout.SOUTH);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Abrir nueva caja");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setIcon(new ImageIcon(JVenta.class.getResource("/icon/box0.png")));
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(JVenta.class.getResource("/icon/box0.png")));
		btnNewButton_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnNewButton_2.setIcon(new ImageIcon(JVenta.class.getResource("/icon/box1.png")));
			}
		});
		btnNewButton_2.setPreferredSize(new Dimension(60, 60));
		panel_28.add(btnNewButton_2, BorderLayout.CENTER);

		Prod.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		JLabel lblNewLabel_15 = new JLabel(" ");
		panel_7.add(lblNewLabel_15, BorderLayout.SOUTH);

		JLabel lblNewLabel_18 = new JLabel("   ");
		panel_7.add(lblNewLabel_18, BorderLayout.EAST);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_9.gridx = 3;
		gbc_panel_9.gridy = 0;
		panel_4.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		btnNewButton_3 = new JButton("Agregar");
		panel_9.add(btnNewButton_3, BorderLayout.CENTER);
		btnNewButton_3.setIcon(new ImageIcon(JVenta.class.getResource("/icon/agregar.png")));
		btnNewButton_3.setBackground(color);
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("IBM Plex Sans", Font.BOLD, 17));

		JLabel lblNewLabel_26 = new JLabel("   ");
		lblNewLabel_26.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel_9.add(lblNewLabel_26, BorderLayout.WEST);

		JLabel lblNewLabel_27 = new JLabel("");
		lblNewLabel_27.setIcon(new ImageIcon(JVenta.class.getResource("/icon/ventaOF.png")));
		panel_8.add(lblNewLabel_27);
		JPanel panel_18 = new JPanel();
		panel_18.setOpaque(false);
		panel_22.add(panel_18, BorderLayout.CENTER);
		panel_18.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_34 = new JLabel("                                                    ");
		panel_18.add(lblNewLabel_34, BorderLayout.WEST);

		JLabel lblNewLabel_35 = new JLabel("                                                                     ");
		panel_18.add(lblNewLabel_35, BorderLayout.EAST);

		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel_18.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new BorderLayout(0, 0));

		JPanel panel_13 = new JPanel();
		panel_13.setOpaque(false);
		FlowLayout flowLayout_5 = (FlowLayout) panel_13.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panel_12.add(panel_13, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		panel_13.add(panel_2);
		panel_2.setBackground(color);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel totalPago = new JLabel("Total a pagar:");
		totalPago.setOpaque(true);
		totalPago.setForeground(new Color(255, 255, 255));
		totalPago.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(totalPago);
		totalPago.setFont(new Font("IBM Plex Sans", Font.BOLD, 17));
		totalPago.setBackground(color);

		total = new JTextField();
		panel_13.add(total);
		total.setEditable(false);
		total.setHorizontalAlignment(SwingConstants.CENTER);
		total.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		total.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Bs.");
		panel_13.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));

		table = new JTable();
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		table.setModel(model);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setGridColor(Color.GRAY);
		table.setSelectionForeground(Color.white);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(color);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		// Configurando el tamaï¿½o de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);
		panel_12.add(pane, BorderLayout.CENTER);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		JPanel panel_19 = new JPanel();
		panel_19.setOpaque(false);
		panel_18.add(panel_19, BorderLayout.SOUTH);
		panel_19.setLayout(new BorderLayout(0, 0));
		JPanel panel_20 = new JPanel();
		panel_20.setOpaque(false);
		panel_19.add(panel_20, BorderLayout.WEST);
		panel_20.setLayout(new GridLayout(0, 4, 30, 20));
		JLabel lblNewLabel_16 = new JLabel("                                   ");
		panel_20.add(lblNewLabel_16);

		btnNewButton_1 = new JButton("Cancelar");
		panel_20.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(JVenta.class.getResource("/icon/cancel.png")));
		btnNewButton_1.setBackground(new Color(250, 116, 112));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));

		btnNewButton = new JButton("Eliminar");
		btnNewButton.setIcon(new ImageIcon(JVenta.class.getResource("/icon/basura.png")));
		panel_20.add(btnNewButton);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnNewButton.setBackground(color);
		btnNewButton.setToolTipText("Elimina un elemento de la tabla");

		btnNewButton_1_1_1_2 = new JButton("Reiniciar");
		panel_20.add(btnNewButton_1_1_1_2);
		btnNewButton_1_1_1_2.setIcon(new ImageIcon(JVenta.class.getResource("/icon/refresh1.png")));
		btnNewButton_1_1_1_2.setForeground(Color.WHITE);
		btnNewButton_1_1_1_2.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnNewButton_1_1_1_2.setBackground(color);
		btnNewButton_1_1_1_2.setToolTipText("Elimina todos los elementos de la tabla");

		Generar = new JButton("Generar Venta");
		panel_19.add(Generar, BorderLayout.EAST);
		Generar.setIcon(new ImageIcon(JVenta.class.getResource("/icon/aceptar.png")));
		Generar.setForeground(Color.WHITE);
		Generar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		Generar.setBackground(color);

		JLabel lblNewLabel_10 = new JLabel(" ");
		panel_19.add(lblNewLabel_10, BorderLayout.SOUTH);

		JLabel lblNewLabel_17 = new JLabel(" ");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panel_19.add(lblNewLabel_17, BorderLayout.CENTER);

	}

	public boolean getCliente(String ci) {
		boolean flag = false;
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select ci, nombre from cliente");
			while (rs.next()) {
				if (rs.getString(1).equals(ci)) {
					cli.setText(rs.getString(2));
					flag = true;
					break;
				}
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return flag;
	}

	public void ingresarProducto(int codVenta, String user) {
		int filas = table.getRowCount();
		for (int i = 0; i < filas; i++) {
			Double imp = Double.parseDouble(table.getValueAt(i, 7).toString());
			int cp = Integer.parseInt(table.getValueAt(i, 0).toString());
			int cant = Integer.parseInt(table.getValueAt(i, 4).toString());
			String tipo;
			if (table.getValueAt(i, 5).toString().equals("Unidad")) {
				tipo = "U";
			} else {
				tipo = "C";
			}
			int admin = Integer.parseInt(funcion.getCodigo(user, "administrador", 2, 1));
			BigDecimal precio = new BigDecimal(Double.parseDouble(table.getValueAt(i, 6).toString()));
			VentaProd venta = new VentaProd(new BigDecimal(imp), cp, codVenta, admin, cant, tipo, precio);

			boolean controlado = false;
			try {
				Conexion con = new Conexion();
				Connection conexion = (Connection) con.Conectar();
				Statement s = (Statement) conexion.createStatement();
				String query = "select controlado from producto where cod = " + cp;
				ResultSet rs = (ResultSet) s.executeQuery(query);
				while (rs.next()) {
					controlado = rs.getBoolean(1);
				}
				conexion.close();

				if (controlado) {
					ArrayList<String> infoExtra = new ArrayList<String>();
					infoExtra.add(table.getValueAt(i, 8).toString());
					infoExtra.add(table.getValueAt(i, 9).toString());
					infoExtra.add(table.getValueAt(i, 10).toString());
					venta.agregarControlado(infoExtra);
				} else {
					venta.agregar();
				}
			} catch (Exception e) {
				System.out.println("Error " + e);
			}
			actualizarStock(cp, tipo, cant);
		}
	}

	public void actualizarStock(int cp, String tipo, int cant) {
		try {
			int stock = getStock(cp, tipo);
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query;
			if (tipo.equals("U")) {
				query = "update producto set stockUnidad = ? where cod = ?";
			} else {
				query = "update producto set stockCaja = ? where cod = ?";
			}
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, stock - cant);
			s.setInt(2, cp);
			s.executeUpdate();

			conexion.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

	}

	public int getStock(int cod, String tipo) {
		int n = 0, aux;
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			if (tipo.equals("U")) {
				aux = 7;
			} else {
				aux = 8;
			}
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto");
			while (rs.next()) {
				if (cod == rs.getInt(1)) {
					n = rs.getInt(aux);
					break;
				}
			}
			conexion.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error " + e);
		}
		return n;

	}

	public void abrirCaja(int prod) {
		try {
			int cod = 0, inicial = 0, contenido = 0, caja = 0;
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto where cod = '" + prod + "';");
			while (rs.next()) {
				cod = rs.getInt(1);
				caja = rs.getInt(8);
				inicial = rs.getInt(7);
				contenido = rs.getInt(12);
			}
			conexion.close();
			if (caja >= 1) {
				actualizarUnidades(cod, inicial, contenido, caja);
				JOptionPane.showMessageDialog(null, "La caja se abrió correctamente!", "", -1,
						new ImageIcon(Acceso.class.getResource("/icon/gifCajita.gif")));
				unidad.setSelected(true);
				cant.setValue(0);
				precio.setText(funcion.getCodigo(Prod.getSelectedItem().toString(), "producto", 2, 4) + "");
				stock.setText(funcion.getCodigo(Prod.getSelectedItem().toString(), "producto", 2, 7) + "");
				color(Integer.parseInt(stock.getText()), stock);
				modelo.setMaximum(Integer.parseInt(stock.getText()));
			} else {
				JOptionPane.showMessageDialog(null, "No se cuenta con cajas para abrir");
			}

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

	public void actualizarUnidades(int cod, int inicial, int contenido, int caja) {
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "update producto set stockUnidad = ?, stockCaja = ? where cod = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, (inicial + contenido));
			s.setInt(2, (caja - 1));
			s.setInt(3, cod);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}

	public void actualizar() {
		int n = 8;
		if (unidad.isSelected()) {
			n = 7;
		}
		stock.setText(funcion.getCodigo(Prod.getSelectedItem().toString(), "producto", 2, n) + "");
		color(Integer.parseInt(stock.getText()), stock);
		modelo.setMaximum(Integer.parseInt(stock.getText()));
	}

	public String getTipo() {
		String cad = "";
		if (unidad.isSelected()) {
			cad = "Unidad";
		} else {
			cad = "Caja";
		}
		return cad;
	}

	public boolean comprobar() {
		boolean flag = false;

		if (!total.getText().isEmpty()) {
			flag = true;
		} else {
			JOptionPane.showMessageDialog(null, "Debe ingresar por lo menos 1 producto a la tabla de venta",
					"Advertencia", 1, null);
		}
		if (CI.getText().isEmpty()) {
			CI.setText("-");
			cli.setText("S/N");
		}
		return flag;
	}

	public void limpiar() {
		fecha.setCalendar(cal);
		CI.setText("");
		cli.setText("");
		cant.setValue(0);
		funcion.limpiarTabla(table);
		total.setText("");
		montoTotal = 0.0;
	}

	public void color(int n, JTextField field) {
		if (n == 0) {
			field.setForeground(Color.RED);
		} else {
			field.setForeground(Color.black);
		}
	}

	public void escribeDatos() throws IOException {
		DataOutputStream fileout = new DataOutputStream(new FileOutputStream("datos.dat", false));
		String nombre, ci, fecha1, total1;
		nombre = cli.getText();
		ci = CI.getText();
		total1 = total.getText();
		SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
		fecha1 = formato.format(fecha.getDate());
		fileout.writeUTF(ci);
		fileout.writeUTF(nombre);
		fileout.writeUTF(fecha1);
		fileout.writeUTF(total1);
		fileout.close();
	}

	public void escribeDatosProd() throws IOException {
		DataOutputStream fileout = new DataOutputStream(new FileOutputStream("datosP.dat", false));
		for (int i = 0; i < model.getRowCount(); i++) {
			int cod = Integer.parseInt(model.getValueAt(i, 0).toString());
			String prod = model.getValueAt(i, 1).toString();
			String pres = model.getValueAt(i, 2).toString();
			String venc = model.getValueAt(i, 3).toString();
			int cant = Integer.parseInt(model.getValueAt(i, 4).toString());
			String tipo = model.getValueAt(i, 5).toString();
			Double precio = Double.parseDouble(model.getValueAt(i, 6).toString());
			Double total1 = cant * precio;
			String medico = "", receta = "", observacion = "";
			System.out.println(model.getColumnCount());
			if (model.getColumnCount() > 8) {
				if (model.getValueAt(i, 8) != null) {
					medico = model.getValueAt(i, 8).toString();
					receta = model.getValueAt(i, 9).toString();
					observacion = model.getValueAt(i, 10).toString();
				}

			}
			fileout.writeInt(cod);
			fileout.writeUTF(prod);
			fileout.writeUTF(pres);
			fileout.writeUTF(venc);
			fileout.writeInt(cant);
			fileout.writeUTF(tipo);
			fileout.writeDouble(precio);
			fileout.writeDouble(total1);
			fileout.writeUTF(medico);
			fileout.writeUTF(receta);
			fileout.writeUTF(observacion);
		}
		fileout.close();
	}

	public void leer() {
		try {
			DataInputStream fileIn;
			fileIn = new DataInputStream(new FileInputStream("datos.dat"));

			while (fileIn.available() != 0) {
				CI.setText(fileIn.readUTF());
				cli.setText(fileIn.readUTF());
				String fechaDat = fileIn.readUTF();
				Date date = Date.valueOf(fechaDat);
				fecha.setDate(date);
				Double tot = Double.parseDouble(fileIn.readUTF());
				total.setText(formatoN.format(tot));
				Double montoTotal = Double.parseDouble(total.getText());
				montoTotal += tot;
			}
			fileIn.close();
			boolean flag = existeControlado();

			DataInputStream fileIn2 = new DataInputStream(new FileInputStream("datosP.dat"));
			while (fileIn2.available() != 0) {
				if(flag ==true) {
					Object[] columnas = { "COD", "PRODUCTO", "PRESENTACIÓN", "VENCIMIENTO", "CANTIDAD", "TIPO",
							"PRECIO", "TOTAL", "MÉDICO", "Nº RECETA", "OBSERVACIONES" };
					model.setColumnIdentifiers(columnas);
					Object[] row = new Object[11];
					row[0] = fileIn2.readInt();
					row[1] = fileIn2.readUTF();
					row[2] = fileIn2.readUTF();
					row[3] = fileIn2.readUTF();
					row[4] = fileIn2.readInt();
					row[5] = fileIn2.readUTF();
					row[6] = formatoN.format(fileIn2.readDouble());
					row[7] = formatoN.format(fileIn2.readDouble());
					row[8] = fileIn2.readUTF();
					row[9] = fileIn2.readUTF();
					row[10] = fileIn2.readUTF();
					model.addRow(row);
				}else {
					Object[] row = new Object[8];
					row[0] = fileIn2.readInt();
					row[1] = fileIn2.readUTF();
					row[2] = fileIn2.readUTF();
					row[3] = fileIn2.readUTF();
					row[4] = fileIn2.readInt();
					row[5] = fileIn2.readUTF();
					row[6] = formatoN.format(fileIn2.readDouble());
					row[7] = formatoN.format(fileIn2.readDouble());
					model.addRow(row);
				}
			}

			fileIn2.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public boolean existeControlado() {
		boolean flag = false;
		try {
			DataInputStream fileIn2 = new DataInputStream(new FileInputStream("datosP.dat"));
			while (fileIn2.available() != 0) {

				int cod = fileIn2.readInt();
				String prod = fileIn2.readUTF();
				String pres = fileIn2.readUTF();
				String venc = fileIn2.readUTF();
				int cant = fileIn2.readInt();
				String tipo = fileIn2.readUTF();
				String precio = formatoN.format(fileIn2.readDouble());
				String total = formatoN.format(fileIn2.readDouble());
				String medico = fileIn2.readUTF();
				String receta = fileIn2.readUTF();
				String obs = fileIn2.readUTF();
				if (!medico.equals("")) {
					flag = true;
					break;
				}

			}

			fileIn2.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return flag;
	}
}
