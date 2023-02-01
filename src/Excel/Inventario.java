package Excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Inventario {

	@SuppressWarnings("deprecation")
	public  void reporte() throws InstantiationException, IllegalAccessException {

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Inventario");

        try {        
        	
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setColor(IndexedColors.LIME.getIndex());
            fuenteTitulo.setFontName("Bahnschrift");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 20);
            tituloEstilo.setFont(fuenteTitulo);
            
            Date fecha = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-YYYY");
            String fechaFormato = formato.format(fecha);
            
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("INVENTARIO "+fechaFormato);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 12));

            String[] cabecera = new String[]{"PRODUCTO", "PRESENTACIÓN","LABORATORIO", "CONTENIDO","VENCIMIENTO", "STOCK CAJAS", "STOCK UNIDADES","PRECIO CAJA","PRECIO UNIDADES","VALOR CAJAS", "VALOR UNIDADES","VALOR TOTAL"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIME.getIndex());
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
            fuentefecha.setColor(IndexedColors.LIME.getIndex());
            fuentefecha.setFontName("Bahnschrift SemiLight");
            fuentefecha.setFontHeightInPoints((short) 12);
            fechaEstilo.setFont(fuentefecha);
            celdaFecha.setCellStyle(fechaEstilo);
            
            
            celdaFecha.setCellValue("Fecha: "+fechaFormato);
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
			ResultSet rs = (ResultSet) s.executeQuery("select a.nombre, b.descripcion,c.laboratorio,a.contenido,a.fechavencimiento,"
					+ " a.stockcaja, a.stockunidad, a.preciocaja, a.preciounitario from producto a, "
					+ "presentacion b, laboratorio c where a.presentacion_codpres = b.codpres and c.codlab = a.laboratorio"
					+ " and a.activo = true order by c.laboratorio;");

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 1; a <= numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);

                    if (a == 6 || a == 4 || a==7||a==8||a==9) {
                        CeldaDatos.setCellValue(rs.getDouble(a));
                    } else {
                    	
                    		CeldaDatos.setCellValue(rs.getString(a));
                    	
                        
                    }
                }
                Cell celdaImporteC = filaDatos.createCell(10);
                celdaImporteC.setCellStyle(datosEstilo);
                celdaImporteC.setCellFormula(String.format("G%d*I%d", numFilaDatos + 1, numFilaDatos + 1));
                

                Cell celdaImporte = filaDatos.createCell(11);
                celdaImporte.setCellStyle(datosEstilo);
                celdaImporte.setCellFormula(String.format("H%d*J%d", numFilaDatos + 1, numFilaDatos + 1));
                
                
                Cell celdaImporteT = filaDatos.createCell(12);
                celdaImporteT.setCellStyle(datosEstilo);
                celdaImporteT.setCellFormula(String.format("L%d+K%d", numFilaDatos + 1, numFilaDatos + 1));

                numFilaDatos++;

            }
            Row filaDatos = sheet.createRow(numFilaDatos);
            Cell CeldaDatos = filaDatos.createCell(12);
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
            CeldaDatos.setCellFormula(String.format("SUM(M7:M"+fila+")", numFilaDatos + 1, numFilaDatos + 1));
            Cell CeldaTotal = filaDatos.createCell(11);
            CeldaTotal.setCellStyle(headerStyle);
            CeldaTotal.setCellValue("Total: ");
            
            for(int i=1;i<=13;i++) {
            	sheet.autoSizeColumn(i);
            }
          
            sheet.setZoom(100);
         
            comprobar();
            String nombre = "Inventario_"+fechaFormato;
            String ruta ="C:\\Reportes\\Inventario\\"+nombre+".xlsx";
            FileOutputStream fileOut = new FileOutputStream(ruta);
            book.write(fileOut);
            fileOut.close();
            if(new File(ruta).exists()){
				JOptionPane.showMessageDialog(null, nombre + " creado correctamente!","",-1,new ImageIcon(VentaR.class.getResource("/icon/excel (1).png")));
				abrir(ruta);
			}

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void comprobar() {
		File carpeta = new File("C:\\Reportes\\Inventario");
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
