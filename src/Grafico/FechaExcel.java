package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import Excel.ReporteVentasDiarias;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class FechaExcel extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JDateChooser fecha;
	private Calendar cal = new GregorianCalendar();
	public FechaExcel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FechaExcel.class.getResource("/icon/excel (1).png")));
		setResizable(false);
		Color color = new Color(89, 217, 206);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 199);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fecha = new JDateChooser();
		fecha.getCalendarButton().setBackground(Color.WHITE);
		fecha.getCalendarButton().setIcon(new ImageIcon(FechaExcel.class.getResource("/icon/calBG.png")));
		fecha.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		fecha.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fecha.getJCalendar().setWeekOfYearVisible(false);
		fecha.getJCalendar().setDecorationBackgroundColor(color);
		fecha.getJCalendar().setWeekdayForeground(Color.black);
		fecha.getJCalendar().setTodayButtonVisible(true);
		fecha.getJCalendar().setBackground(Color.WHITE);
		fecha.setBounds(224, 57, 182, 23);
		contentPane.add(fecha);
		fecha.setCalendar(cal);

		JTextPane msj = new JTextPane();
		msj.setEditable(false);
		msj.setText("Seleccione la fecha para el reporte de ventas:");
		msj.setForeground(color);
		msj.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		msj.setBounds(22, 11, 384, 35);
		contentPane.add(msj);

		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(FechaExcel.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250, 116, 112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(22, 107, 115, 35);
		contentPane.add(btnNewButton);

		JButton btnAgregarProducto = new JButton("Generar Excel");
		btnAgregarProducto.setIcon(new ImageIcon(FechaExcel.class.getResource("/icon/aceptar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
					String fechaDato = format.format(fecha.getDate());
					ReporteVentasDiarias reporte = new ReporteVentasDiarias();
					reporte.reporte(fechaDato);
					
				}catch (Exception error) {
					JOptionPane.showMessageDialog(null,"Ingrese la fecha correctamente", "Advertencia", 2, null);
				}
			}
		});

		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(357, 107, 149, 35);
		contentPane.add(btnAgregarProducto);
		Object[] columnas = { "COD", "FECHA", "DESCRIPCION" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnas);
		JLabel lblNewLabel = new JLabel("Ingrese una fecha:");
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.PLAIN, 14));
		lblNewLabel.setBounds(76, 60, 138, 20);
		contentPane.add(lblNewLabel);

	}

}
