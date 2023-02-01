package Grafico;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Clases.Acceso;
import Clases.Funciones;
import java.awt.Frame;
import java.awt.HeadlessException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class Login extends JFrame {
	
	private int c = 0;
	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private Funciones funcion = new Funciones();
	private final JPanel Principal = new JPanel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		
		//Configuracion del frame
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icon/prescription.png")));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		//Content Pane
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Inicializacion y configuracion ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setEnabled(false);
		contentPane.add(scrollPane);
		
		//Inicializacion panel superior
		JPanel panelSup = new JPanel();
		panelSup.setOpaque(false);
		panelSup.setLayout(new BorderLayout(0, 0));
		
		//imagen Login encabezado
		JLabel encabezado = new JLabel("");
		encabezado.setIcon(new ImageIcon(Login.class.getResource("/icon/encabezado.png")));
		panelSup.add(encabezado, BorderLayout.CENTER);

		JLabel label1 = new JLabel(" ");
		panelSup.add(label1, BorderLayout.SOUTH);
		Principal.setBackground(Color.WHITE);
		scrollPane.setViewportView(Principal);
		Principal.setLayout(new BorderLayout(0, 0));
		Principal.add(panelSup, BorderLayout.NORTH);

		JPanel panelInf = new JPanel();
		Principal.add(panelInf, BorderLayout.SOUTH);
		panelInf.setOpaque(false);
		FlowLayout fl_panelInf = (FlowLayout) panelInf.getLayout();
		fl_panelInf.setHgap(30);
		fl_panelInf.setVgap(30);
		fl_panelInf.setAlignment(FlowLayout.LEFT);
		
		//Boton Salir --> Cierra el programa
		JButton Salir = new JButton("              Salir                ");
		panelInf.add(Salir);
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Salir.setForeground(Color.WHITE);
		Salir.setFont(new Font("IBM Plex Sans Medium", Font.BOLD, 19));
		Salir.setBackground(new Color(240, 128, 128));
		
		JPanel panelCentral = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCentral.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		Principal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setOpaque(false);

		JLabel label2 = new JLabel("                                                                                                     ");
		panelCentral.add(label2);

		JPanel panelFromulario = new JPanel();
		panelCentral.add(panelFromulario);
		panelFromulario.setBorder(null);
		panelFromulario.setBackground(Color.WHITE);
		panelFromulario.setLayout(new GridLayout(10, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Bienvenido!");
		panelFromulario.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 37));

		JLabel lblNewLabel_1 = new JLabel("Ingrese sus datos para acceder al sistema");
		panelFromulario.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(104, 104, 104));
		lblNewLabel_1.setFont(new Font("IBM Plex Sans Light", Font.BOLD, 17));

		JLabel lblNewLabel_2 = new JLabel("");
		panelFromulario.add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Nombre de usuario:");
		panelFromulario.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(new Color(104, 104, 104));
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans Light", Font.BOLD, 17));

		userField = new JTextField();
		panelFromulario.add(userField);
		userField.setFont(new Font("IBM Plex Sans", Font.PLAIN, 19));
		userField.setBackground(new Color(243, 243, 243));
		userField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("");
		panelFromulario.add(lblNewLabel_3);

		JLabel lblNewLabel_1_1_1 = new JLabel("Contrase\u00F1a:");
		panelFromulario.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setForeground(new Color(104, 104, 104));
		lblNewLabel_1_1_1.setFont(new Font("IBM Plex Sans Light", Font.BOLD, 17));

		passwordField = new JPasswordField();
		panelFromulario.add(passwordField);
		passwordField.setFont(new Font("IBM Plex Sans", Font.PLAIN, 19));
		passwordField.setBackground(new Color(243, 243, 243));
		JLabel lblNewLabel_4 = new JLabel("");
		panelFromulario.add(lblNewLabel_4);
		
		//Accion al presionar el boton INGRESAR --> Comprueba contrase�a y usuario
		JButton ingresar = new JButton("Ingresar");
		panelFromulario.add(ingresar);
		ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Acceso ac = new Acceso(userField.getText(), passwordField.getText());
				intentos(ac);
			}
		});
		ingresar.setForeground(new Color(255, 255, 255));
		ingresar.setFont(new Font("IBM Plex Sans Medium", Font.PLAIN, 19));
		ingresar.setBackground(new Color(0, 163, 224));
		JLabel label3 = new JLabel("                                                                                                     ");
		panelCentral.add(label3);
		
		//imagen Login
		JLabel fr = new JLabel();
		panelCentral.add(fr);
		fr.setIcon(new ImageIcon(Login.class.getResource("/icon/fr.jpg")));
	}
	
	//Verifica que los intentos no sobrepasen 3
	public void intentos(Acceso ac) {
		try {
			if (ac.comprobar()) {
				dispose();
				funcion.callMenu(userField.getText());//Si el usuario y contrase�a son correctos, ingresa al sistema
			}else {
				c++;
				JOptionPane.showMessageDialog(null, "Datos incorrectos, le quedan "+(3-c)+" intentos","Advertencia",2,null);
			
			}
			if (c>2) {
				JOptionPane.showMessageDialog(null, "Intentos agotados, sesion cerrada","Cerrando sesion",0,null);
				System.exit(0);
			}
			userField.setText("");
			passwordField.setText("");
			
		} catch (SQLException | HeadlessException | InstantiationException | IllegalAccessException
				| IOException e1) {
			JOptionPane.showMessageDialog(null,"Se produjo un error, intente nuevamente"+e1);
		}
	}
}
