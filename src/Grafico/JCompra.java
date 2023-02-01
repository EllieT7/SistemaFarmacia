package Grafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Compra;
import Clases.CompraProd;
import Clases.Conexion;
import Clases.Funciones;
import Clases.Laboratorio;
import Clases.Venta;
import Clases.VentaProd;
import Clases.Producto;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.export.HtmlExporterHyperlinkProducerAdapter;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.FlowLayout;

@SuppressWarnings({ "serial", "unused" })
public class JCompra extends JFrame {

	private JPanel contentPane, central;
	private DefaultTableModel model;
	private JTable table;
	private JTextField fact, stock, pres, vendedor, stockC;
	private JComboBox<Laboratorio> Lab;
	private JComboBox<Producto> Prod;
	private JTextField total;
	private Double montoTotal = 0.0;
	private Dimension dim;
	private JSpinner cant, cant1;
	private SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
	private JDateChooser fecha;
	private JDateChooser fechaV;
	private Calendar cal = new GregorianCalendar();
	private Funciones funcion = new Funciones();
	private SpinnerNumberModel modeloSpinner1;
	private SpinnerNumberModel modeloSpinner2;
	private JTextField pu;
	private JTextField pc;
	private JTextField desc;
	

	@SuppressWarnings("deprecation")
	public JCompra(String user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JCompra.class.getResource("/icon/prescription.png")));
		Color color = new Color(101, 166, 240);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(NORMAL);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Object[] columnas = { "COD", "PRODUCTO", "PRESENTACIÓN", "VENCIMIENTO", "CANT.UNIDAD", "CANT.CAJAS","PRECIO UNITARIO","PRECIO CAJA","IMPORTE" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);

		ButtonGroup grupo1 = new ButtonGroup();
		modeloSpinner1 = new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1));
		modeloSpinner2 = new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1));
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

		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		JLabel atras = new JLabel("");
		panel_10.add(atras);
		atras.setIcon(new ImageIcon(JCompra.class.getResource("/icon/atras1.png")));

		JLabel lblNewLabel_11_1 = new JLabel("             ");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_10.add(lblNewLabel_11_1, BorderLayout.NORTH);

		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_10.add(lblNewLabel_12, BorderLayout.WEST);

		JPanel panelCentral = new JPanel();
		panelCentral.setOpaque(false);
		Principal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panelCentral.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_8.add(panel_9, BorderLayout.NORTH);

		JButton btnNewButton_1_1_1_1 = new JButton("Ver registro de compras");
		btnNewButton_1_1_1_1.setPreferredSize(new Dimension(250, 39));
		panel_9.add(btnNewButton_1_1_1_1);
		btnNewButton_1_1_1_1.setIcon(new ImageIcon(JCompra.class.getResource("/icon/lista.png")));
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callRegistroCompra(user);
			}
		});
		btnNewButton_1_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnNewButton_1_1_1_1.setBackground(color);

		JLabel lblNewLabel_25 = new JLabel("    ");
		panel_9.add(lblNewLabel_25);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_8.add(panel_7, BorderLayout.WEST);

		JLabel lblNewLabel_13 = new JLabel("                ");
		panel_7.add(lblNewLabel_13);

		JLabel lblNewLabel_1 = new JLabel("");
		panel_7.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(JCompra.class.getResource("/icon/compraR.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("Registrar compra");
		panel_7.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panelCentral.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_11.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

		JLabel lblNewLabel_20 = new JLabel("               ");
		panel_5.add(lblNewLabel_20);

		JPanel datos = new JPanel();
		datos.setOpaque(false);
		panel_5.add(datos);
		datos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		datos.setBackground(Color.WHITE);
		GridBagLayout gbl_datos = new GridBagLayout();
		gbl_datos.columnWidths = new int[] { 6, 151, 102, 0, 101, 0, 0 };
		gbl_datos.rowHeights = new int[] { 0, 29, 14, 29, 14, 31, 14, 31, 14, 29, 0, 29, 0, 0 };
		gbl_datos.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_datos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,Double.MIN_VALUE };
		datos.setLayout(gbl_datos);

		JLabel lblNewLabel_9 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 0;
		datos.add(lblNewLabel_9, gbc_lblNewLabel_9);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("N\u00BA de factura:");
		GridBagConstraints gbc_lblNewLabel_2_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2_1_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1_1.gridwidth = 2;
		gbc_lblNewLabel_2_1_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1_1.gridy = 1;
		datos.add(lblNewLabel_2_1_1_1, gbc_lblNewLabel_2_1_1_1);
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		fact = new JTextField();
		GridBagConstraints gbc_fact = new GridBagConstraints();
		gbc_fact.fill = GridBagConstraints.HORIZONTAL;
		gbc_fact.anchor = GridBagConstraints.NORTH;
		gbc_fact.insets = new Insets(0, 0, 5, 5);
		gbc_fact.gridwidth = 3;
		gbc_fact.gridx = 2;
		gbc_fact.gridy = 1;
		datos.add(fact, gbc_fact);
		fact.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		fact.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		datos.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Fecha:");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridwidth = 2;
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 3;
		datos.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		fecha = new JDateChooser();
		fecha.getCalendarButton().setBackground(Color.WHITE);
		fecha.getCalendarButton().setIcon(new ImageIcon(JCompra.class.getResource("/icon/calP.png")));
		fecha.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		fecha.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fecha.getJCalendar().setWeekOfYearVisible(false);
		fecha.getJCalendar().setDecorationBackgroundColor(color);
		fecha.getJCalendar().setWeekdayForeground(Color.black);
		fecha.getJCalendar().setTodayButtonVisible(true);
		fecha.getJCalendar().setBackground(Color.WHITE);
		GridBagConstraints gbc_fecha = new GridBagConstraints();
		gbc_fecha.fill = GridBagConstraints.BOTH;
		gbc_fecha.insets = new Insets(0, 0, 5, 5);
		gbc_fecha.gridwidth = 3;
		gbc_fecha.gridx = 2;
		gbc_fecha.gridy = 3;
		datos.add(fecha, gbc_fecha);
		fecha.setCalendar(cal);

		JLabel lblNewLabel_4 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		datos.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_2_1_2 = new JLabel("Laboratorio:");
		GridBagConstraints gbc_lblNewLabel_2_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_2.gridwidth = 2;
		gbc_lblNewLabel_2_1_2.gridx = 0;
		gbc_lblNewLabel_2_1_2.gridy = 5;
		datos.add(lblNewLabel_2_1_2, gbc_lblNewLabel_2_1_2);
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		Lab = new JComboBox<Laboratorio>();
		GridBagConstraints gbc_Lab = new GridBagConstraints();
		gbc_Lab.anchor = GridBagConstraints.NORTH;
		gbc_Lab.insets = new Insets(0, 0, 5, 5);
		gbc_Lab.gridwidth = 3;
		gbc_Lab.gridx = 2;
		gbc_Lab.gridy = 5;
		datos.add(Lab, gbc_Lab);
		Lab.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		funcion.datoLab(Lab);
		AutoCompleteDecorator.decorate(Lab);
		Lab.setSelectedIndex(0);
		

		JLabel lblNewLabel_5 = new JLabel("  ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		datos.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_2_1_1 = new JLabel("Producto:");
		GridBagConstraints gbc_lblNewLabel_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_1.gridwidth = 2;
		gbc_lblNewLabel_2_1_1.gridx = 0;
		gbc_lblNewLabel_2_1_1.gridy = 7;
		datos.add(lblNewLabel_2_1_1, gbc_lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));

		Prod = new JComboBox<Producto>();
		Prod.setPreferredSize(new Dimension(300, 30));
		Prod.setEnabled(false);
		GridBagConstraints gbc_Prod = new GridBagConstraints();
		gbc_Prod.anchor = GridBagConstraints.NORTH;
		gbc_Prod.insets = new Insets(0, 0, 5, 5);
		gbc_Prod.gridwidth = 3;
		gbc_Prod.gridx = 2;
		gbc_Prod.gridy = 7;
		datos.add(Prod, gbc_Prod);
		Prod.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		AutoCompleteDecorator.decorate(Prod);
		Prod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Prod.getItemCount() > 0) {
					Producto pro = (Producto) Prod.getSelectedItem();
					Prod.setEnabled(true);
					pres.setText(funcion.getDato("presentacion", 2, pro.getPres()));
					stock.setText(pro.getStockU()+"");
					stockC.setText(pro.getStockC()+"");
					cant.setValue(0);
					cant1.setValue(0);
				}
			}
		});

		JLabel lblNewLabel_7 = new JLabel("  ");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		datos.add(lblNewLabel_7, gbc_lblNewLabel_7);

		JLabel lblNewLabel_2_1_3 = new JLabel("Presentaci\u00F3n:");
		GridBagConstraints gbc_lblNewLabel_2_1_3 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_3.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2_1_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2_1_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1_3.gridwidth = 2;
		gbc_lblNewLabel_2_1_3.gridx = 0;
		gbc_lblNewLabel_2_1_3.gridy = 9;
		datos.add(lblNewLabel_2_1_3, gbc_lblNewLabel_2_1_3);
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));
		pres = new JTextField();
		pres.setEditable(false);
		GridBagConstraints gbc_pres = new GridBagConstraints();
		gbc_pres.fill = GridBagConstraints.HORIZONTAL;
		gbc_pres.anchor = GridBagConstraints.NORTH;
		gbc_pres.insets = new Insets(0, 0, 5, 5);
		gbc_pres.gridwidth = 3;
		gbc_pres.gridx = 2;
		gbc_pres.gridy = 9;
		datos.add(pres, gbc_pres);
		pres.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		pres.setColumns(10);

		JLabel lblNewLabel_22 = new JLabel("   ");
		GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints();
		gbc_lblNewLabel_22.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_22.gridx = 5;
		gbc_lblNewLabel_22.gridy = 10;
		datos.add(lblNewLabel_22, gbc_lblNewLabel_22);

		JLabel lblNewLabel_6 = new JLabel("Stock unidades:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 11;
		datos.add(lblNewLabel_6, gbc_lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		stock = new JTextField();
		stock.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_stock = new GridBagConstraints();
		gbc_stock.fill = GridBagConstraints.HORIZONTAL;
		gbc_stock.anchor = GridBagConstraints.NORTH;
		gbc_stock.insets = new Insets(0, 0, 5, 5);
		gbc_stock.gridx = 2;
		gbc_stock.gridy = 11;
		datos.add(stock, gbc_stock);
		stock.setBackground(UIManager.getColor("CheckBox.highlight"));
		stock.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		stock.setEditable(false);
		stock.setColumns(5);

		JLabel lblNewLabel_6_1 = new JLabel("Stock cajas:");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
		gbc_lblNewLabel_6_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6_1.gridx = 3;
		gbc_lblNewLabel_6_1.gridy = 11;
		datos.add(lblNewLabel_6_1, gbc_lblNewLabel_6_1);
		stockC = new JTextField();
		stockC.setHorizontalAlignment(SwingConstants.RIGHT);
		stockC.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		stockC.setEditable(false);
		stockC.setColumns(5);
		stockC.setBackground(UIManager.getColor("Button.highlight"));
		GridBagConstraints gbc_stockC = new GridBagConstraints();
		gbc_stockC.fill = GridBagConstraints.HORIZONTAL;
		gbc_stockC.insets = new Insets(0, 0, 5, 5);
		gbc_stockC.anchor = GridBagConstraints.NORTH;
		gbc_stockC.gridx = 4;
		gbc_stockC.gridy = 11;
		datos.add(stockC, gbc_stockC);

		JLabel lblNewLabel_23 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_23 = new GridBagConstraints();
		gbc_lblNewLabel_23.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_23.gridx = 1;
		gbc_lblNewLabel_23.gridy = 12;
		datos.add(lblNewLabel_23, gbc_lblNewLabel_23);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_5.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_15 = new JLabel(" ");
		lblNewLabel_15.setFont(new Font("Snap ITC", Font.PLAIN, 99));
		panel_4.add(lblNewLabel_15, BorderLayout.CENTER);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_4.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_3.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel_2_1_3_1 = new JLabel("Nombre del vendedor:");
		panel_1.add(lblNewLabel_2_1_3_1);
		lblNewLabel_2_1_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_3_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));
		vendedor = new JTextField();
		panel_1.add(vendedor);
		vendedor.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		vendedor.setColumns(10);

		Lab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String labo;
				if (Lab.getSelectedItem().toString() == null) {
					labo = "";
				} else {
					labo = Lab.getSelectedItem().toString();
				}
				vendedor.setText(funcion.getDato("laboratorio", 2, Integer.parseInt(funcion.getCodigo(labo, "laboratorio", 4, 1))));
				datoP(Prod, Integer.parseInt(funcion.getCodigo(labo, "laboratorio", 4, 1)));
			}
		});
		
		
		JPanel panel_1_1 = new JPanel();
		panel_3.add(panel_1_1, BorderLayout.CENTER);
		panel_1_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setLayout(new MigLayout("", "[94px,grow][7px][][69px,grow][][26px]", "[][31px][][31.00][][grow]"));

		JLabel cantidad = new JLabel("Cantidad");
		cantidad.setForeground(color);
		cantidad.setVerticalAlignment(SwingConstants.BOTTOM);
		cantidad.setHorizontalAlignment(SwingConstants.CENTER);
		cantidad.setFont(new Font("IBM Plex Sans", Font.BOLD, 19));
		panel_1_1.add(cantidad, "cell 0 0 6 1");

		JLabel lblNewLabel_2_2 = new JLabel("Unidad:");
		panel_1_1.add(lblNewLabel_2_2, "flowx,cell 0 1,alignx left,aligny center");
		lblNewLabel_2_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 19));

		JLabel lblNewLabel_2_2_1 = new JLabel("         Caja:");
		lblNewLabel_2_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 19));
		panel_1_1.add(lblNewLabel_2_2_1, "cell 2 1");

		cant1 = new JSpinner();
		cant1.setModel(modeloSpinner1);
		panel_1_1.add(cant1, "cell 3 1 3 1,grow");
		cant1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		
		JLabel lblNewLabel_27 = new JLabel(" ");
		panel_1_1.add(lblNewLabel_27, "cell 0 2");
		
		JLabel lblNewLabel_19 = new JLabel("P. Unidad: ");
		lblNewLabel_19.setFont(new Font("IBM Plex Sans", Font.PLAIN, 19));
		panel_1_1.add(lblNewLabel_19, "flowx,cell 0 3");
		
		pu = new JTextField("0");
		pu.setHorizontalAlignment(SwingConstants.RIGHT);
		pu.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		panel_1_1.add(pu, "cell 0 3,grow");
		pu.setColumns(10);
		
		JLabel lblNewLabel_26 = new JLabel("P. Caja: ");
		lblNewLabel_26.setFont(new Font("IBM Plex Sans", Font.PLAIN, 19));
		panel_1_1.add(lblNewLabel_26, "cell 2 3,alignx trailing");
		
		pc = new JTextField("0");
		pc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		pc.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1_1.add(pc, "cell 3 3 3 1,grow");
		pc.setColumns(10);
		
		JLabel lblNewLabel_27_1 = new JLabel(" ");
		lblNewLabel_27_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		panel_1_1.add(lblNewLabel_27_1, "cell 0 4");

		JLabel lblNewLabel_3 = new JLabel("Fecha de vencimiento:");
		panel_1_1.add(lblNewLabel_3, "cell 0 5,alignx center,aligny center");
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 19));

		cant = new JSpinner();
		cant.setModel(modeloSpinner2);
		panel_1_1.add(cant, "cell 0 1,grow");
		cant.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));

		fechaV = new JDateChooser();
		fechaV.getCalendarButton().setIcon(new ImageIcon(JCompra.class.getResource("/icon/calP.png")));
		fechaV.getCalendarButton().setBackground(Color.WHITE);

		fechaV.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		fechaV.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fechaV.getJCalendar().setWeekOfYearVisible(false);
		fechaV.getJCalendar().setDecorationBackgroundColor(color);
		fechaV.getJCalendar().setWeekdayForeground(Color.black);
		fechaV.getJCalendar().setTodayButtonVisible(true);
		fechaV.getJCalendar().setBackground(Color.WHITE);
		fechaV.setBounds(381, 129, 324, 31);
		panel_1_1.add(fechaV, "cell 2 5 4 1,growx,aligny center");

		JLabel lblNewLabel_21_1 = new JLabel("                    ");
		panel_5.add(lblNewLabel_21_1);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_5.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_11 = new JLabel("New label");
		panel_6.add(lblNewLabel_11);

		JButton btnNewButton_3 = new JButton("Agregar");
		panel_6.add(btnNewButton_3);
		btnNewButton_3.setIcon(new ImageIcon(JCompra.class.getResource("/icon/agregar.png")));
		btnNewButton_3.setBackground(color);
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("IBM Plex Sans", Font.BOLD, 17));

		JLabel lblNewLabel_10 = new JLabel(" ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 99));
		panel_6.add(lblNewLabel_10, BorderLayout.NORTH);

		JLabel lblNewLabel_14 = new JLabel(" ");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 76));
		panel_6.add(lblNewLabel_14, BorderLayout.EAST);

		JPanel panel_13 = new JPanel();
		panel_13.setOpaque(false);
		panel_11.add(panel_13, BorderLayout.SOUTH);
		panel_13.setLayout(new BorderLayout(0, 0));

		central = new JPanel();
		central.setOpaque(false);
		panel_11.add(central, BorderLayout.CENTER);
		central.setLayout(new BorderLayout(0, 0));
		JLabel img = new JLabel("");
		img.setIcon(new ImageIcon(JCompra.class.getResource("/icon/compra1.jpg")));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(1633, 388, 248, 271);
		central.add(img, BorderLayout.EAST);
		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel_13.add(panel_12, BorderLayout.WEST);
		panel_12.setLayout(new GridLayout(0, 4, 40, 0));

		JLabel lblNewLabel_16 = new JLabel("  ");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_12.add(lblNewLabel_16);

		JButton btnNewButton_1 = new JButton("Cancelar");
		panel_12.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(JCompra.class.getResource("/icon/cancel.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callMenu(user);
			}
		});
		btnNewButton_1.setBackground(new Color(250, 116, 112));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.setToolTipText("Elimina un elemento de la tabla");
		btnNewButton.setBackground(color);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(JCompra.class.getResource("/icon/basura.png")));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		panel_12.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = table.getSelectedRow();
					model.removeRow(index);
					actualizarTotal();
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione una fila de la tabla para eliminar", "Advertencia", 2, null);
				}
					
			}
		});

		JButton btnNewButton_1_1_1_2 = new JButton("Reiniciar");
		btnNewButton_1_1_1_2.setToolTipText("Elimina todos los elementos de la tabla");
		panel_12.add(btnNewButton_1_1_1_2);
		btnNewButton_1_1_1_2.setIcon(new ImageIcon(JCompra.class.getResource("/icon/refresh1.png")));
		btnNewButton_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.limpiarTabla(table);
				actualizarTotal();
				cant.setValue(0);
				cant1.setValue(0);
			}
		});
		btnNewButton_1_1_1_2.setForeground(Color.WHITE);
		btnNewButton_1_1_1_2.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnNewButton_1_1_1_2.setBackground(color);

		JPanel panel_14 = new JPanel();
		panel_14.setOpaque(false);
		panel_13.add(panel_14, BorderLayout.EAST);
		panel_14.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_17 = new JLabel("           ");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel_13.add(lblNewLabel_17, BorderLayout.SOUTH);

		JPanel panel_17 = new JPanel();
		panel_17.setOpaque(false);
		panel_13.add(panel_17, BorderLayout.EAST);

		JButton Generar = new JButton("Registrar");
		Generar.setPreferredSize(new Dimension(150, 50));
		panel_17.add(Generar);
		Generar.setIcon(new ImageIcon(JCompra.class.getResource("/icon/aceptar.png")));
		Generar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobar()) {
					try {
						Producto pro = (Producto) Prod.getSelectedItem();
						Laboratorio lab = (Laboratorio) Lab.getSelectedItem();
						int codLab = Integer.parseInt(lab.getCod());
						String nfact = fact.getText();
						String date = formato.format(fecha.getDate());
						BigDecimal imp = new BigDecimal(Double.parseDouble(total.getText()));
						BigDecimal descuento = new BigDecimal(Double.parseDouble(desc.getText()));
						Compra compra = new Compra(nfact, date, codLab, imp,descuento );
						compra.agregar();
						int cod = funcion.getLastCod("compra");
						ingresarProducto(cod, user);
						limpiar();
						funcion.limpiarTabla(table);
						JOptionPane.showMessageDialog(null, "Compra registrada correctamente!");
						pro = (Producto) Prod.getSelectedItem();
						stock.setText(funcion.getCodigo(pro.getCod()+"", "producto", 1, 7));
						stockC.setText(funcion.getCodigo(pro.getCod()+"", "producto", 1, 8));

					}catch (NumberFormatException error) {
						JOptionPane.showMessageDialog(null,"El importe ingresado deben ser numérico\n y debe llevar un punto decimal", "Advertencia", 2, null);
					}
					
				}

			}
		});
		Generar.setForeground(Color.WHITE);
		Generar.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		Generar.setBackground(color);

		JLabel lblNewLabel_18 = new JLabel("            ");
		panel_17.add(lblNewLabel_18);

		JPanel panel_15 = new JPanel();
		panel_15.setOpaque(false);
		central.add(panel_15, BorderLayout.CENTER);

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
		// Configurando el tamaño de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		panel_15.setLayout(new BorderLayout(0, 0));

		JPanel panel_16 = new JPanel();
		panel_16.setOpaque(false);
		panel_15.add(panel_16, BorderLayout.SOUTH);
		FlowLayout flowLayout_1 = (FlowLayout) panel_16.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(101, 166, 240));
		panel_16.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDescuento = new JLabel("Descuento: ");
		lblDescuento.setOpaque(true);
		lblDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescuento.setForeground(Color.WHITE);
		lblDescuento.setFont(new Font("IBM Plex Sans", Font.BOLD, 17));
		lblDescuento.setBackground(new Color(101, 166, 240));
		panel_2_1.add(lblDescuento);
		
		desc = new JTextField();
		desc.setText("0.0");
		desc.setHorizontalAlignment(SwingConstants.RIGHT);
		desc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		desc.setColumns(10);
		panel_16.add(desc);
		
		JLabel lblNewLabel_8_1 = new JLabel("%         ");
		lblNewLabel_8_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		panel_16.add(lblNewLabel_8_1);

		JPanel panel_2 = new JPanel();
		panel_16.add(panel_2);
		panel_2.setBackground(color);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel totalPago = new JLabel("Importe Total:");
		totalPago.setOpaque(true);
		totalPago.setForeground(new Color(255, 255, 255));
		totalPago.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(totalPago);
		totalPago.setFont(new Font("IBM Plex Sans", Font.BOLD, 17));
		totalPago.setBackground(color);

		total = new JTextField();
		total.setEditable(false);
		panel_16.add(total);
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		total.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		total.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Bs.");
		panel_16.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));

		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);
		panel_15.add(pane);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);

		JLabel lblNewLabel_21 = new JLabel("                                    ");
		panel_15.add(lblNewLabel_21, BorderLayout.WEST);

		JLabel lblNewLabel_24 = new JLabel("   ");
		lblNewLabel_24.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_15.add(lblNewLabel_24, BorderLayout.NORTH);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(cant.getValue().toString()) == 0
						&& Integer.parseInt(cant1.getValue().toString()) == 0) {
					JOptionPane.showMessageDialog(null, "Ingrese una cantidad mayor a 0", "Advertencia", 1, null);
				} else if (fechaV.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una fecha de vencimiento", "Advertencia", 1, null);
				} else {
					Object[] row = new Object[9];
					Producto pro = (Producto) Prod.getSelectedItem();
					int c1 = Integer.parseInt(cant.getValue()+""),c2 = Integer.parseInt(cant1.getValue()+"");
					
					row[0] = pro.getCod();
					row[1] = pro.getNombre();
					row[2] = pres.getText();
					row[3] = formato.format(fechaV.getDate());
					row[4] = c1;
					row[5] = c2;
					try {
						Double p1=Double.parseDouble(pu.getText()) , p2= Double.parseDouble(pc.getText());
						row[6] = p1;
						row[7] = p2;
						row[8] = ((p1*c1)+(p2*c2));
						model.addRow(row);
						actualizarTotal();
					}catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Los precios ingresados deben ser numéricos\n y deben llevar un punto decimal", "Advertencia", 1, null);				
					}
					
					
				}
				cant.setValue(0);
				cant1.setValue(0);
				pu.setText("0");
				pc.setText("0");
			}
		});
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callMenu(user);
			}
		});
		
		Laboratorio lab = (Laboratorio) Lab.getSelectedItem();
		Lab.setPreferredSize(new Dimension(300, 30));
		vendedor.setText(funcion.getDato("laboratorio", 2, Integer.parseInt(lab.getCod())));
		datoP(Prod, Integer.parseInt(lab.getCod()));
		actualizarTotal();

	}
	public void actualizarTotal() {
		Double tl = 0.0;
		for(int i = 0; i<model.getRowCount();i++) {
			tl+=Double.parseDouble(model.getValueAt(i, 8)+"");
		}
		total.setText(tl+"");
	}
	public void datoP(JComboBox<Producto> cb, int codLab) {
		try {
			cb.removeAllItems();
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery(
					"select * from producto where laboratorio = " + codLab + " and activo = true order by nombre");
			while (rs.next()) {
				Producto dato = new Producto(rs.getInt(1), rs.getInt(7), rs.getInt(8), rs.getString(2), rs.getInt(9),
						rs.getString(3), rs.getBigDecimal(4), rs.getBigDecimal(5), rs.getDate(6)+"", rs.getInt(10), rs.getInt(12));
				cb.addItem(dato);
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

	public void ingresarProducto(int codCompra, String user) {
		int filas = table.getRowCount();
		for (int i = 0; i < filas; i++) {
			int cp = Integer.parseInt(table.getValueAt(i, 0).toString());
			int cantU = Integer.parseInt(table.getValueAt(i, 4).toString());
			int cantC = Integer.parseInt(table.getValueAt(i, 5).toString());
			Date date = Date.valueOf(table.getValueAt(i, 3).toString());
			int admin =Integer.parseInt(funcion.getCodigo(user, "administrador", 2, 1));
			BigDecimal pUni = new BigDecimal(Double.parseDouble(table.getValueAt(i, 6)+""));
			BigDecimal pCaja = new BigDecimal(Double.parseDouble(table.getValueAt(i, 7)+""));
			
			CompraProd compra = new CompraProd(cp, codCompra, admin , cantU, cantC, date,pUni,pCaja);
			compra.agregar();
			actualizarStock(cp, cantU, cantC);
			compra.actualizarVenc();
		}
	}

	public void actualizarStock(int cp, int cantU, int cantC) {
		try {
			int stockU = getStock(cp, 7);
			int stockC = getStock(cp, 8);
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			PreparedStatement s;
			String query = "update producto set stockUnidad = ?, stockCaja = ? where cod = ?";
			s = (PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, stockU + cantU);
			s.setInt(2, stockC + cantC);
			s.setInt(3, cp);
			s.executeUpdate();
			conexion.close();
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}

	}
	
	public int getStock(int cod, int columna) {
		int n = 0;
		try {
			Conexion con = new Conexion();
			Connection conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto");
			while (rs.next()) {
				if (cod == rs.getInt(1)) {
					n = rs.getInt(columna);
					break;
				}
			}
			conexion.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error " + e);
		}
		return n;

	}

	public void limpiar() {
		fact.setText("");
		fecha.setCalendar(cal);
		fechaV.setCalendar(cal);
		cant1.setValue(0);
		cant.setValue(0);
		total.setText("");
		vendedor.setText("");
		montoTotal = 0.0;
		desc.setText("0");
	}

	public boolean comprobar() {
		boolean flag = false;
		if (!fact.getText().isEmpty()) {
			if (!total.getText().isEmpty()) {
				flag = true;
			} else {
				JOptionPane.showMessageDialog(null, "Debe ingresar el importe de la compra", "Advertencia", 1, null);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados", "Advertencia", 1, null);
		}
		return flag;
	}
}
