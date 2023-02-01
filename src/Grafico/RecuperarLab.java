package Grafico;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Clases.Conexion;
import Clases.Funciones;
import Clases.Laboratorio;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class RecuperarLab extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private Funciones funcion = new Funciones();

	public RecuperarLab(String user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RecuperarLab.class.getResource("/icon/prescription.png")));
		Color color = new Color(41, 173, 158);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Object[] columnas = { "CÓDIGO", "VENDEDOR", "TELÉFONO", "LABORATORIO" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		JPanel Principal = new JPanel();
		Principal.setBackground(Color.WHITE);
		scrollPane.setViewportView(Principal);
		Principal.setLayout(new BorderLayout(0, 0));
		JPanel panel_Izq = new JPanel();
		Principal.add(panel_Izq, BorderLayout.WEST);
		panel_Izq.setBackground(color);
		panel_Izq.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_Izq.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel atras = new JLabel("");
		panel_2.add(atras);
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callLab(user);
			}
		});
		atras.setIcon(new ImageIcon(RecuperarLab.class.getResource("/icon/atras3.png")));

		JLabel lblNewLabel_11 = new JLabel("             ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_11, BorderLayout.NORTH);
		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_2.add(lblNewLabel_12, BorderLayout.WEST);
		JPanel panel_Central = new JPanel();
		panel_Central.setOpaque(false);
		Principal.add(panel_Central, BorderLayout.CENTER);
		panel_Central.setLayout(new BorderLayout(0, 25));
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_Central.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new BorderLayout(0, 0));
		JLabel img = new JLabel();
		panel_4.add(img, BorderLayout.CENTER);
		img.setIcon(new ImageIcon(RecuperarLab.class.getResource("/icon/labo1.png")));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_4.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton_1_1_1 = new JButton("Recuperar laboratorio");
		panel_3.add(btnNewButton_1_1_1);
		btnNewButton_1_1_1.setIcon(new ImageIcon(RecuperarLab.class.getResource("/icon/refresh1.png")));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Laboratorio.recuperar(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					JOptionPane.showMessageDialog(null,"Recuperado exitosamente!","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
					insertarDatos();
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el laboratorio a recuperar", "Advertencia", 2, null);
				}
				
			}
		});
		btnNewButton_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnNewButton_1_1_1.setBackground(color);

		JLabel lblNewLabel_2 = new JLabel(" ");
		panel_3.add(lblNewLabel_2, BorderLayout.NORTH);
		JLabel lblNewLabel_3 = new JLabel("         ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel_3.add(lblNewLabel_3, BorderLayout.WEST);
		JLabel lblNewLabel_4 = new JLabel(" ");
		panel_3.add(lblNewLabel_4, BorderLayout.SOUTH);
		JLabel lblNewLabel_5 = new JLabel("         ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel_3.add(lblNewLabel_5, BorderLayout.EAST);
		JLabel lblNewLabel_10 = new JLabel(" ");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 60));
		panel_4.add(lblNewLabel_10, BorderLayout.NORTH);

		table = new JTable();
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 17));
		table.setModel(model);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.gray);
		table.setGridColor(Color.GRAY);
		table.setSelectionForeground(Color.white);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(color);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		// Configurando el tamaño de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);
		panel_Central.add(pane, BorderLayout.CENTER);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);

		JPanel panel_Sup = new JPanel();
		panel_Sup.setOpaque(false);
		panel_Central.add(panel_Sup, BorderLayout.NORTH);
		panel_Sup.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_6 = new JLabel("  ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 35));
		panel_Sup.add(lblNewLabel_6, BorderLayout.NORTH);
		JLabel lblNewLabel_7 = new JLabel("               ");
		panel_Sup.add(lblNewLabel_7, BorderLayout.WEST);
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_Sup.add(panel_1, BorderLayout.CENTER);
		JLabel lblNewLabel_1 = new JLabel("");
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(RecuperarLab.class.getResource("/icon/Lab.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel = new JLabel("Laboratorios eliminados");
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
		JLabel lblNewLabel_8 = new JLabel(" ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_Central.add(lblNewLabel_8, BorderLayout.SOUTH);
		JLabel lblNewLabel_9 = new JLabel("                             ");
		panel_Central.add(lblNewLabel_9, BorderLayout.WEST);

		//Ingresar laboratorios eliminados
		insertarDatos();

	}

	public void insertarDatos() {
		Object[] row = new Object[4];
		try {
			funcion.limpiarTabla(table);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from laboratorio where activo = false");
			while (rs.next()) {
				row[0] = rs.getInt(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				model.addRow(row);
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

}
