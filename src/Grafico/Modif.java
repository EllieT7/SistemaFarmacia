package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Clases.Conexion;
import Clases.Funciones;
import Clases.Plan;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Modif extends JFrame {

	private JPanel contentPane;
	private JTextField desc;
	private SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
	private JDateChooser fecha;
	private Calendar cal = new GregorianCalendar();
	private Funciones funcion = new Funciones();
	
	public Modif(int cod, String user) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Modif.class.getResource("/icon/edit.png")));
		Color color = new Color(183,212,97);
		setTitle("Editar tarea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fecha = new JDateChooser();
		fecha.getCalendarButton().setIcon(new ImageIcon(Modif.class.getResource("/icon/calG.png")));
		fecha.getCalendarButton().setBackground(Color.WHITE);
		fecha.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fecha.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fecha.getJCalendar().setWeekOfYearVisible(false);
		fecha.getJCalendar().setDecorationBackgroundColor(color);
		fecha.getJCalendar().setWeekdayForeground(Color.black);
		fecha.getJCalendar().setTodayButtonVisible(true);
		fecha.getJCalendar().setBackground(Color.WHITE);
		contentPane.add(fecha);
		fecha.setCalendar(cal);
		fecha.setBounds(126, 80, 228, 20);
		
		JLabel lblNewLabel = new JLabel("Ingrese los siguientes datos:");
		lblNewLabel.setForeground(color);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(35, 29, 329, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha:");
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(79, 80, 148, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Descripci\u00F3n:");
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setBounds(79, 126, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		desc = new JTextField();
		desc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 15));
		desc.setColumns(10);
		desc.setBounds(79, 157, 382, 155);
		contentPane.add(desc);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(Modif.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(10, 335, 121, 35);
		contentPane.add(btnNewButton);
		
		JButton Guardar = new JButton("Guardar");
		Guardar.setIcon(new ImageIcon(Modif.class.getResource("/icon/save.png")));
		Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descp = desc.getText();
				String fch = formato.format(fecha.getDate());
				int admin = Integer.parseInt(funcion.getCodigo(user, "administrador",2,1));
				Plan plan = new Plan(fch,descp,admin);
				plan.modificar(cod);
				JOptionPane.showMessageDialog(null,"Datos modificados exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				limpiar();
			}
		});
		Guardar.setForeground(Color.WHITE);
		Guardar.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		Guardar.setBackground(color);
		Guardar.setBounds(374, 335, 148, 35);
		contentPane.add(Guardar);
		
		
		setLocationRelativeTo(null);
		getDatos(cod);
	}
	public void limpiar() {
		fecha.setCalendar(cal);
		desc.setText("");
	}
	public void getDatos(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from tarea");
			while (rs.next()) {
				if(cod==rs.getInt(1)) {
					fecha.setDate(rs.getDate(2));
					desc.setText(rs.getString(3));
					break;
				}
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
