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
import Clases.Laboratorio;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ModificarLab extends JFrame {

	private JPanel contentPane;
	private JTextField cc;
	private JTextField lab;
	private JTextField ven;
	private JTextField telf;

	public ModificarLab(int cod) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarLab.class.getResource("/icon/edit.png")));
		setResizable(false);
		Color color = new Color(41,173,158);
		setTitle("Editar laboratorio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 360);
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
		
		cc = new JTextField();
		cc.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		cc.setEditable(false);
		cc.setBounds(227, 81, 86, 25);
		contentPane.add(cc);
		cc.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo de laboratorio:");
		lblNewLabel_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(70, 82, 148, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre del laboratorio:");
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(69, 125, 148, 20);
		contentPane.add(lblNewLabel_1_1);
		
		lab = new JTextField();
		lab.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lab.setColumns(10);
		lab.setBounds(227, 124, 224, 25);
		contentPane.add(lab);
		
		JLabel lblNewLabel_1_2 = new JLabel("Vendedor/a:");
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(69, 166, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(69, 208, 148, 20);
		contentPane.add(lblNewLabel_1_3);
		
		ven = new JTextField();
		ven.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		ven.setColumns(10);
		ven.setBounds(227, 165, 224, 25);
		contentPane.add(ven);
		
		telf = new JTextField();
		telf.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		telf.setColumns(10);
		telf.setBounds(227, 207, 224, 25);
		contentPane.add(telf);
		
		getDatos(cod);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(ModificarLab.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(29, 261, 115, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Guardar");
		btnAgregarProducto.setIcon(new ImageIcon(ModificarLab.class.getResource("/icon/save.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Laboratorio labo = new Laboratorio(cc.getText(), ven.getText(), telf.getText(), lab.getText());
				labo.modificar();
				JOptionPane.showMessageDialog(null,"Modificado exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				limpiar();
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(367, 261, 136, 35);
		contentPane.add(btnAgregarProducto);
		setLocationRelativeTo(null);
	}
	public void limpiar() {
		ven.setText("");
		cc.setText("");
		telf.setText("");
		lab.setText("");
	}
	public void getDatos(int cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from laboratorio");
			while (rs.next()) {
				if(cod == rs.getInt(1)) {
					cc.setText(cod+"");
					lab.setText(rs.getString(4));
					ven.setText(rs.getString(2));
					telf.setText(rs.getString(3));
					break;
				}
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
