package Grafico;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import Clases.Conexion;
import Clases.Funciones;
import Clases.Grafica;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;
	private Funciones funcion =  new Funciones();
	private JButton usuarios, plan, compra;
	private JTextArea hoy;

	public Menu(String user) {
		//Declarando el color principal de la interfaz
		Color fondo =  new Color(250,250,250);
		
		//Inicializando y configurando frame
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/icon/prescription.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Inicializando la fecha actual
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("EEEE-dd-MMMM-YYYY");
		String fecha = format.format(date);
		String [] fecha1 = fecha.toString().split("-");//Split para obtener los datos de la fecha
		
		//Declaracion ScrollPane de la ventana principal
		JScrollPane scrollPanePrincipal = new JScrollPane();
		contentPane.add(scrollPanePrincipal);
		
		//Declaracion de paneles y jlabels
		JPanel principal = new JPanel();
		scrollPanePrincipal.setViewportView(principal);
		principal.setBackground(Color.WHITE);
		principal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSup = new JPanel();
		principal.add(panelSup, BorderLayout.NORTH);
		panelSup.setOpaque(false);
		panelSup.setLayout(new BorderLayout(0, 0));
		
		JPanel SupIzq = new JPanel();
		SupIzq.setOpaque(false);
		panelSup.add(SupIzq, BorderLayout.WEST);
		
		JLabel lblNewLabel_13 = new JLabel("                 ");
		SupIzq.add(lblNewLabel_13);
		
		JLabel lblNewLabel_8 = new JLabel("");
		SupIzq.add(lblNewLabel_8);
		lblNewLabel_8.setIcon(new ImageIcon(Menu.class.getResource("/icon/symbol1.png")));
		
		JLabel lblNewLabel_3 = new JLabel("Inicio");
		SupIzq.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("IBM Plex Sans", Font.BOLD, 54));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panelSup.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel btnNewButton_1_2 = new JLabel("                  ");
		panel_1.add(btnNewButton_1_2, BorderLayout.EAST);
		
		JPanel SupDer = new JPanel();
		SupDer.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) SupDer.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setVgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.add(SupDer, BorderLayout.CENTER);
		
		JLabel lblNewLabel_10_1 = new JLabel("    ");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 41));
		panelSup.add(lblNewLabel_10_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_11 = new JLabel("                       ");
		principal.add(lblNewLabel_11, BorderLayout.WEST);
		
		JLabel graficas = new JLabel("<html><u>Ver gr\u00E1fica de ventas mensuales</u></html>");
		principal.add(graficas, BorderLayout.SOUTH);
		graficas.setHorizontalAlignment(SwingConstants.RIGHT);
		graficas.setFont(new Font("IBM Plex Sans", Font.PLAIN, 15));
		
		JPanel panelCentral = new JPanel();
		principal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setOpaque(false);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel categoriasT = new JPanel();
		categoriasT.setMaximumSize(new Dimension(1000, 1000));
		categoriasT.setOpaque(false);
		panelCentral.add(categoriasT, BorderLayout.EAST);
		categoriasT.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione una categor\u00EDa:");
		categoriasT.add(lblNewLabel_1, BorderLayout.NORTH);
		lblNewLabel_1.setFont(new Font("IBM Plex Sans Medium", Font.PLAIN, 24));
		
		JPanel categorias = new JPanel();
		categorias.setOpaque(false);
		categoriasT.add(categorias, BorderLayout.CENTER);
		categorias.setLayout(new GridLayout(2, 3, 30, 20));
		
		JPanel producto = new JPanel();
		producto.setOpaque(false);
		categorias.add(producto);
		producto.setLayout(new BorderLayout(0, 0));
		
		//Encabezado de bienvenida
		JLabel lblNewLabel = new JLabel("Bienvenid@ "+user+", hoy es "+fecha1[0]+" "+fecha1[1]+" de "+fecha1[2]+" de "+fecha1[3]);
		SupDer.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		
		
		//Declarando y configurando el boton usuarios
		usuarios = new JButton("Administrar usuarios");
		SupDer.add(usuarios);
		usuarios.setIcon(new ImageIcon(Menu.class.getResource("/icon/user.png")));
		usuarios.setForeground(new Color(255, 255, 255));
		usuarios.setBackground(new Color(0, 197, 227));
		usuarios.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		
		//Declarando y configurando el boton cerrarSesion 
		JButton cerrarSesion = new JButton("Cerrar sesi\u00F3n");
		SupDer.add(cerrarSesion);
		cerrarSesion.setIcon(new ImageIcon(Menu.class.getResource("/icon/salir (2).png")));
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callLogin();//Llamando a login
			}
		});
		cerrarSesion.setForeground(new Color(255, 255, 255));
		cerrarSesion.setBackground(new Color(0, 197, 227));
		cerrarSesion.setFont(new Font("IBM Plex Sans", Font.BOLD, 17));
		
		//Declarando y configurando el boton para ingresar a la seccion de productos
		JButton pro = new JButton("");
		producto.add(pro, BorderLayout.NORTH);
		pro.setBackground(new Color(250,209,169));
		pro.setBorder(UIManager.getBorder("Button.border"));
		pro.setIcon(new ImageIcon(Menu.class.getResource("/icon/producto.jpg")));
		pro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callProducto(user);//Llamando a la interfaz JProducto
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		producto.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTextArea produc = new JTextArea();
		produc.setBorder(null);
		produc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		produc.setForeground(Color.DARK_GRAY);
		panel_2.add(produc, BorderLayout.CENTER);
		produc.setLineWrap(true);
		produc.setText("Agregue, modifique y elimine productos de la lista; mediante una b\u00FAsqueda r\u00E1pida visualice la informaci\u00F3n de cada producto.");
		produc.setWrapStyleWord(true);
		
		JLabel lblNewLabel_2 = new JLabel("Productos");
		panel_2.add(lblNewLabel_2, BorderLayout.NORTH);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 23));
		producto.revalidate();
		JPanel cliente = new JPanel();
		cliente.setOpaque(false);
		categorias.add(cliente);
		cliente.setLayout(new BorderLayout(0, 0));
		
		JButton cli = new JButton("");
		cliente.add(cli, BorderLayout.NORTH);
		cli.setBackground(new Color(255, 182, 193));
		cli.setIcon(new ImageIcon(Menu.class.getResource("/icon/cliente.jpg")));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setOpaque(false);
		cliente.add(panel_2_1, BorderLayout.CENTER);
		panel_2_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2_1 = new JLabel("Clientes");
		lblNewLabel_2_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 23));
		panel_2_1.add(lblNewLabel_2_1, BorderLayout.NORTH);
		
		JTextArea txtrAgregueModifiqueY = new JTextArea();
		txtrAgregueModifiqueY.setWrapStyleWord(true);
		txtrAgregueModifiqueY.setText("Agregue, modifique y elimine clientes de la lista; mediante una b\u00FAsqueda r\u00E1pida visualice la informaci\u00F3n de cada cliente.");
		txtrAgregueModifiqueY.setLineWrap(true);
		txtrAgregueModifiqueY.setForeground(Color.DARK_GRAY);
		txtrAgregueModifiqueY.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		txtrAgregueModifiqueY.setBorder(null);
		panel_2_1.add(txtrAgregueModifiqueY, BorderLayout.CENTER);
		
		JPanel laboratorio = new JPanel();
		laboratorio.setOpaque(false);
		categorias.add(laboratorio);
		laboratorio.setLayout(new BorderLayout(0, 0));
		
		JButton lab = new JButton("");
		laboratorio.add(lab, BorderLayout.NORTH);
		lab.setBackground(new Color(213,188,219));
		lab.setIcon(new ImageIcon(Menu.class.getResource("/icon/lab.jpg")));
		
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		laboratorio.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Laboratorios");
		lblNewLabel_2_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 23));
		panel_4.add(lblNewLabel_2_1_1, BorderLayout.NORTH);
		
		JTextArea txtrAgregueModifiqueY_1 = new JTextArea();
		txtrAgregueModifiqueY_1.setWrapStyleWord(true);
		txtrAgregueModifiqueY_1.setText("Agregue, modifique y elimine laboratorios de la lista; mediante una b\u00FAsqueda r\u00E1pida visualice la informaci\u00F3n de cada laboratorio.");
		txtrAgregueModifiqueY_1.setLineWrap(true);
		txtrAgregueModifiqueY_1.setForeground(Color.DARK_GRAY);
		txtrAgregueModifiqueY_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		txtrAgregueModifiqueY_1.setBorder(null);
		panel_4.add(txtrAgregueModifiqueY_1, BorderLayout.CENTER);
		
		JPanel ventas = new JPanel();
		ventas.setOpaque(false);
		categorias.add(ventas);
		ventas.setLayout(new BorderLayout(0, 0));
		
		JButton venta = new JButton("");
		venta.setBackground(new Color(193,212,241));
		ventas.add(venta, BorderLayout.NORTH);
		venta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callVenta(user);
			}
		});
		venta.setIcon(new ImageIcon(Menu.class.getResource("/icon/venta.jpg")));
		
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		ventas.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2_1_4 = new JLabel("Ventas");
		lblNewLabel_2_1_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_4.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 23));
		panel_7.add(lblNewLabel_2_1_4, BorderLayout.NORTH);
		
		JTextArea txtrRegistreLasVentas = new JTextArea();
		txtrRegistreLasVentas.setWrapStyleWord(true);
		txtrRegistreLasVentas.setText("Registre las ventas y visualice una lista de las ventas realizadas anualmente.");
		txtrRegistreLasVentas.setLineWrap(true);
		txtrRegistreLasVentas.setForeground(Color.DARK_GRAY);
		txtrRegistreLasVentas.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		txtrRegistreLasVentas.setBorder(null);
		panel_7.add(txtrRegistreLasVentas, BorderLayout.CENTER);
		
		JPanel compras = new JPanel();
		compras.setOpaque(false);
		categorias.add(compras);
		compras.setLayout(new BorderLayout(0, 0));
		
		compra = new JButton("");
		compra.setBackground(new Color (181,235,233));
		compras.add(compra, BorderLayout.NORTH);
		compra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callCompra(user);
			}
		});
		compra.setIcon(new ImageIcon(Menu.class.getResource("/icon/compra.png")));
		
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		compras.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Compras");
		lblNewLabel_2_1_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_3.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 23));
		panel_6.add(lblNewLabel_2_1_3, BorderLayout.NORTH);
		
		JTextArea txtrRegistreLasCompras = new JTextArea();
		txtrRegistreLasCompras.setWrapStyleWord(true);
		txtrRegistreLasCompras.setText("Registre las compras de abastecimiento y visualice una lista de las compras realizadas anualmente.");
		txtrRegistreLasCompras.setLineWrap(true);
		txtrRegistreLasCompras.setForeground(Color.DARK_GRAY);
		txtrRegistreLasCompras.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		txtrRegistreLasCompras.setBorder(null);
		panel_6.add(txtrRegistreLasCompras, BorderLayout.CENTER);
		
		JPanel planificacion = new JPanel();
		planificacion.setOpaque(false);
		categorias.add(planificacion);
		planificacion.setLayout(new BorderLayout(0, 0));
		
		plan = new JButton("");
		plan.setBackground(new Color(195,227,177));
		planificacion.add(plan, BorderLayout.NORTH);
		plan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callPlan(user);
			}
		});
		plan.setIcon(new ImageIcon(Menu.class.getResource("/icon/plan.jpg")));
		
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		planificacion.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Planificaci\u00F3n");
		lblNewLabel_2_1_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_2.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 23));
		panel_5.add(lblNewLabel_2_1_2, BorderLayout.NORTH);
		
		JTextArea txtrAgregueModifiqueY_2 = new JTextArea();
		txtrAgregueModifiqueY_2.setWrapStyleWord(true);
		txtrAgregueModifiqueY_2.setText("Agregue, modifique y elimine tareas de la lista, visulice un reporte que contenga las tareas asignadas mensualmente.");
		txtrAgregueModifiqueY_2.setLineWrap(true);
		txtrAgregueModifiqueY_2.setForeground(Color.DARK_GRAY);
		txtrAgregueModifiqueY_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		txtrAgregueModifiqueY_2.setBorder(null);
		panel_5.add(txtrAgregueModifiqueY_2, BorderLayout.CENTER);
		
		JLabel lblNewLabel_14 = new JLabel("  ");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 50));
		categoriasT.add(lblNewLabel_14, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panelCentral.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_10 = new JLabel("                         ");
		panel.add(lblNewLabel_10, BorderLayout.EAST);
		
		JPanel reporteT = new JPanel();
		panel.add(reporteT);
		reporteT.setMaximumSize(new Dimension(1000, 1000));
		reporteT.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Reporte diario");
		reporteT.add(lblNewLabel_4, BorderLayout.NORTH);
		lblNewLabel_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_4.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 23));
		
		JPanel reporte = new JPanel();
		reporteT.add(reporte, BorderLayout.CENTER);
		reporte.setBackground(Color.WHITE);
		reporte.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		reporte.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setOpaque(true);
		
		hoy = new JTextArea();
		scrollPane.setViewportView(hoy);
		hoy.setBackground(fondo);
		hoy.setColumns(2);
		hoy.setFont(new Font("IBM Plex Sans", Font.PLAIN, 15));
		hoy.setText(tareas(date));
		
		JLabel lblNewLabel_5 = new JLabel("    Para hoy:");
		lblNewLabel_5.setForeground(Color.DARK_GRAY);
		lblNewLabel_5.setOpaque(true);
		scrollPane.setColumnHeaderView(lblNewLabel_5);
		lblNewLabel_5.setBackground(fondo);
		lblNewLabel_5.setFont(new Font("IBM Plex Sans Medium", Font.PLAIN, 19));
		
		JPanel panel_3 = new JPanel();
		reporte.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1);
		scrollPane_1.setBackground(new Color(250,250,250));
		
		JTextArea stock = new JTextArea();
		stock.setLineWrap(true);
		stock.setBackground(fondo);
		stock.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		scrollPane_1.setViewportView(stock);
		stock.setText(stock());
		stock.setRows(20);
		
		JLabel lblNewLabel_6 = new JLabel("    Stock bajo:");
		lblNewLabel_6.setForeground(Color.DARK_GRAY);
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setBackground(fondo);
		lblNewLabel_6.setVerticalAlignment(SwingConstants.BOTTOM);
		scrollPane_1.setColumnHeaderView(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("IBM Plex Sans Medium", Font.PLAIN, 18));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_3.add(scrollPane_2);
		scrollPane_2.setBackground(new Color(250,250,250));
		
		JTextArea venc = new JTextArea();
		venc.setRows(20);
		scrollPane_2.setViewportView(venc);
		venc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		venc.setBackground(fondo);
		venc.setText(vencimiento());
		
		JLabel lblNewLabel_7 = new JLabel("    Vencimiento cercano:");
		lblNewLabel_7.setForeground(Color.DARK_GRAY);
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setBackground(fondo);
		lblNewLabel_7.setVerticalAlignment(SwingConstants.BOTTOM);
		scrollPane_2.setColumnHeaderView(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("IBM Plex Sans Medium", Font.PLAIN, 18));
		
		JLabel lblNewLabel_12 = new JLabel(" ");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel.add(lblNewLabel_12, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_11_1 = new JLabel("                          ");
		principal.add(lblNewLabel_11_1, BorderLayout.EAST);
		
		//Accion del boton laboratorio
		lab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callLab(user);//Llamando a la interfaz laboratorio
			}
		});
		
		//Accion del boton cliente
		cli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				funcion.callCliente(user);//Llamando a la interfaz cliente
			}
		});
		
		//Accion al presionar el Jlabel 
		graficas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Grafica grafica =  new Grafica();
				grafica.grafico("U");//Mostrar grafica de ventas por unidades al mes
				grafica.grafico("C");//Mostrar grafica de ventas por cajas al mes
			}
		});
		
		//Administrar usuarios
		usuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callAdminUsuarios();
			}
		});
		
		
		//Comprobar el tipo de acceso del usuario
		if(!funcion.comprobar(user)) {
			deshabilitar();
		}
	}
	
	//Deshabilitar funciones en modo lector
	public void deshabilitar() {
		usuarios.setVisible(false);
		plan.setEnabled(false);
		hoy.setText(" * No disponible *");
		compra.setEnabled(false);
	}	
	
	//Tareas para hoy en el reporte diario
	public String tareas(Date date) {
		String cad = "";
		try {
			SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
			String dateModif = formato.format(date);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from tarea where fecha = '"+dateModif+"';");
			while (rs.next()) {
				cad += " - "+rs.getString(3) +"\n";
			}
			if(cad.isEmpty()) {
				cad=" - Nada para hoy";
			}
			conexion.close();
			return cad;

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
			return cad;
		
	}
	
	//Productos con stock bajo en el reporte diario
	public String stock() {
		String cad = "";
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select *  from producto where activo = true and (stockunidad < 20 or stockcaja <20);");
			while (rs.next()) {
				cad += " - "+rs.getString(2);
				if(rs.getInt(7)<20) {
					cad+="\n     |_ Stock unidad = "+rs.getInt(7);
				}
				if(rs.getInt(8)<20) {
					cad+="\n     |_ Stock caja = "+rs.getInt(8);
				}
				cad+="\n__________________________\n";
				
			}
			conexion.close();
			

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return cad;
	}
	
	//Productos con vencimiento cercano en el reporte diario
	@SuppressWarnings("deprecation")
	public String vencimiento() {
		String cad="";
		try {
			Date fecha = new Date();
			Date actual = new Date();
			String date = "yyyy-MM-dd";
			SimpleDateFormat obj = new SimpleDateFormat(date);
			fecha.setMonth(fecha.getMonth() + 1);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto WHERE activo = true and fechaVencimiento between '"
					+ obj.format(actual) + "' and '" + obj.format(fecha) + "'");
			obj = new SimpleDateFormat("dd MMM YYYY");
			while (rs.next()) {
				cad += " - "+rs.getString(2)+"\n    |_ Vencimiento: "+obj.format(rs.getDate(6))+"\n__________________________\n";
				
			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return cad;
	}
}
