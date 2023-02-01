package Grafico;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Clases.Cliente;
import Clases.Conexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ModificarCli extends JFrame {

	private JPanel contentPane;
	private JTextField ci;
	private JTextField nomb;
	private JTextField telf;

	public ModificarCli(String cod) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarCli.class.getResource("/icon/edit.png")));
		Color color = new Color(118,203,222);
		setTitle("Editar cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 307);
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
		
		ci = new JTextField();
		ci.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		ci.setBounds(227, 81, 86, 25);
		contentPane.add(ci);
		ci.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CI del cliente:");
		lblNewLabel_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(70, 82, 148, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre del cliente:");
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(69, 125, 148, 20);
		contentPane.add(lblNewLabel_1_1);
		
		nomb = new JTextField();
		nomb.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		nomb.setColumns(10);
		nomb.setBounds(227, 124, 224, 25);
		contentPane.add(nomb);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(69, 166, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		telf = new JTextField();
		telf.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		telf.setColumns(10);
		telf.setBounds(227, 165, 224, 25);
		contentPane.add(telf);
		
		getDatos(cod);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(ModificarCli.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(35, 217, 118, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Guardar");
		btnAgregarProducto.setIcon(new ImageIcon(ModificarCli.class.getResource("/icon/save.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CI = ci.getText();
				String nb = nomb.getText();
				String tl = telf.getText();
				Cliente cli =  new Cliente(CI,nb,tl);
				cli.modificar();
				JOptionPane.showMessageDialog(null,"Modificado exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				limpiar();
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(373, 217, 136, 35);
		contentPane.add(btnAgregarProducto);
		setLocationRelativeTo(null);
	}
	public void getDatos(String cod) {
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from cliente");
			while (rs.next()) {
				if(cod.equals(rs.getString(1))) {
					ci.setText(cod);
					nomb.setText(rs.getString(2));
					telf.setText(rs.getString(3));
					break;
				}
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	public void limpiar() {
		nomb.setText("");
		ci.setText("");
		telf.setText("");
	}
}
