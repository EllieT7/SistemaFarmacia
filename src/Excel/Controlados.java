package Excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Clases.Conexion;
import Grafico.VentaR;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aspose.cells.*;

public class Controlados {
	

	@SuppressWarnings("deprecation")
	public void reporte(String fecha_inicial, String fecha_final) throws Exception {

		// Creando libro :D
		@SuppressWarnings("resource")
		Workbook book = new XSSFWorkbook();

		// Lista con códigos de productos controlados
		ArrayList<Integer> productosControlados = getProductosControlados();

		for (int i = 0; i < productosControlados.size(); i++) {
			int codProducto = productosControlados.get(i);
			// Obtenemos los datos del producto
			ArrayList<String> arrayDatosProducto = getDatosProducto(codProducto);
			// Crendo primera hoja
			Sheet sheet = book.createSheet(arrayDatosProducto.get(0));
			try {

				// Estilo Etiquetas
				CellStyle etiquetasStyle = book.createCellStyle();
				etiquetasStyle.setAlignment(HorizontalAlignment.LEFT);
				etiquetasStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				Font fuenteEtiquetas = book.createFont();
				fuenteEtiquetas.setColor(IndexedColors.BLACK.getIndex());
				fuenteEtiquetas.setFontName("Calibri");
				fuenteEtiquetas.setBold(true);
				fuenteEtiquetas.setFontHeightInPoints((short) 9);
				etiquetasStyle.setFont(fuenteEtiquetas);

				// Estilo contenido
				CellStyle contenidoStyle = book.createCellStyle();
				contenidoStyle.setAlignment(HorizontalAlignment.LEFT);
				contenidoStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				Font fuenteContenido = book.createFont();
				fuenteContenido.setColor(IndexedColors.BLACK.getIndex());
				fuenteContenido.setFontName("Calibri");
				fuenteContenido.setFontHeightInPoints((short) 9);
				contenidoStyle.setFont(fuenteContenido);

				// Trabajando fila 1
				Row filaEtiquetas1 = sheet.createRow(1);
				Cell celdaNombre = filaEtiquetas1.createCell(2);
				celdaNombre.setCellStyle(etiquetasStyle);
				celdaNombre.setCellValue("NOMBRE:");

				Cell celdaNombreContenido = filaEtiquetas1.createCell(4);
				celdaNombreContenido.setCellStyle(contenidoStyle);
				celdaNombreContenido.setCellValue(arrayDatosProducto.get(0));

				Cell celdaConcentracion = filaEtiquetas1.createCell(6);
				celdaConcentracion.setCellStyle(etiquetasStyle);
				celdaConcentracion.setCellValue("CONCENTRACIÓN:");

				Cell celdaConcentracionContenido = filaEtiquetas1.createCell(7);
				celdaConcentracionContenido.setCellStyle(contenidoStyle);
				celdaConcentracionContenido.setCellValue(arrayDatosProducto.get(3));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));

				// Trabajando fila 2
				Row filaEtiquetas2 = sheet.createRow(2);
				Cell celdaDCI = filaEtiquetas2.createCell(2);
				celdaDCI.setCellStyle(etiquetasStyle);
				celdaDCI.setCellValue("DCI:");

				Cell celdaDCIContenido = filaEtiquetas2.createCell(4);
				celdaDCIContenido.setCellStyle(contenidoStyle);
				celdaDCIContenido.setCellValue(arrayDatosProducto.get(1));

				Cell celdaPresentacion = filaEtiquetas2.createCell(6);
				celdaPresentacion.setCellStyle(etiquetasStyle);
				celdaPresentacion.setCellValue("PRESENTACIÓN:");

				Cell celdaPresentacionContenido = filaEtiquetas2.createCell(7);
				celdaPresentacionContenido.setCellStyle(contenidoStyle);
				celdaPresentacionContenido.setCellValue(arrayDatosProducto.get(4));
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));

				// Trabajando fila 3
				Row filaEtiquetas3 = sheet.createRow(3);
				Cell celdaLaboratorio = filaEtiquetas3.createCell(2);
				celdaLaboratorio.setCellStyle(etiquetasStyle);
				celdaLaboratorio.setCellValue("LABORATORIO:");

				Cell celdaLaboratorioContenido = filaEtiquetas3.createCell(4);
				celdaLaboratorioContenido.setCellStyle(contenidoStyle);
				celdaLaboratorioContenido.setCellValue(arrayDatosProducto.get(2));

				Cell celdaOrigen = filaEtiquetas3.createCell(6);
				celdaOrigen.setCellStyle(etiquetasStyle);
				celdaOrigen.setCellValue("ORIGEN:");

				Cell celdaOrigenContenido = filaEtiquetas3.createCell(7);
				celdaOrigenContenido.setCellStyle(contenidoStyle);
				celdaOrigenContenido.setCellValue(arrayDatosProducto.get(5));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

				String[] cabecera = new String[] { "Fecha", "Cantidad Ingreso", "No Factura", "Nombre Paciente",
						"C.I. Paciente", "Nombre Médico", "No Receta", "Cantidad Egreso", "Saldo Anterior",
						"Saldo Actual", "Observaciones" };
				XSSFCellStyle headerStyle = (XSSFCellStyle) book.createCellStyle();
				XSSFColor color = new XSSFColor(new java.awt.Color(198, 224, 180));
				headerStyle.setFillForegroundColor(color);
				headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerStyle.setBorderTop(BorderStyle.THIN);
				headerStyle.setBorderLeft(BorderStyle.THIN);
				headerStyle.setBorderRight(BorderStyle.THIN);
				headerStyle.setBorderBottom(BorderStyle.THIN);
				headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
				headerStyle.setWrapText(true);
				headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

				Font font = book.createFont();
				font.setFontName("Calibri");
				font.setBold(true);
				font.setColor(IndexedColors.BLACK.getIndex());
				font.setFontHeightInPoints((short) 8.5);
				headerStyle.setFont(font);

				// Estableciendo anchos fijos
				sheet.setColumnWidth((short) 1, (short) 2220); // 8
				sheet.setColumnWidth((short) 2, (short) 1720); // 6
				sheet.setColumnWidth((short) 3, (short) 1720); // 6
				sheet.setColumnWidth((short) 4, (short) 4550); // 17
				sheet.setColumnWidth((short) 5, (short) 1970); // 7
				sheet.setColumnWidth((short) 6, (short) 4280); // 16
				sheet.setColumnWidth((short) 7, (short) 1450); // 5
				sheet.setColumnWidth((short) 8, (short) 1720); // 6
				sheet.setColumnWidth((short) 9, (short) 1720); // 6
				sheet.setColumnWidth((short) 10, (short) 1720); // 6
				sheet.setColumnWidth((short) 11, (short) 2750); // 10

				Row filaEncabezados = sheet.createRow(5);
				// filaEncabezados.setHeight((short)675);
				for (int i1 = 0; i1 < cabecera.length; i1++) {
					Cell celdaEnzabezado = filaEncabezados.createCell(i1 + 1);
					celdaEnzabezado.setCellStyle(headerStyle);
					celdaEnzabezado.setCellValue(cabecera[i1]);
				}

				int numFilaDatos = 6;

				CellStyle datosEstilo = book.createCellStyle();
				datosEstilo.setBorderBottom(BorderStyle.THIN);
				datosEstilo.setBorderLeft(BorderStyle.THIN);
				datosEstilo.setBorderRight(BorderStyle.THIN);
				datosEstilo.setBorderBottom(BorderStyle.THIN);
				datosEstilo.setAlignment(CellStyle.ALIGN_CENTER);
				datosEstilo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				datosEstilo.setTopBorderColor(IndexedColors.BLACK.getIndex());
				datosEstilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				datosEstilo.setRightBorderColor(IndexedColors.BLACK.getIndex());
				Font fuenteDatos = book.createFont();
				fuenteDatos.setColor(IndexedColors.BLACK.getIndex());
				fuenteDatos.setFontName("Calibri");
				fuenteDatos.setFontHeightInPoints((short) 8);
				datosEstilo.setFont(fuenteDatos);

				// Formato fecha
				DataFormat formatDate = book.createDataFormat();
				CellStyle style = book.createCellStyle();
				style.setDataFormat(formatDate.getFormat("dd/mm/yyyy"));
				style.setFont(fuenteDatos);
				style.setBorderBottom(BorderStyle.THIN);
				style.setBorderLeft(BorderStyle.THIN);
				style.setBorderRight(BorderStyle.THIN);
				style.setBorderBottom(BorderStyle.THIN);
				style.setAlignment(CellStyle.ALIGN_CENTER);
				style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				style.setTopBorderColor(IndexedColors.BLACK.getIndex());
				style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				style.setRightBorderColor(IndexedColors.BLACK.getIndex());

				Conexion con = new Conexion();
				Connection conexion;
				conexion = (Connection) con.Conectar();
				Statement s = (Statement) conexion.createStatement();
				ResultSet rs = (ResultSet) s.executeQuery(
						"select a.tiempo_actual, a.nombre, a.cliente_ci, b.medico, b.receta, b.cantidad, b.observaciones, b.tipo, c.contenido from venta a, venta_producto b, producto c "
								+ "where c.controlado=true and c.cod = b.producto_cod and b.venta_codventa = a.codVenta and c.cod="
								+ codProducto + " and FORMATDATETIME(a.tiempo_actual,'yyyy-MM-dd')  between '"
								+ fecha_inicial + "' and '" + fecha_final + "' order by a.tiempo_actual;");

				int numCol = rs.getMetaData().getColumnCount() - 2;

				while (rs.next()) {
					String tipo = rs.getString(numCol + 1);
					int contenido = rs.getInt(numCol + 2);

					Row filaDatos = sheet.createRow(numFilaDatos);
					int nroColumnas = 1;
					for (int a = 1; a <= numCol; a++) {

						Cell CeldaDatos = filaDatos.createCell(nroColumnas);

						CeldaDatos.setCellStyle(datosEstilo);

						if (nroColumnas != 2 && nroColumnas != 3 && nroColumnas != 9 && nroColumnas != 10) {
							if (a == 6) {
								double cantidad = rs.getDouble(a);
								if (tipo.equalsIgnoreCase("C")) {
									cantidad *= contenido;
								}
								CeldaDatos.setCellValue(cantidad);
							} else {
								if (a == 1) {
									Timestamp cadena = rs.getTimestamp(a);
									CeldaDatos.setCellValue(cadena);
									CeldaDatos.setCellStyle(style);

								} else {
									CeldaDatos.setCellValue(rs.getString(a));
								}

							}

						} else {
							if (nroColumnas == 10) {
								CeldaDatos.setCellFormula(String.format("J%d+C%d-I%d", numFilaDatos + 1,
										numFilaDatos + 1, numFilaDatos + 1));
							} else {
								if (nroColumnas == 9) {
									a--;
									CeldaDatos.setCellFormula(String.format("K%d", numFilaDatos));
								}

								a--;

							}

						}

						nroColumnas++;
					}

					numFilaDatos++;

				}
				conexion.close();

				// salidas
				Conexion con1 = new Conexion();
				Connection conexion1;
				conexion1 = (Connection) con1.Conectar();
				Statement s1 = (Statement) conexion1.createStatement();
				ResultSet rs1 = (ResultSet) s1.executeQuery("\n"
						+ "select a.tiempo_actual, b.cantidadUni + (b.cantidadCaja*d.contenido), a.nrofactura, c.laboratorio from compra a, compra_producto b, laboratorio c, producto d \n"
						+ "where a.codcompra = b.compra_codcompra and c.codlab = a.laboratorio_codlab and d.cod = b.producto_cod and d.cod ="
						+ codProducto + " and FORMATDATETIME(a.tiempo_actual,'yyyy-MM-dd')  between '" + fecha_inicial
						+ "' and '" + fecha_final + "' order by a.tiempo_actual;");

				int numCol1 = rs1.getMetaData().getColumnCount();
				
				while (rs1.next()) {
					Row filaDatos = sheet.createRow(numFilaDatos);

					for (int a = 1; a <= numCol1; a++) {

						Cell CeldaDatos = filaDatos.createCell(a);
						CeldaDatos.setCellStyle(datosEstilo);

						if (a == 2) {
							CeldaDatos.setCellValue(rs1.getDouble(a));
						} else {
							if (a == 1) {
								Timestamp cadena = rs1.getTimestamp(a);
								CeldaDatos.setCellValue(cadena);
								CeldaDatos.setCellStyle(style);

							} else {
								CeldaDatos.setCellValue(rs1.getString(a));
							}

						}
						for (int i1 = 5; i1 <= 11; i1++) {
							Cell CeldaDatosExtra = filaDatos.createCell(i1);
							CeldaDatosExtra.setCellStyle(datosEstilo);
							if (i1 == 10) {
								CeldaDatosExtra.setCellFormula(String.format("J%d+C%d-I%d", numFilaDatos + 1,
										numFilaDatos + 1, numFilaDatos + 1));
							} else {
								if (i1 == 9) {

									CeldaDatosExtra.setCellFormula(String.format("K%d", numFilaDatos));
								}
							}
						}

					}

					numFilaDatos++;

				}
				conexion1.close();

				CellStyle style1 = book.createCellStyle();
				style1.setBorderBottom(BorderStyle.THIN);
				style1.setBorderLeft(BorderStyle.THIN);
				style1.setBorderRight(BorderStyle.THIN);
				style1.setBorderBottom(BorderStyle.THIN);
				style1.setAlignment(CellStyle.ALIGN_CENTER);
				style1.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
				style1.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
				style1.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
				style1.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
				style1.setFont(fuenteDatos);
				sheet.setZoom(100);
				Row row = sheet.getRow(6);
				Cell cell = row.getCell(9);
				cell.setCellFormula(getStockInicial(fecha_inicial, codProducto) + "");
				System.out.println();

			} catch (SQLException ex) {
				Logger.getLogger(Controlados.class.getName()).log(Level.SEVERE, null, ex);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		Date fechaInicial = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_inicial);
		Date fechaFinal = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_final);
		SimpleDateFormat formato = new SimpleDateFormat("dd.MM.YY");
		String fechaInicialFormato = formato.format(fechaInicial);
		String fechaFinalFormato = formato.format(fechaFinal);

		comprobar();
		String nombre = "ReporteControlados_" + fechaInicialFormato + "_" + fechaFinalFormato;
		String ruta = "C:\\Reportes\\Controlados\\" + nombre + ".xlsx";
		FileOutputStream fileOut = new FileOutputStream(ruta);
		book.write(fileOut);
		for (int j = 0; j < productosControlados.size(); j++) {
			int codProducto = productosControlados.get(j);
			// Obtenemos los datos del producto
			ArrayList<String> arrayDatosProducto = getDatosProducto(codProducto);
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook(ruta);
			workbook.calculateFormula();
			DataSorter sorter = workbook.getDataSorter();
			Worksheet worksheet = workbook.getWorksheets().get(arrayDatosProducto.get(0));
			Cells cells = worksheet.getCells();

			sorter.addKey(1, SortOrder.ASCENDING);
			CellArea ca1 = CellArea.createCellArea("B7", "L500");
			sorter.sort(cells, ca1);

			workbook.save(ruta);
			System.out.println("listo");

		}

		fileOut.close();

		if (new File(ruta).exists()) {

			// Open file
			FileInputStream inputStream = new FileInputStream(new File(ruta));
			@SuppressWarnings("resource")
			XSSFWorkbook workBook = new XSSFWorkbook(inputStream);

			// Delete Sheet
			for (int i = 0; i < productosControlados.size(); i++) {
				String nombreHoja = "Evaluation Warning";
				if (i != 0) {
					nombreHoja = nombreHoja + " (" + i + ")";
				}
				workBook.removeSheetAt(workBook.getSheetIndex(nombreHoja));
			}

			// Save the file
			FileOutputStream outFile = new FileOutputStream(new File(ruta));
			workBook.write(outFile);
			outFile.close();
			JOptionPane.showMessageDialog(null, nombre + " creado correctamente!", "", -1,
					new ImageIcon(VentaR.class.getResource("/icon/excel (1).png")));
			abrir(ruta);
		}

	}

	public void comprobar() {
		File carpeta = new File("C:\\Reportes\\Controlados");
		if (!carpeta.exists()) {
			carpeta.mkdir();
		}
	}

	public void abrir(String nombre) {
		try {
			File path = new File(nombre);
			Desktop.getDesktop().open(path);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo :c " + e);
		}
	}

	public ArrayList<String> getDatosProducto(int codProd) {
		ArrayList<String> datos = new ArrayList<>();
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery(
					"select a.nombre, a.dci, b.laboratorio, a.concentracion, c.descripcion, a.origen from producto a, laboratorio b, presentacion c\n"
							+ "where a.presentacion_codPres = c.codPres and a.laboratorio =b.codLab and a.cod="
							+ codProd);
			while (rs.next()) {
				for (int i = 1; i <= 6; i++) {
					datos.add(rs.getString(i));
				}

			}
			conexion.close();
		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return datos;
	}

	public int getStockInicial(String fechaInicio, int codProducto) {
		int stock = 0;
		int stockVenta = 0, stockCompra = 0;
		Timestamp fechaVenta = null, fechaCompra = null;
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery(
					"select a.tiempo_actual, b.producto_cod, b.stockuniactual, b.stockCajaActual, c.contenido from venta a, venta_producto b, producto c "
							+ "where c.controlado=true and c.cod = b.producto_cod and b.venta_codventa = a.codVenta and c.cod="
							+ codProducto + "and FORMATDATETIME(a.tiempo_actual,'yyyy-MM-dd') >= '" + fechaInicio
							+ "' order by a.tiempo_actual;");

			while (rs.next()) {
				fechaVenta = rs.getTimestamp(1);
				stockVenta = rs.getInt(3) + (rs.getInt(4) * rs.getInt(5));
				break;
			}
			conexion.close();

			Conexion con1 = new Conexion();
			Connection conexion1;
			conexion1 = (Connection) con1.Conectar();
			Statement s1 = (Statement) conexion1.createStatement();
			ResultSet rs1 = (ResultSet) s1.executeQuery(
					"select a.tiempo_actual, b.producto_cod, b.stockuniactual, b.stockCajaActual, c.contenido from compra a, compra_producto b, producto c "
							+ "where c.controlado=true and c.cod = b.producto_cod and b.compra_codCompra = a.codCompra and c.cod="
							+ codProducto + "and FORMATDATETIME(a.tiempo_actual,'yyyy-MM-dd') >= '" + fechaInicio
							+ "' order by a.tiempo_actual;");

			while (rs1.next()) {
				fechaCompra = rs1.getTimestamp(1);
				stockCompra = rs1.getInt(3) + (rs1.getInt(4) * rs1.getInt(5));
				break;
			}
			conexion1.close();
			if (fechaVenta != null && fechaCompra != null) {
				if (fechaVenta.after(fechaCompra)) {
					stock = stockCompra;
				} else {
					stock = stockVenta;
				}
			} else if (fechaVenta == null) {
				stock = stockCompra;
			} else {
				stock = stockVenta;
			}

		} catch (InstantiationException | SQLException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return stock;
	}

	public ArrayList<Integer> getProductosControlados() {
		ArrayList<Integer> listaControlados = new ArrayList<>();
		try {
			Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select * from producto where controlado=true;");

			while (rs.next()) {
				listaControlados.add(rs.getInt(1));
			}
			System.out.println(listaControlados);
			conexion.close();

		} catch (Exception e) {
			System.out.println("error " + e);
		}
		return listaControlados;
	}

}
