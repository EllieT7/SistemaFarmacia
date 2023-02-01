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

import Clases.Funciones;
import Clases.Plan;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AgregarPlan extends JFrame {

	private JPanel contentPane;
	private JTextField desc;
	private SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
	private JDateChooser fecha;
	private Calendar cal = new GregorianCalendar();
	private Funciones funcion =  new Funciones();

	public AgregarPlan(String user) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarPlan.class.getResource("/icon/agregar.png")));
		Color color = new Color(183,212,97);
		setTitle("Agregar nueva tarea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese los siguientes datos:");
		lblNewLabel.setForeground(color);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(35, 29, 329, 31);
		contentPane.add(lblNewLabel);
		
		JLabel fechaText = new JLabel("Fecha:");
		fechaText.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fechaText.setHorizontalAlignment(SwingConstants.LEFT);
		fechaText.setBounds(79, 83, 148, 20);
		contentPane.add(fechaText);
		
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
		btnNewButton.setIcon(new ImageIcon(AgregarPlan.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(10, 335, 120, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Agregar tarea");
		btnAgregarProducto.setIcon(new ImageIcon(AgregarPlan.class.getResource("/icon/agregar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descp = desc.getText();
				String fch =  formato.format(fecha.getDate());
				Plan plan = new Plan(fch,descp,Integer.parseInt(funcion.getCodigo(user, "administrador",2,1)));
				plan.agregar();
				JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				limpiar();
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(363, 335, 159, 35);
		contentPane.add(btnAgregarProducto);
		
		fecha = new JDateChooser();
		fecha.getCalendarButton().setBackground(Color.WHITE);
		fecha.getCalendarButton().setIcon(new ImageIcon(AgregarPlan.class.getResource("/icon/calG.png")));
		fecha.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fecha.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fecha.getJCalendar().setWeekOfYearVisible(false);
		fecha.getJCalendar().setDecorationBackgroundColor(color);
		fecha.getJCalendar().setWeekdayForeground(Color.black);
		fecha.getJCalendar().setTodayButtonVisible(true);
		fecha.getJCalendar().setBackground(Color.WHITE);
		fecha.setBounds(127, 80, 228, 25);
		contentPane.add(fecha);
		fecha.setCalendar(cal);
	}
	public void limpiar() {
		fecha.setCalendar(cal);
		desc.setText("");
	}
}
