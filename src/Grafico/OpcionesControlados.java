package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import Clases.Funciones;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class OpcionesControlados extends JFrame {

	private JPanel contentPane;
	private int c=1;
	private Funciones funcion = new Funciones();

	public OpcionesControlados() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarPro.class.getResource("/icon/basura.png")));
		setResizable(false);
		Color color = new Color(0,163,224);
		setTitle("Eliminar producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 245);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane msj = new JTextPane();
		msj.setEditable(false);
		msj.setText("Seleccione una opci\u00F3n:");
		msj.setForeground(color);
		msj.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		msj.setBounds(32, 29, 374, 35);
		contentPane.add(msj);
		
		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(EliminarPro.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(69, 155, 115, 35);
		contentPane.add(btnNewButton);

		JRadioButton existente = new JRadioButton("Agregar desde registro existente");
		existente.setBackground(Color.WHITE);
		existente.setSelected(true);
		existente.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		existente.setBounds(89, 73, 285, 23);
		contentPane.add(existente);
		
		JRadioButton nuevo = new JRadioButton("Agregar nuevo producto");
		nuevo.setBackground(Color.WHITE);
		nuevo.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		nuevo.setBounds(89, 105, 285, 23);
		contentPane.add(nuevo);
		ButtonGroup grupo1 = new ButtonGroup();
		grupo1.add(nuevo);
		grupo1.add(existente);
		
		
		JButton btnAgregarProducto = new JButton("Aceptar");	
		btnAgregarProducto.setIcon(new ImageIcon(EliminarPro.class.getResource("/icon/aceptar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if(nuevo.isSelected()) {
					System.out.println("nuevo");
					funcion.callOpcionNuevoControlados();
				}else if (existente.isSelected()){
					System.out.println("existente");
					funcion.callOpcionExistenteControlados();
				}
				

			}
		});
		
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(264, 155, 131, 35);
		contentPane.add(btnAgregarProducto);
		
		
	}
}

