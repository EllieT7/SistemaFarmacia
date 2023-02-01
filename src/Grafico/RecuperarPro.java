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
import Clases.Producto;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class RecuperarPro extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private Funciones funcion = new Funciones();

	public RecuperarPro(String user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RecuperarPro.class.getResource("/icon/prescription.png")));
		Color color = new Color(0, 163, 224);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1875, 1085);
		setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Object[] columnas = { "COD", "STOCK UNIDAD", "STOCK CAJAS", "NOMBRE", "PRESENTACIÓN", "LABORATORIO",
				"PRINCIPIO ACTIVO", "PRECIO UNITARIO", "PRECIO CAJA", "FECHA DE VENCIMIENTO" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		contentPane.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		JPanel Principal = new JPanel();
		Principal.setBackground(Color.WHITE);
		scrollPane.setViewportView(Principal);
		Principal.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		Principal.add(panel, BorderLayout.WEST);
		panel.setBackground(color);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel atras = new JLabel("");
		panel_2.add(atras);
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				funcion.callProducto(user);
			}
		});
		atras.setIcon(new ImageIcon(RecuperarPro.class.getResource("/icon/Metro Icon 13x.png")));

		JLabel lblNewLabel_11 = new JLabel("             ");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblNewLabel_11, BorderLayout.NORTH);
		JLabel lblNewLabel_12 = new JLabel("    ");
		panel_2.add(lblNewLabel_12, BorderLayout.WEST);
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		Principal.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 30));
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_6.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_4 = new JLabel(" ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_5.add(lblNewLabel_4, BorderLayout.NORTH);
		JLabel lblNewLabel_5 = new JLabel("               ");
		panel_5.add(lblNewLabel_5, BorderLayout.WEST);
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_5.add(panel_1, BorderLayout.CENTER);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		JLabel lblNewLabel_1 = new JLabel("");
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(RecuperarPro.class.getResource("/icon/Helpbag.jpg")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNewLabel = new JLabel("Productos eliminados");
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_6.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 50));
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_4, BorderLayout.SOUTH);
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_4.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_6 = new JLabel("  ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panel_7.add(lblNewLabel_6, BorderLayout.EAST);
		JLabel lblNewLabel_7 = new JLabel("  ");
		panel_7.add(lblNewLabel_7, BorderLayout.SOUTH);
		
		JButton btnNewButton_1_1_1 = new JButton("Recuperar producto");
		panel_7.add(btnNewButton_1_1_1);
		btnNewButton_1_1_1.setIcon(new ImageIcon(RecuperarPro.class.getResource("/icon/refresh1.png")));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Producto.recuperar(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					JOptionPane.showMessageDialog(null,"Recuperado exitosamente!","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
					insertarDatos();
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el producto a recuperar", "Advertencia", 2, null);
				}
			}
		});
		
		btnNewButton_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		btnNewButton_1_1_1.setBackground(new Color(0, 163, 224));

		JLabel lblNewLabel_2 = new JLabel("                                  ");
		panel_3.add(lblNewLabel_2, BorderLayout.WEST);
		JLabel lblNewLabel_3 = new JLabel("                                  ");
		panel_3.add(lblNewLabel_3, BorderLayout.EAST);
		
		//configuracion de tabla
		table = new JTable();
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 16));
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
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(200);
		table.setAutoCreateRowSorter(true);

		JScrollPane pane = new JScrollPane(table);
		panel_3.add(pane, BorderLayout.CENTER);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		
		//Productos eliminados
		insertarDatos();

	}

	public void insertarDatos() {
		Object[] row = new Object[10];
		try {
			SimpleDateFormat formato =  new SimpleDateFormat("dd-MM-YYYY");
			funcion.limpiarTabla(table);
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto where activo = false");
			while (rs.next()) {
				row[0] = rs.getInt(1);
				row[1] = rs.getInt(7);
				row[2] = rs.getInt(8);
				row[3] = rs.getString(2);
				row[4] = funcion.getDato("presentacion", 2, rs.getInt(9));
				row[5] = funcion.getDato("laboratorio", 4, rs.getInt(10));
				row[6] = rs.getString(3);
				row[7] = rs.getBigDecimal(4);
				row[8] = rs.getBigDecimal(5);
				row[9] = formato.format(rs.getDate(6));
				model.addRow(row);
			}
			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
