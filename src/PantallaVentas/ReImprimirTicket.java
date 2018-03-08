package PantallaVentas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
import modelo.ListaTicket;
import modelo.Ticket;
import util.ConstantesUtil;

public class ReImprimirTicket {
	private String numero;
	private List<ListaTicket> lista = null;
	private AppService appService = new AppServiceImpl();
	
	public ReImprimirTicket(Ticket ticket){
		PrinterMatrix printer = new PrinterMatrix();
	    
	    Extenso e = new Extenso();
	    lista = appService.obtenerListaTicketById(ticket.getIdTicket());
	    numero = "Número: " + ticket.getNumeroDigito();
	    e.setNumber(101.85);  
	  //Defio el tamaño del papel para la impresion  aca 25 lineas y 80 columnas
	    printer.setOutSize(lista.size()+38, 48);
	    //Imprimir * 1ra linea de la columa de 1 a 80
	    printer.printCharAtCol(1, 1, 48, "=");
	  //Imprimir Encabezado nombre del La EMpresa
	   printer.printTextWrap(2, 2, 16, 48, "TICKET DE COMPRA");
	   //printer.printTextWrap(linI, linE, colI, colE, null);
	   printer.printTextWrap(3, 3, 1, 13, numero);
	   printer.printTextWrap(3, 3, 13, 30, "Fecha: " + ticket.getFecha());
	   printer.printTextWrap(3, 3, 33, 48, "Hora: " + ticket.getHora());
	   printer.printTextWrap(4, 4, 1, 48, "Atendido por: "+ ticket.getEmpleado());
	   printer.printTextWrap(5, 5, 1, 48, ticket.getNombreEmpresa() != null ? "Empresa: " + ticket.getNombreEmpresa() : "Empresa: ");		
	   printer.printTextWrap(6, 6, 1, 48, ticket.getDireccionEmpresa() != null ? "Dirección: " + ticket.getDireccionEmpresa() : "Dirección: " );
	   printer.printTextWrap(7, 7, 1, 48, ticket.getCifNif() != null ?  "CIF/NIF: " + ticket.getCifNif() : "CIF/NIF: " );
	   printer.printTextWrap(8, 8, 1, 48, ticket.getTelefonoEmpresa() != null ? "Teléfono: " + ticket.getTelefonoEmpresa(): "Teléfono: ");
	   printer.printCharAtCol(10, 1, 48, "=");
	   //									0						24
	   printer.printTextWrap(10, 11,1, 48, "Producto    Cantidad    Precio    P.Total");
	   printer.printCharAtCol(12, 1, 48, "-");
	   int i=0;
	   for (ListaTicket listaTicket : lista) {
		   printer.printTextWrap(13 + i, 13, 1, 8, listaTicket.getNombreProducto());
		   Integer cantidad = Integer.parseInt(listaTicket.getCantidadProducto());
		   printer.printTextWrap(13 + i, 13, 13, 20, cantidad.toString());
		   printer.printTextWrap(13 + i, 13, 25, 30, listaTicket.getPrecioProducto().toString()+ " €");
		   printer.printTextWrap(13 + i, 13, 35, 48, new BigDecimal(listaTicket.getPrecioProducto()).multiply(new BigDecimal(cantidad)).toString()+ " €");
		   i++;
	   }
	   
	    printer.printCharAtCol(14 + i, 1, 48, "=");
	    printer.printTextWrap(15 + i, 15, 1, 16, "TOTAL A PAGAR : ");
	    printer.printTextWrap(15 + i, 15, 35, 48, ticket.getTotal().toString() + " €");
	    
	    if(ticket.getFormaPago()){
		    	printer.printTextWrap(16 + i, 16, 1, 48, "Pagado con tarjeta");
		    	printer.printCharAtCol(20 + i, 1, 48, "=");
	    }else{
		    	printer.printTextWrap(16 + i, 16, 1, 21, "Efectivo entregado : ");
		    	printer.printTextWrap(16 + i, 16, 22, 48, ConstantesUtil.pagoEfectivo);
		    	printer.printTextWrap(18 + i, 18, 1, 8, "Cambio: ");
		    	printer.printTextWrap(18 + i, 18, 9, 48, ConstantesUtil.cambio);
		    	printer.printCharAtCol(21 + i, 1, 48, "=");
	    }
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
	   
	   
	   
	}
	
}
