package Grafico;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class AgregarInfoControlado extends JFrame {

	private JPanel contentPane;
	private JTextField medico;
	private JTextField receta;
	private JTextField observaciones;

	public AgregarInfoControlado(int cod, DefaultTableModel model, Object datosVenta[],Double montoTotal, JTextField campoTotal) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgregarInfoControlado.class.getResource("/icon/agregar.png")));
		Color color = new Color(89, 217, 206);
		setTitle("Datos del producto controlado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 548, 342);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usted ha seleccionado un producto controlado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(color);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(52, 29, 428, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre del m\u00E9dico:");
		lblNewLabel_1_1.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(71, 113, 148, 20);
		contentPane.add(lblNewLabel_1_1);
		
		medico = new JTextField();
		medico.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		medico.setColumns(10);
		medico.setBounds(229, 112, 224, 25);
		contentPane.add(medico);
		
		JLabel lblNewLabel_1_2 = new JLabel("N\u00BA de receta:");
		lblNewLabel_1_2.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(71, 154, 148, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Observaciones:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(71, 196, 148, 20);
		contentPane.add(lblNewLabel_1_3);
		
		receta = new JTextField();
		receta.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		receta.setColumns(10);
		receta.setBounds(229, 153, 224, 25);
		contentPane.add(receta);
		
		observaciones = new JTextField();
		observaciones.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		observaciones.setColumns(10);
		observaciones.setBounds(229, 195, 224, 25);
		contentPane.add(observaciones);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(AgregarInfoControlado.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(31, 253, 118, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Aceptar");
		btnAgregarProducto.setIcon(new ImageIcon(AgregarInfoControlado.class.getResource("/icon/aceptar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DataOutputStream fileout = new DataOutputStream(new FileOutputStream("datosProductosControlados.dat", false));
					fileout.writeInt(cod);
					fileout.writeUTF(medico.getText());
					fileout.writeUTF(receta.getText());
					fileout.writeUTF(observaciones.getText());
					fileout.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"Datos ingresados exitosamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
				
				Object[] row = new Object[11];
				for(int i=0; i<8;i++) {
					row[i] = datosVenta[i];
				}
				row[8] = medico.getText();
				row[9] = receta.getText();
				row[10] = observaciones.getText();
				Object[] columnas = { "COD", "PRODUCTO", "PRESENTACIÓN", "VENCIMIENTO", "CANTIDAD", "TIPO", "PRECIO",
				"TOTAL", "MÉDICO","Nº RECETA","OBSERVACIONES" };
				model.setColumnIdentifiers(columnas);
				model.addRow(row);
				Double montoTotal = Double.parseDouble(row[7].toString());
				
				if(campoTotal.getText()!=null && !campoTotal.getText().isEmpty()) {
					campoTotal.setText((Double.parseDouble(campoTotal.getText())+montoTotal)+"");
				}else {
					campoTotal.setText(montoTotal+"");
				}
				
				dispose();
				
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(379, 253, 126, 35);
		contentPane.add(btnAgregarProducto);
		
		JLabel lblIngreseLosSiguientes = new JLabel("Ingrese los siguientes datos:");
		lblIngreseLosSiguientes.setForeground(new Color(89, 217, 206));
		lblIngreseLosSiguientes.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblIngreseLosSiguientes.setBounds(35, 71, 428, 31);
		contentPane.add(lblIngreseLosSiguientes);
		setLocationRelativeTo(null);
		
	}
	public void limpiar() {
		medico.setText("");
		receta.setText("");
		observaciones.setText("");
	}

}
