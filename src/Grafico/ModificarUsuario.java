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
import Clases.Acceso;
import Clases.Conexion;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class ModificarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JTextField pass;
	private JRadioButton Editor, Lector;

	public ModificarUsuario(int cod) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarUsuario.class.getResource("/icon/agregar.png")));
		Color color = new Color(244, 164, 96);
		setTitle("Agregar nuevo usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 301);
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
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(ModificarUsuario.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(10, 210, 120, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Guardar");
		btnAgregarProducto.setIcon(new ImageIcon(ModificarUsuario.class.getResource("/icon/save.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario1 = usuario.getText();
				String password = pass.getText();
				boolean estado = false;
				if(Editor.isSelected()) {
					estado = true;
				}
				Acceso acceso = new Acceso(usuario1,password,estado);
				acceso.modificar(cod);
				JOptionPane.showMessageDialog(null,"Modificado exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				limpiar();
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(298, 210, 159, 35);
		contentPane.add(btnAgregarProducto);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 72, 148, 20);
		contentPane.add(lblNewLabel_1_1);
		
		usuario = new JTextField();
		usuario.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		usuario.setColumns(10);
		usuario.setBounds(168, 71, 224, 25);
		contentPane.add(usuario);
		
		JLabel lblNewLabel_1_2 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 113, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		pass = new JTextField();
		pass.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		pass.setColumns(10);
		pass.setBounds(168, 112, 224, 25);
		contentPane.add(pass);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(169, 169, 169)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(178, 152, 159, 39);
		contentPane.add(panel);
		Editor = new JRadioButton("Editor");
		panel.add(Editor);
		Editor.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		Editor.setBackground(Color.WHITE);
	
		Lector = new JRadioButton("Lector");
		panel.add(Lector);
		Lector.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		Lector.setBackground(Color.WHITE);
		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(Editor);
		grupo1.add(Lector);
		getDatos(cod);
	}
	public void limpiar() {
		usuario.setText("");
		pass.setText("");
	}
	public void getDatos(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from administrador");
			while (rs.next()) {
				if(cod==rs.getInt(1)) {
					usuario.setText(rs.getString(2));
					pass.setText(rs.getString(3));
					if(rs.getBoolean(4)) {
						Editor.setSelected(true);
					}
					else {
						Lector.setSelected(true);
					}
					break;
				}
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
