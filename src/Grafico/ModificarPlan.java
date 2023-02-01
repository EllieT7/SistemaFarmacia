package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import Clases.Conexion;
import Clases.Funciones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ModificarPlan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
	private JDateChooser fecha;
	private Calendar cal = new GregorianCalendar();
	private Funciones funcion = new Funciones();

	public ModificarPlan(String user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarPlan.class.getResource("/icon/edit.png")));
		setResizable(false);
		Color color = new Color(183, 212, 97);
		setTitle("Editar tarea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fecha = new JDateChooser();
		fecha.getCalendarButton().setBackground(Color.WHITE);
		fecha.getCalendarButton().setIcon(new ImageIcon(ModificarPlan.class.getResource("/icon/calG.png")));
		fecha.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		fecha.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fecha.getJCalendar().setWeekOfYearVisible(false);
		fecha.getJCalendar().setDecorationBackgroundColor(color);
		fecha.getJCalendar().setWeekdayForeground(Color.black);
		fecha.getJCalendar().setTodayButtonVisible(true);
		fecha.getJCalendar().setBackground(Color.WHITE);
		fecha.setBounds(145, 53, 148, 23);
		contentPane.add(fecha);
		fecha.setCalendar(cal);

		JTextPane msj = new JTextPane();
		msj.setEditable(false);
		msj.setText("Seleccione la tarea a editar:");
		msj.setForeground(color);
		msj.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		msj.setBounds(22, 11, 384, 35);
		contentPane.add(msj);

		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(ModificarPlan.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250, 116, 112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(10, 289, 115, 35);
		contentPane.add(btnNewButton);

		JButton btnAgregarProducto = new JButton("Editar");
		btnAgregarProducto.setIcon(new ImageIcon(ModificarPlan.class.getResource("/icon/edit.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
					funcion.callModificarPlan(cod, user);	
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione la tarea a editar", "Advertencia", 2, null);
				}
			}
		});

		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(388, 289, 131, 35);
		contentPane.add(btnAgregarProducto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 84, 485, 182);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		Object[] columnas = { "COD", "FECHA", "DESCRIPCION" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);

		table.setRowHeight(20);
		table.getTableHeader().setBackground(color);
		table.getTableHeader().setFont(new Font("IBM Plex Sans", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.WHITE);
		JLabel lblNewLabel = new JLabel("Ingrese una fecha:");
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		lblNewLabel.setBounds(22, 53, 138, 20);
		contentPane.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setIcon(new ImageIcon(ModificarPlan.class.getResource("/icon/buscar (1).png")));
		btnNewButton_1.setBackground(color);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] row = new Object[3];
				try {
					funcion.limpiarTabla(table);
					SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
					Conexion con = new Conexion();
					Connection conexion;
					conexion = (Connection) con.Conectar();
					Statement s = (Statement) conexion.createStatement();
					ResultSet rs = (ResultSet) s.executeQuery(
							"select * from tarea where fecha ='" + format.format(fecha.getDate()) + "';");

					while (rs.next()) {
						row[0] = rs.getInt(1);
						row[1] = formato.format(rs.getDate(2));
						row[2] = rs.getString(3);
						model.addRow(row);
					}

					conexion.close();

				} catch (InstantiationException | SQLException | IllegalAccessException e1) {
					JOptionPane.showMessageDialog(null, "Error: " + e1);
				}
			}
		});
		btnNewButton_1.setBounds(303, 53, 96, 23);
		contentPane.add(btnNewButton_1);

		insertarDatos();
	}

	public void insertarDatos() {
		Object[] row = new Object[3];

		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			Date fecha = new Date();
			String date = "yyyy-MM-dd";
			SimpleDateFormat obj = new SimpleDateFormat(date);
			ResultSet rs = (ResultSet) s.executeQuery("select * from tarea where fecha >='" + obj.format(fecha) + "';");

			while (rs.next()) {
				row[0] = rs.getInt(1);
				row[1] = obj.format(rs.getDate(2));
				row[2] = rs.getString(3);
				model.addRow(row);
			}

			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

}
