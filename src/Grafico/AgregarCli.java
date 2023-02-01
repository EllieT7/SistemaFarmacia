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
import Clases.Cliente;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AgregarCli extends JFrame {

	private JPanel contentPane;
	private JTextField CI;
	private JTextField nombre;
	private JTextField telf;

	
	public AgregarCli() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarCli.class.getResource("/icon/agregar.png")));
		Color color = new Color(118,203,222);
		setTitle("Agregar nuevo cliente");
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
		
		CI = new JTextField();
		CI.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		CI.setBounds(227, 81, 137, 25);
		contentPane.add(CI);
		CI.setColumns(10);
		
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
		
		nombre = new JTextField();
		nombre.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		nombre.setColumns(10);
		nombre.setBounds(227, 124, 224, 25);
		contentPane.add(nombre);
		
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
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(AgregarCli.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(35, 217, 119, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Agregar cliente");
		btnAgregarProducto.setIcon(new ImageIcon(AgregarCli.class.getResource("/icon/agregar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ci = CI.getText();
				String nomb = nombre.getText();
				String tf = telf.getText();
				Cliente cli =  new Cliente(ci,nomb,tf);
				cli.agregar();
				JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				limpiar();
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(350, 217, 159, 35);
		contentPane.add(btnAgregarProducto);
		setLocationRelativeTo(null);
	}
	public void limpiar() {
		nombre.setText("");
		CI.setText("");
		telf.setText("");
	}
}
