package Grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import Clases.Funciones;
import Excel.Controlados;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class GenerarReporteControlados extends JFrame {

	private JPanel contentPane;
	private JDateChooser fechaInicio, fechaFinal;
	private Calendar cal = new GregorianCalendar();

	public GenerarReporteControlados() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerarReporteControlados.class.getResource("/icon/exc.png")));
		Color color = new Color(118, 203, 222);
		setTitle("Generar reporte controlados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 286);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el rango de fechas:");
		lblNewLabel.setForeground(color);
		lblNewLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 16));
		lblNewLabel.setBounds(35, 29, 329, 31);
		contentPane.add(lblNewLabel);
		
		JLabel fechaText = new JLabel("Desde:");
		fechaText.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fechaText.setHorizontalAlignment(SwingConstants.LEFT);
		fechaText.setBounds(108, 84, 148, 20);
		contentPane.add(fechaText);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setIcon(new ImageIcon(GenerarReporteControlados.class.getResource("/icon/cancel.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(250,116,112));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnNewButton.setBounds(10, 184, 120, 35);
		contentPane.add(btnNewButton);
		
		JButton btnAgregarProducto = new JButton("Generar reporte");
		btnAgregarProducto.setIcon(new ImageIcon(GenerarReporteControlados.class.getResource("/icon/aceptar.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fechaInicio.getDate().before(fechaFinal.getDate())){
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					Controlados generarReporte = new Controlados();
					try {
						generarReporte.reporte(formato.format(fechaInicio.getDate()), formato.format(fechaFinal.getDate()));
						dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Ocurrió un error, intente nuevamente", "Error", 0, null);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Ingrese las fechas correctamente", "Advertencia", 2, null);
				}
			}
		});
		btnAgregarProducto.setForeground(Color.WHITE);
		btnAgregarProducto.setFont(new Font("IBM Plex Sans", Font.BOLD, 13));
		btnAgregarProducto.setBackground(color);
		btnAgregarProducto.setBounds(363, 184, 159, 35);
		contentPane.add(btnAgregarProducto);
		
		fechaInicio = new JDateChooser();
		fechaInicio.getCalendarButton().setBackground(Color.WHITE);
		fechaInicio.getCalendarButton().setIcon(new ImageIcon(GenerarReporteControlados.class.getResource("/icon/calP.png")));
		fechaInicio.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fechaInicio.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fechaInicio.getJCalendar().setWeekOfYearVisible(false);
		fechaInicio.getJCalendar().setDecorationBackgroundColor(color);
		fechaInicio.getJCalendar().setWeekdayForeground(Color.black);
		fechaInicio.getJCalendar().setTodayButtonVisible(true);
		fechaInicio.getJCalendar().setBackground(Color.WHITE);
		fechaInicio.setBounds(156, 81, 228, 25);
		contentPane.add(fechaInicio);
		fechaInicio.setCalendar(cal);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
		lblHasta.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		lblHasta.setBounds(108, 133, 148, 20);
		contentPane.add(lblHasta);
		
		fechaFinal = new JDateChooser();
		fechaFinal.getCalendarButton().setBackground(Color.WHITE);
		fechaFinal.setBounds(156, 130, 228, 25);
		fechaFinal.getCalendarButton().setIcon(new ImageIcon(GenerarReporteControlados.class.getResource("/icon/calP.png")));
		fechaFinal.setFont(new Font("IBM Plex Sans", Font.PLAIN, 13));
		fechaFinal.getJCalendar().setFont(new Font("IBM Plex Sans", Font.PLAIN, 12));
		fechaFinal.getJCalendar().setWeekOfYearVisible(false);
		fechaFinal.getJCalendar().setDecorationBackgroundColor(color);
		fechaFinal.getJCalendar().setWeekdayForeground(Color.black);
		fechaFinal.getJCalendar().setTodayButtonVisible(true);
		fechaFinal.getJCalendar().setBackground(Color.WHITE);
		contentPane.add(fechaFinal);
		fechaFinal.setCalendar(cal);
	}
	public void limpiar() {
		fechaInicio.setCalendar(cal);
		fechaFinal.setCalendar(cal);
	}
}
