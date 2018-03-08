package PantallaVentas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
import modelo.Venta;
import util.IVAEnum;
import util.UtilService;


public class ImprimirDevolucion {
	private Configuracion configuracion;
	private GestionService gestionService = new GestionServiceImpl();
	private AppService appService = new AppServiceImpl();
	private String direccion;
	private String path, numero, numeroDigito;
	private IVAEnum iva = IVAEnum.VEINTIUNO;
	private Float base = 0f,dividendo = 1f;
	private List<String> datosTicket = new ArrayList<>();
	
	
	public ImprimirDevolucion(Venta venta, Devolucion devolucion){
	    PrinterMatrix printer = new PrinterMatrix();
	    
	    Extenso e = new Extenso();
	    numeroDigito = appService.obtenerNumeroVenta() != null ? String.valueOf(appService.obtenerNumeroVenta()): "1";
	    numero = "Número: " + numeroDigito;
	    configuracion = gestionService.getConfiguracion();
		if(configuracion != null){
			path = configuracion.getDirectorioFactura();
		}
	 
	    e.setNumber(101.85);  
	    
	  
	    //Defio el tamaño del papel para la impresion  aca 25 lineas y 80 columnas
	    printer.setOutSize(30, 48);
	    //Imprimir * de la 2da linea a 25 en la columna 1;
	   // printer.printCharAtLin(2, 25, 1, "*");
	    //Imprimir * 1ra linea de la columa de 1 a 80
	   printer.printCharAtCol(1, 1, 48, "=");
	    //Imprimir Encabezado nombre del La EMpresa
	   printer.printTextWrap(2, 2, 16, 48, "TICKET DE DEVOLUCIÓN");
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
	   printer.printTextWrap(10,10,1, 48, "Producto    Cantidad    Precio    P.Total");
	   printer.printCharAtCol(12, 1, 48, "-");
	   
	   BigDecimal aux = BigDecimal.ZERO;
	   if(devolucion.getCantidad() > 1){
		   aux = venta.getTotal().multiply(new BigDecimal(devolucion.getCantidad()));
	   }else{
		   aux = venta.getTotal();
	   }
	   
	   Long idTicket = appService.crearTicket(fecha, hora, empleado, nombreEmpresa, direccionEmpresa, cifNif, telefonoEmpresa,numeroDigito, venta.getFormaPago(), aux, true);
	  
	   BigDecimal totalIva = new BigDecimal(iva.getCodigo());
	   totalIva = totalIva.divide(new BigDecimal(100)).add(new BigDecimal(1));
	   base = venta.getTotal().negate().floatValue();
	   dividendo = totalIva.floatValue();
	   base = base/dividendo;
	   BigDecimal baseImponible = new BigDecimal(base).negate();
	   baseImponible = baseImponible.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	   
	   printer.printTextWrap(12, 12, 0, 13, devolucion.getNombreProducto());
	   printer.printTextWrap(12, 12, 16, 20, devolucion.getCantidad().toString());
//	   printer.printTextWrap(13 + i, 13, 25, 34,String.format ("%,.2f", base)+ " €");
//	   printer.printTextWrap(12, 12, 25, 32, venta.getImporte().negate().toString());
	   printer.printTextWrap(12, 12, 25, 32, String.format ("%,.2f", base)+ " €");
	   printer.printTextWrap(12, 12, 36, 48, venta.getTotal().negate().toString() +" €");
		   
	   // Crea una lineaTicket en la tabla LineaTicket por cada fila de tupla datos relacionada con el idTicket para que tengan un ticket asociado.
	   appService.crearListaTicket(idTicket,devolucion.getNombreProducto(), devolucion.getCantidad().toString(),baseImponible.toString(), aux.toString());
			    	    
	   
	   
	    printer.printCharAtCol(14, 1, 48, "=");
	    printer.printTextWrap(15, 15, 1, 18, "TOTAL A DEVOLVER: ");
	    printer.printTextWrap(15, 15, 36, 48, aux.negate().toString() + " €");
	    
	    if(venta.getFormaPago()){
		    	printer.printTextWrap(17, 16, 1, 48, "Devolución con tarjeta");
		    	printer.printCharAtCol(19, 1, 48, "=");
	    }else{
		    	printer.printTextWrap(17, 16, 1, 48, "Devolución en efectivo");
		    	printer.printCharAtCol(19, 1, 48, "=");
	    }
	    printer.printTextWrap(22,24, 12, 48, "Gracias por su visita!!!");
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
	}
}

