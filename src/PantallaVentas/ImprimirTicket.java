package PantallaVentas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.LineaVenta;
import util.ConstantesUtil;
import util.IVAEnum;
import util.UtilService;


public class ImprimirTicket {
	private List<LineaVenta> lista = null;
	private Configuracion configuracion;
	private GestionService gestionService = new GestionServiceImpl();
	private AppService appService = new AppServiceImpl();
	private String direccion;
	private List<TuplaDatos> ListaTuplaDatos;
	private String path, numero, numeroDigito;
	private IVAEnum iva = IVAEnum.VEINTIUNO;
	private Float base = 0f,dividendo = 1f;
	
	
	private List<String> datosTicket = new ArrayList<>();
	
	
	public ImprimirTicket(List<LineaVenta> listaLineas, Long idLVR, boolean formaPago){
	    PrinterMatrix printer = new PrinterMatrix();
	    
	    Extenso e = new Extenso();
	    numeroDigito = appService.obtenerNumeroVenta() != null ? String.valueOf(appService.obtenerNumeroVenta()): "1";
	    numero = "Número: " + numeroDigito;
	    configuracion = gestionService.getConfiguracion();
		if(configuracion != null){
			path = configuracion.getDirectorioFactura();
		}
	 
	    e.setNumber(101.85);  
	    lista = listaLineas;
	    ListaTuplaDatos = appService.obtenerIdProductoCantidadById(idLVR);
	    
	  
	    //Defio el tamaño del papel para la impresion  aca 25 lineas y 80 columnas
	    printer.setOutSize(lista.size()+38, 48);
	    //Imprimir * de la 2da linea a 25 en la columna 1;
	   // printer.printCharAtLin(2, 25, 1, "*");
	    //Imprimir * 1ra linea de la columa de 1 a 80
	   printer.printCharAtCol(1, 1, 48, "=");
	    //Imprimir Encabezado nombre del La EMpresa
	   printer.printTextWrap(2, 2, 16, 48, "TICKET DE COMPRA");
	   //printer.printTextWrap(linI, linE, colI, colE, null);
	   printer.printTextWrap(3, 3, 1, 13, numero);
	   Date hoy = new Date();
	   
	   String fecha = UtilService.obtenerFecha(hoy);
	   String hora = UtilService.obtenerHora(hoy);
	   String empleado = UtilService.getEmpleadoLogado().getNombre();
	   String nombreEmpresa = configuracion.getNombreEmpresa() != null ? configuracion.getNombreEmpresa() : null;
	   String direccionEmpresa = configuracion.getDireccionEmpresa() != null ? configuracion.getDireccionEmpresa() : null;
	   String cifNif = configuracion.getCifNif() != null ? configuracion.getCifNif() : null;
	   String telefonoEmpresa = configuracion.getTelefonoEmpresa() != null ? configuracion.getTelefonoEmpresa() : null;
	   
	   datosTicket.add(fecha);
	   datosTicket.add(hora);
	   datosTicket.add(empleado);
	   datosTicket.add(nombreEmpresa);
	   datosTicket.add(direccionEmpresa);
	   datosTicket.add(cifNif);
	   datosTicket.add(telefonoEmpresa);
	   datosTicket.add(numeroDigito);
	   
	   printer.printTextWrap(3, 3, 13, 30, "Fecha: " + fecha);
	   printer.printTextWrap(3, 3, 33, 48, "Hora: " + hora);
	   printer.printTextWrap(4, 4, 1, 48, "Atendido por: "+ empleado);
	   printer.printTextWrap(5, 5, 1, 48, nombreEmpresa != null ? "Empresa: " + nombreEmpresa : "Empresa: ");		
	   printer.printTextWrap(6, 6, 1, 48, direccionEmpresa != null ? "Dirección: " + direccionEmpresa : "Dirección: " );
	   printer.printTextWrap(7, 7, 1, 48, cifNif != null ?  "CIF/NIF: " + cifNif : "CIF/NIF: " );
	   printer.printTextWrap(8, 8, 1, 48, telefonoEmpresa != null ? "Teléfono: " + telefonoEmpresa: "Teléfono: ");
	   printer.printCharAtCol(10, 1, 48, "=");
	   //									0						24
	   printer.printTextWrap(10, 11,1, 48, "Producto    Cantidad    Precio    P.Total");
	   printer.printCharAtCol(12, 1, 48, "-");
	   
	   
	   
	   List<BigDecimal> listaBigDecimal = new ArrayList<>();
	    for (LineaVenta lineaVenta : lista) {
			BigDecimal bd = lineaVenta.getTotal();
			listaBigDecimal.add(bd);
		}
	    BigDecimal aux = BigDecimal.ZERO;
		for (BigDecimal numero : listaBigDecimal) {
			aux = aux.add(numero);
		}
		Long idTicket = appService.crearTicket(fecha, hora, empleado, nombreEmpresa, direccionEmpresa, cifNif, telefonoEmpresa,numeroDigito, formaPago, aux, false);
	   int filas = ListaTuplaDatos.size();
	   BigDecimal totalIva = new BigDecimal(iva.getCodigo());
	   totalIva = totalIva.divide(new BigDecimal(100)).add(new BigDecimal(1));
	   int i=0;
	   for (TuplaDatos tupla : ListaTuplaDatos) {		
		   base = tupla.getPrecioProducto().floatValue();
		   dividendo = totalIva.floatValue();
		   base = base/dividendo;
		   BigDecimal baseImponible = new BigDecimal(base);
		   printer.printTextWrap(13 + i, 13, 1, 8, tupla.getNombreProducto());
		   Integer cantidad = tupla.getCantidadProducto();
		   printer.printTextWrap(13 + i, 13, 13, 20, cantidad.toString());
		   printer.printTextWrap(13 + i, 13, 25, 34,String.format ("%,.2f", base)+ " €");
//		   DecimalFormat decimalFormat = new DecimalFormat("%.00");
		   baseImponible = baseImponible.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		//   printer.printTextWrap(13 + i, 13, 25, 30, tupla.getPrecioProducto().toString());
//		   printer.printTextWrap(13 + i, 13, 25, 30, baseImponible.toString());
		   printer.printTextWrap(13 + i, 13, 35, 48, tupla.getPrecioProducto().multiply(new BigDecimal(cantidad)).toString() + " €");
		   
		   // Crea una lineaTicket en la tabla LineaTicket por cada fila de tupla datos relacionada con el idTicket para que tengan un ticket asociado.
		   appService.crearListaTicket(idTicket,tupla.getNombreProducto(), cantidad.toString(),baseImponible.toString(), tupla.getPrecioProducto().multiply(new BigDecimal(cantidad)).toString());
		   i++;
	   }	    	    
	   
	   
	    printer.printCharAtCol(14 + i, 1, 48, "=");
	    printer.printTextWrap(15 + i, 15, 1, 16, "TOTAL A PAGAR: ");
	    printer.printTextWrap(15 + i, 15, 35, 48, aux.toString() + " €");
	    
	    if(formaPago){
		    	printer.printTextWrap(16 + i, 16, 1, 48, "Pagado con tarjeta");
		    	printer.printCharAtCol(20 + i, 1, 48, "=");
//		    	printer.printCharAtCol(22 + i, 1, 48, "Este ticket no tiene valor fiscal.");
//				printer.printCharAtCol(24 + i, 12, 48, "Graciaslllll por su visita!!!");
	    }else{
		    	printer.printTextWrap(16 + i, 16, 1, 21, "Efectivo entregado : ");
		    	printer.printTextWrap(16 + i, 16, 22, 48, ConstantesUtil.pagoEfectivo);
		    	printer.printTextWrap(18 + i, 18, 1, 8, "Cambio: ");
		    	printer.printTextWrap(18 + i, 18, 9, 48, ConstantesUtil.cambio);
		    	printer.printCharAtCol(21 + i, 1, 48, "=");
//		    	printer.printCharAtCol(22 + i, 1, 48, "Este ticket no tiene valor fiscal.");
//		    	printer.printCharAtCol(24 + i, 12, 48, "Gracias..... por su visita!!!");
	    }
//	    printer.printCharAtCol(22 + i, 1, 48, "Este ticket no tiene valor fiscal.");
//	    printer.printCharAtCol(25 + i, 12, 48, "Gracias por su visita!!!");
	    printer.printTextWrap(22 + i,24, 12, 48, "Gracias por su visita!!!");
	    printer.toFile("impresion.txt");
	
	    FileInputStream inputStream = null;
	    try {
	        inputStream = new FileInputStream("impresion.txt");
	        
	    } catch (FileNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    if (inputStream == null) {
	        return;
	    }
	
	    DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
	    Doc document = new SimpleDoc(inputStream, docFormat, null);
	
	    PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
	
	    PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
	
	
	    if (defaultPrintService != null) {
	        DocPrintJob printJob = defaultPrintService.createPrintJob();
	        try {
	            printJob.print(document, attributeSet);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    } else {
	    	JOptionPane.showInputDialog(this, "No hay impresoras instaladas");
	        System.err.println("No hay impresoras instaladas");
	    }
	
	 /*   try {
	   
	    	File file = new File("impresion.txt");
	    	FileInputStream inp = inputStream;
	    	FileOutputStream salida=new FileOutputStream(file);
	    	byte[] buf =new byte[1024];//Actualizado me olvide del 1024
	    	int len;
	    		while((len=inp.read(buf))>0){
	    		    salida.write(buf,0,len);
	    		}
	    	salida.close();	    
		//    file.createNewFile();
		    Desktop.getDesktop().open(file);
		    inp.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
	}
}

