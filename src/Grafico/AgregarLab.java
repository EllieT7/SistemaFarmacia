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
import Clases.Laboratorio;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AgregarLab extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField vendedor;
	private JTextField telf;

	public AgregarLab() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarLab.class.getResource("/icon/agregar.png")));
		Color color = new Color(41,173,158);
		setTitle("Agregar nuevo laboratorio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 310);
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre del laboratorio:");
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(68, 78, 148, 20);
		contentPane.add(lblNewLabel_1_1);
		
		nombre = new JTextField();
		nombre.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		nombre.setColumns(10);
		nombre.setBounds(226, 77, 224, 25);
		contentPane.add(nombre);
		
		JLabel lblNewLabel_1_2 = new JLabel("Vendedor/a:");
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(68, 119, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(68, 161, 148, 20);
		contentPane.add(lblNewLabel_1_3);
		
		vendedor = new JTextField();
		vendedor.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		vendedor.setColumns(10);
		vendedor.setBounds(226, 118, 224, 25);
		contentPane.add(vendedor);
		
		telf = new JTextField();
		telf.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		telf.setColumns(10);
		telf.setBounds(226, 160, 224, 25);
		contentPane.add(telf);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(AgregarLab.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(30, 222, 118, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Agregar laboratorio");
		btnAgregarProducto.setIcon(new ImageIcon(AgregarLab.class.getResource("/icon/agregar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vend = vendedor.getText();
				String tl = telf.getText();
				String lab = nombre.getText();
				Laboratorio labo = new Laboratorio("1",vend,tl,lab);
				labo.agregar();
				JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				limpiar();
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(320, 222, 184, 35);
		contentPane.add(btnAgregarProducto);
		setLocationRelativeTo(null);
	}
	public void limpiar() {
		nombre.setText("");
		vendedor.setText("");
		telf.setText("");
	}
}
