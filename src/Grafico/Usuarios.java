package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import Clases.Acceso;
import Clases.Conexion;
import Clases.Funciones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Usuarios extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Funciones funcion = new Funciones();

	public Usuarios() {
		Color color = new Color(244, 164, 96);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/icon/edit.png")));
		setResizable(false);
		setTitle("Administrar usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane msj = new JTextPane();
		msj.setEditable(false);
		msj.setText("Usuarios");
		msj.setForeground(color);
		msj.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		msj.setBounds(22, 11, 384, 35);
		contentPane.add(msj);

		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
					ImageIcon icono = new ImageIcon(EliminarPlan.class.getResource("/icon/megaphone.png"));
					int c = JOptionPane.showConfirmDialog(null, "Está seguro de que quiere eliminar este usuario?","Eliminar tarea", JOptionPane.YES_NO_OPTION,-1,icono);
					if (c == 0) {
						Acceso.eliminar(cod);
						JOptionPane.showMessageDialog(null,"Eliminado correctamente","",-1,new ImageIcon(AgregarCli.class.getResource("/icon/checked.png")));
					}
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el usuario a eliminar", "Advertencia", 2, null);
				}
			}
		});
		btnNewButton.setBackground(new Color(250, 116, 112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(22, 289, 115, 35);
		contentPane.add(btnNewButton);

		JButton btnAgregarProducto = new JButton("Editar");
		btnAgregarProducto.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/edit.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
					funcion.callModificarUsuario(cod);
				}catch (IndexOutOfBoundsException error) {
					JOptionPane.showMessageDialog(null,"Seleccione el usuario a editar", "Advertencia", 2, null);
				}
			}
		});

		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(191, 289, 131, 35);
		contentPane.add(btnAgregarProducto);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 57, 485, 209);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		Object[] columnas = { "COD", "USUARIO", "CONTRASEÑA", "ACCESO" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);

		table.setRowHeight(20);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/agregar.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcion.callAgregarUsuario();
			}
		});
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregar.setBackground(color);
		btnAgregar.setBounds(380, 289, 131, 35);
		contentPane.add(btnAgregar);

		JButton btnAgregar_1 = new JButton("");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatos();
			}
		});
		btnAgregar_1.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/refresh1.png")));
		btnAgregar_1.setForeground(Color.WHITE);
		btnAgregar_1.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregar_1.setBackground(new Color(244, 164, 96));
		btnAgregar_1.setBounds(474, 11, 33, 35);
		contentPane.add(btnAgregar_1);
		table.getTableHeader().setBackground(color);
		table.getTableHeader().setFont(new Font("IBM Plex Sans", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.WHITE);

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
			ResultSet rs = (ResultSet) s.executeQuery("select * from administrador where activo =  true;");

			while (rs.next()) {
				row[0] = rs.getInt(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = getAcceso(rs.getBoolean(4));
				model.addRow(row);
			}

			conexion.close();

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}

	public String getAcceso(boolean acceso) {
		String cad = "";
		if (acceso) {
			cad = "Editor";
		} else {
			cad = "Lector";
		}
		return cad;
	}

}
