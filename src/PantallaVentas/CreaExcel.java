package PantallaVentas;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.Empleado;
import modelo.Venta;
import util.ConstantesUtil;
import util.UtilService;

public class CreaExcel {
	private static final FillPatternType CellStyle = null;
	private String [] headers;
	private HSSFWorkbook workbook = new HSSFWorkbook();
	private HSSFSheet sheet;
	private CellStyle headerStyle;
	private CellStyle style;
	private HSSFRow headerRow;
	private GestionService gestionService = new GestionServiceImpl();
	private String path;
	private Configuracion configuracion;
	
	public CreaExcel(List<Venta> listaVenta, List<String> listaCheck){
		configuracion = gestionService.getConfiguracion();
		path = configuracion.getDirectorioFactura();
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet();
		workbook.setSheetName(0, "Listado ventas");
		headerStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//        style.setFillBackgroundColor((short) 22);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
       
		headers = new String [listaCheck.size()];
		int i = 0;
		
//		Creo las cabeceras
		for (String check : listaCheck) {
			if(check.equals(ConstantesUtil.estado)){
				headers[i] = "Estado";
				i++;
			}else if(check.equals(ConstantesUtil.empleadoch)){
				headers[i] = "Empleado";
				i++;
			}else if(check.equals(ConstantesUtil.fecha)){
				headers[i] = "Fecha";
				i++;
			}else if(check.equals(ConstantesUtil.formaPago)){
				headers[i] = "Forma de pago";
				i++;
			}else if(check.equals(ConstantesUtil.importe)){
				headers[i] = "Importe";
				i++;
			}else if(check.equals(ConstantesUtil.iva)){
				headers[i] = "IVA";
				i++;
			}else if(check.equals(ConstantesUtil.numeroVenta)){
				headers[i] = "Número venta";
				i++;
			}else if(check.equals(ConstantesUtil.totalch)){
				headers[i] = "Total";
				i++;
			}
			
		}
		
//		Añado estilo a las cabeceras
		headerRow = sheet.createRow(0);
		for (int j = 0; j < headers.length; ++j) {
			String header = headers[j];
			HSSFCell cell = headerRow.createCell(j);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
		}
		
//		Añado lineas en excel
		for (int j = 0; j < listaVenta.size(); ++j) {
			Venta ventaAux = new Venta();
			HSSFRow dataRow = sheet.createRow(j + 1);
			ventaAux = listaVenta.get(j);
			
			for (int k=0; k < listaCheck.size(); k++) {
				if(listaCheck.get(k).equals(ConstantesUtil.estado)){
					dataRow.createCell(k).setCellValue(listaVenta.get(j).getEstado());
				}else if(listaCheck.get(k).equals(ConstantesUtil.empleadoch)){
					Empleado emp = gestionService.getEmpleadoById(listaVenta.get(j).getEmpleado());
					dataRow.createCell(k).setCellValue(emp.getNombre());
				}else if(listaCheck.get(k).equals(ConstantesUtil.fecha)){
					dataRow.createCell(k).setCellValue(UtilService.obtenerFechaConHHMM(listaVenta.get(j).getFecha()));
				}else if(listaCheck.get(k).equals(ConstantesUtil.formaPago)){
					 if(listaVenta.get(j).getFormaPago() == false){
						 dataRow.createCell(k).setCellValue("Efectivo");
				     }else{
				         dataRow.createCell(k).setCellValue("Tarjeta");
				     }
				}else if(listaCheck.get(k).equals(ConstantesUtil.importe)){
					dataRow.createCell(k).setCellValue(listaVenta.get(j).getImporte().toString());
				}else if(listaCheck.get(k).equals(ConstantesUtil.numeroVenta)){
					dataRow.createCell(k).setCellValue(listaVenta.get(j).getNumero());
				}else if(listaCheck.get(k).equals(ConstantesUtil.totalch)){
					dataRow.createCell(k).setCellValue(listaVenta.get(j).getTotal().toString());
				}else if(listaCheck.get(k).equals(ConstantesUtil.iva)){
					dataRow.createCell(k).setCellValue(String.valueOf(listaVenta.get(j).getIVA()) + " %");
					
				}
				
			}
		}
		
		try {
			File file = new File(path + "/ListadoVentas" + UtilService.obtenerFechaParaPath(new Date()) + ".xls");
			workbook.write(file);
			Desktop.getDesktop().open(file);
			
//			FileOutputStream fileOutPut;
//			fileOutPut = new FileOutputStream("ListadoVentas.xls");
//			
//			workbook.write(fileOutPut);
//			fileOutPut.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
		
		
	}

}
