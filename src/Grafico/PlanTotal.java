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
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class PlanTotal extends JFrame {

	private JPanel contentPane,panel_1,panel_2,panel_3,panel_4,panel_5,panel_6, Central, Principal, panel;
	private Funciones funcion = new Funciones();
	private Color color = new Color(183,212,97);
	private JScrollPane scrollPane_1;
	private JLabel atras;
	private Date fecha = new Date();
	private JTextArea Plan = new JTextArea();
	
	@SuppressWarnings("deprecation")
	public PlanTotal(String user) {
		//Configuraciones del JFrame
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlanTotal.class.getResource("/icon/prescription.png")));
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
		
		//JScrollPane
		scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1);
		
		paneles();
		labels();
		
		//ScrollPane
		JScrollPane scrollText = new JScrollPane();
		scrollText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_5.add(scrollText, BorderLayout.CENTER);
		
		//Retroceder a la interfaz Planificacion
		atras.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callPlan(user);
			}
		});
		atras.setIcon(new ImageIcon(PlanTotal.class.getResource("/icon/atras5.png")));
	
		//ComboBox con los meses del año
		JComboBox<String> Mes = new JComboBox<String>();
		Mes.setPreferredSize(new Dimension(300, 27));
		panel_3.add(Mes);
		Mes.setModel(new DefaultComboBoxModel<String>(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		Mes.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
		Mes.setBackground(Color.WHITE);
		Mes.setSelectedItem(funcion.getMes(fecha.getMonth()+1));
		Mes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatos(Mes.getSelectedIndex()+1,fecha.getYear());
			}
		});
		Plan.setRows(25);
		Plan.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		scrollText.setViewportView(Plan);
		insertarDatos(Mes.getSelectedIndex()+1,fecha.getYear());
		
	}
	
	public String insertarDatos(int mes, int year ) {
		String cad ="";
		String total="";
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			int i = 0;
			
			while(i<31) {
				String date = funcion.getFecha(mes-1,year,i);
				ResultSet rs = (ResultSet) s.executeQuery("select * from tarea where fecha = '"+date+"';");
				String aux = date;
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date fecha = formato.parse(aux);
				SimpleDateFormat formato1 = new SimpleDateFormat("EEEE dd MMMM yyyy");
				String f=formato1.format(fecha);
				cad="\n                 "+f+"\n\n";
				while (rs.next()) {
					cad += "\t+ "+rs.getString(3)+"\n\n";
				}
				if(cad.equals("\n                 "+f+"\n\n")) {
					cad+="\t- Nada para hoy\n\n";
				}
				total += cad+"-------------------------------------------------------------------------------------------------------------------------------------------------\n";
				i++;
			}
			Plan.setText(total);
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException | ParseException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return cad;
	}
	public void paneles() {
		Principal = new JPanel();
		scrollPane_1.setViewportView(Principal);
		Principal.setBackground(Color.WHITE);
		Principal.setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		Principal.add(panel, BorderLayout.WEST);
		panel.setBackground(color);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		Central = new JPanel();
		Central.setOpaque(false);
		Principal.add(Central, BorderLayout.CENTER);
		Central.setLayout(new BorderLayout(0, 0));
		panel_4 = new JPanel();
		panel_4.setOpaque(false);
		Central.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 20));
		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_2, BorderLayout.CENTER);
		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(50);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_3, BorderLayout.SOUTH);
		panel_5 = new JPanel();
		panel_5.setOpaque(false);
		Central.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_5.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new BorderLayout(0, 0));
	}
	public void labels() {
		atras = new JLabel("");
		panel_1.add(atras);
		JLabel lblNewLabel_11_1 = new JLabel("             ");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_1.add(lblNewLabel_11_1, BorderLayout.NORTH);
		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_1.add(lblNewLabel_12, BorderLayout.WEST);
		JLabel lblNewLabel_8 = new JLabel("                ");
		panel_2.add(lblNewLabel_8);
		JLabel lblNewLabel_1 = new JLabel("");
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(PlanTotal.class.getResource("/icon/Plan.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel = new JLabel("Registro de tareas");
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
		JLabel lblNewLabel_3 = new JLabel("    ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_3.add(lblNewLabel_3);
		JLabel lblNewLabel_2 = new JLabel("Mes:");
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 20));
		JLabel lblNewLabel_5 = new JLabel(" ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 40));
		panel_5.add(lblNewLabel_5, BorderLayout.NORTH);
		JLabel lblNewLabel_6 = new JLabel(" ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel_5.add(lblNewLabel_6, BorderLayout.SOUTH);
		JLabel lblNewLabel_7 = new JLabel("                               ");
		panel_5.add(lblNewLabel_7, BorderLayout.WEST);
		JLabel lblNewLabel_4 = new JLabel("   ");
		panel_4.add(lblNewLabel_4, BorderLayout.NORTH);
		JLabel lblNewLabel_10 = new JLabel("                   ");
		panel_6.add(lblNewLabel_10, BorderLayout.WEST);
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(PlanTotal.class.getResource("/icon/plan2.jpg")));
		panel_6.add(lblNewLabel_9);
		JLabel lblNewLabel_11 = new JLabel("              ");
		panel_6.add(lblNewLabel_11, BorderLayout.EAST);
	}
}
