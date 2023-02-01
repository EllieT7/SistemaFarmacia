package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import Clases.Producto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class EliminarPro extends JFrame {

	private JPanel contentPane;
	private int c=1;

	public EliminarPro(int cod,String pro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarPro.class.getResource("/icon/basura.png")));
		setResizable(false);
		Color color = new Color(0,163,224);
		setTitle("Eliminar producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 191);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane msj = new JTextPane();
		msj.setEditable(false);
		msj.setText("Desea eliminar el producto "+pro+" del registro?");
		msj.setForeground(color);
		msj.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		msj.setBounds(33, 37, 374, 58);
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
		btnNewButton.setBounds(68, 106, 115, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Aceptar");	
		btnAgregarProducto.setIcon(new ImageIcon(EliminarPro.class.getResource("/icon/aceptar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c==1) {
					Producto.eliminar(cod);
					btnNewButton.setVisible(false);
					c++;
					msj.setText("Eliminado correctamente");
				}else if(c==2) {
					dispose();
				}

			}
		});
		
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(259, 106, 131, 35);
		contentPane.add(btnAgregarProducto);
	}
}
