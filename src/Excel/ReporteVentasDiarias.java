package Excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Clases.Conexion;
import Grafico.VentaR;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReporteVentasDiarias {


    @SuppressWarnings("deprecation")
	public void reporte(String fecha) throws InstantiationException, IllegalAccessException {

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Ventas del día");

        try {        
        	InputStream is = new FileInputStream("src\\icon\\compra2.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            @SuppressWarnings("rawtypes")
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(7);
            anchor.setRow1(4);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(4, 12);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setColor(IndexedColors.ROYAL_BLUE.getIndex());
            fuenteTitulo.setFontName("Bahnschrift");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 20);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Ventas del día");
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 5));

            String[] cabecera = new String[]{"Cantidad", "Tipo", "Producto", "Precio unitario", "Total"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

            Font font = book.createFont();
            font.setFontName("Bahnschrift");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
            
            Row filaFecha = sheet.createRow(3);
            Cell celdaFecha = filaFecha.createCell(1);
            CellStyle fechaEstilo = book.createCellStyle();
            Font fuentefecha = book.createFont();
            fuentefecha.setColor(IndexedColors.ROYAL_BLUE.getIndex());
            fuentefecha.setFontName("Bahnschrift SemiLight");
            fuentefecha.setFontHeightInPoints((short) 12);
            fechaEstilo.setFont(fuentefecha);
            celdaFecha.setCellStyle(fechaEstilo);
            celdaFecha.setCellValue("Fecha: "+fecha);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 3));
            
            Row filaEncabezados = sheet.createRow(5);

            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i+1);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }

            

            int numFilaDatos = 6;

            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setAlignment(CellStyle.ALIGN_CENTER);
            datosEstilo.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            datosEstilo.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            datosEstilo.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            datosEstilo.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            Font fuenteDatos = book.createFont();
            fuenteDatos.setColor(IndexedColors.BLACK.getIndex());
            fuenteDatos.setFontName("Bahnschrift SemiLight");
            fuenteDatos.setFontHeightInPoints((short) 10);
            datosEstilo.setFont(fuenteDatos);
            
            Conexion con = new Conexion();
			Connection conexion;
			conexion = (Connection) con.Conectar();
			Statement s = (Statement) conexion.createStatement();
			ResultSet rs = (ResultSet) s.executeQuery("select a.cantidad, a.tipo, b.nombre, a.precio from venta_producto a, producto b, venta c\n"
					+ "where b.cod = a.producto_cod and c.codventa = a.venta_codventa and c.fecha = '"+fecha+"';");

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 1; a <= numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);

                    if (a == 1 || a == 4) {
                        CeldaDatos.setCellValue(rs.getDouble(a));
                    } else {
                    	if(a==2) {
                    		String tipo = "";
                    		if(rs.getString(2).equals("U")) {
                    			tipo ="Unidad";
                    		}else {
                    			tipo = "Caja";
                    		}
                    		CeldaDatos.setCellValue(tipo);
                    	}else {
                    		CeldaDatos.setCellValue(rs.getString(a));
                    	}
                        
                    }
                }

                Cell celdaImporte = filaDatos.createCell(5);
                celdaImporte.setCellStyle(datosEstilo);
                celdaImporte.setCellFormula(String.format("B%d*E%d", numFilaDatos + 1, numFilaDatos + 1));

                numFilaDatos++;

            }
            Row filaDatos = sheet.createRow(numFilaDatos);
            Cell CeldaDatos = filaDatos.createCell(5);
            String fila = String.valueOf(numFilaDatos);
            CellStyle style = book.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setFont(fuenteDatos);
            DataFormat format = book.createDataFormat();
            style.setDataFormat(format.getFormat("[$Bs.-quz-BO] #,##0.00"));
            CeldaDatos.setCellStyle(style);
            
            CeldaDatos.setCellFormula(String.format("SUM(F7:F"+fila+")", numFilaDatos + 1, numFilaDatos + 1));
            Cell CeldaTotal = filaDatos.createCell(4);
            CeldaTotal.setCellStyle(headerStyle);
            CeldaTotal.setCellValue("Total: ");
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(4);
            sheet.setColumnWidth(5,5000);
            sheet.setColumnWidth(3,7000);
            sheet.setColumnWidth(2,4000);
            sheet.setZoom(100);
            
            comprobar();
            String nombre = "Ventas_ "+fecha;
            String ruta ="C:\\Reportes\\VentasDiarias\\"+nombre+".xlsx";
            FileOutputStream fileOut = new FileOutputStream(ruta);
            book.write(fileOut);
            fileOut.close();
            if(new File(ruta).exists()){
				JOptionPane.showMessageDialog(null, nombre + " creado correctamente!","",-1,new ImageIcon(VentaR.class.getResource("/icon/file (2).png")));
				abrir(ruta);
			}

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteVentasDiarias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReporteVentasDiarias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReporteVentasDiarias.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void comprobar() {
		File carpeta = new File("C:\\Reportes\\VentasDiarias");
		if(!carpeta.exists()) {
			carpeta.mkdir();
		}
	}
    public void abrir(String nombre) {
		try {
			File path = new File(nombre);
			Desktop.getDesktop().open(path);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo :c "+e);
		}
	}

}
