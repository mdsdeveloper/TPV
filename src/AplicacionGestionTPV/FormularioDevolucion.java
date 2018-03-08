package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import PantallaVentas.Devolucion;
import PantallaVentas.ImprimirDevolucion;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.Empleado;
import modelo.Factura;
import modelo.Ticket;
import modelo.Venta;
import util.ConstantesUtil;
import util.EstadoEnum;
import util.IVAEnum;
import util.Validador;

public class FormularioDevolucion extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNTicket, tfProducto, tfCantidad, tfImporte;
	private JCheckBox chckbxTarjeta, chckbxEfectivo;
	private String precio;
	private BigDecimal pre;
	private GestionService gestionService = new GestionServiceImpl();
	private Configuracion configuracion;
	private AppService appService = new AppServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioDevolucion dialog = new FormularioDevolucion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioDevolucion() {
		getContentPane().setBackground(new Color(0, 102, 153));
		setBounds(100, 100, 664, 515);
		setUndecorated(true);
		setLocationRelativeTo(null); 
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(6, 77, 652, 327);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(113, 60, 425, 213);
		contentPanel.add(panel);
		
		JLabel label = new JLabel("Nº Ticket");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label.setBounds(9, 6, 119, 26);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Producto");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		label_1.setBounds(9, 50, 119, 26);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Cantidad");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		label_2.setBounds(9, 94, 119, 26);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Importe total");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		label_3.setBounds(9, 140, 119, 26);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Forma pago");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		label_4.setBounds(9, 184, 119, 26);
		panel.add(label_4);
		
		tfNTicket = new JTextField();
		tfNTicket.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		tfNTicket.setColumns(10);
		tfNTicket.setBounds(168, 6, 230, 32);
		panel.add(tfNTicket);
		
		tfProducto = new JTextField();
		tfProducto.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		tfProducto.setColumns(10);
		tfProducto.setBounds(168, 50, 230, 32);
		panel.add(tfProducto);
		
		tfCantidad = new JTextField();
		tfCantidad.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		tfCantidad.setColumns(10);
		tfCantidad.setBounds(168, 94, 230, 32);
		panel.add(tfCantidad);
		
		tfImporte = new JTextField();
		tfImporte.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		tfImporte.setColumns(10);
		tfImporte.setBounds(168, 140, 230, 32);
		panel.add(tfImporte);
		
		chckbxEfectivo = new JCheckBox("EFECTIVO");
		chckbxEfectivo.setForeground(new Color(255, 255, 255));
		chckbxEfectivo.setBounds(168, 187, 107, 23);
		panel.add(chckbxEfectivo);
		
		chckbxTarjeta = new JCheckBox("TARJETA");
		chckbxTarjeta.setForeground(new Color(255, 255, 255));
		chckbxTarjeta.setBounds(287, 187, 111, 23);
		panel.add(chckbxTarjeta);
		
	
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(6, 416, 652, 89);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(null);
		
		JButton btnAceptar = new JButton(ConstantesUtil.aceptar);
		btnAceptar.setBounds(30, 6, 147, 77);
		buttonPane.add(btnAceptar);
		btnAceptar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		JButton btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setBounds(253, 6, 147, 77);
		buttonPane.add(btnCancelar);
		btnCancelar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		JButton btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setBounds(475, 6, 147, 78);
		buttonPane.add(btnSalir);
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(6, 6, 652, 59);
		getContentPane().add(panelTitulo);
		
		JLabel lblDevoluciones = new JLabel("DEVOLUCIONES");
		lblDevoluciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevoluciones.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblDevoluciones.setBounds(243, 6, 203, 44);
		panelTitulo.add(lblDevoluciones);
		
		btnSalir.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnAceptar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
//			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.aceptar)){
			Devolucion devolucion = fillObject();
			if(devolucion != null){
				crearDevolucion(devolucion);
				modelo.Devolucion dev = new modelo.Devolucion();
				dev.setCantidad(devolucion.getCantidad());
				dev.setFormaPago(devolucion.getFormaPago());
				dev.setFecha(new Date().toString());
				dev.setImporte(devolucion.getImporte());
				dev.setNombreProducto(devolucion.getNombreProducto());
				dev.setNumeroTicket(devolucion.getNumTicket());
				dev.setEmpleado(ConstantesUtil.empleado);
				appService.crearDevolucion(dev);
				clearForm();
				dispose();
			}
		}
		
	}
	private Devolucion fillObject(){
		Devolucion devolucion = null;
		if(validar()){				
			devolucion = new Devolucion();
			devolucion.setNumTicket(tfNTicket.getText());
			Ticket ticket = appService.obtenerTicketById(tfNTicket.getText());
//			ticket.setDevuelto(true);
			devolucion.setCantidad(Integer.valueOf(tfCantidad.getText()));
			devolucion.setNombreProducto(tfProducto.getText());
			devolucion.setImporte(pre.negate());
			devolucion.setFormaPago(chckbxEfectivo.isSelected() ? false : true);
			
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos para anular el ticket");
		}
		return devolucion;
	}
	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!tfProducto.getText().equals("") && Validador.isAlfaNumericoConPunto(tfProducto.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!tfImporte.getText().equals("") && Validador.isDecimal(tfImporte.getText())){
			precio = ConstantesUtil.obtenerNumero(tfImporte.getText());
			pre = new BigDecimal(precio);
			dev = true;		
		}else{
			return false;
		}
		if(!tfCantidad.getText().equals("") && Validador.isNumero(tfCantidad.getText())){
			dev = true;
		}else{
			return false;
		}
		if(chckbxEfectivo.isSelected() || chckbxTarjeta.isSelected()){
			dev = true;
		}else{
			return false;
		}
		
		return dev;
	}
	private void clearForm() {
		tfNTicket.setText("");
		tfCantidad.setText("");
		tfProducto.setText("");
		tfImporte.setText("");
		chckbxTarjeta.setSelected(false);
		chckbxEfectivo.setSelected(false);
	}
	private void crearDevolucion(Devolucion devolucion){
		Venta venta = new Venta();
//		List<LineaVenta> listaLineas = lista;
		EstadoEnum estado = EstadoEnum.REALIZADA;
		IVAEnum iva = IVAEnum.VEINTIUNO;
		Date fecha = new Date();
		Long idVenta, idLVR;
		configuracion = gestionService.getConfiguracion();

		Empleado empleado = appService.obtenerEmpleado();		
		venta.setEmpleado(empleado);
		venta.setFecha(fecha);		
		venta.setEstado(estado.name());
		venta.setIVA(iva.getCodigo());
		venta.setFormaPago(devolucion.getFormaPago());

		BigDecimal total = BigDecimal.ZERO;
		total = devolucion.getImporte();

		BigDecimal totalIva = new BigDecimal(iva.getCodigo());
		totalIva = totalIva.divide(new BigDecimal(100)).add(new BigDecimal(1));
		Float base = total.floatValue();
		Float dividendo = totalIva.floatValue();
		base = base/dividendo;
		BigDecimal baseImponible = new BigDecimal(base);
		
		venta.setImporte(baseImponible);
		
		
		// FIXME como los precios se suponen con iva el importe será el total con iva incluido.
		venta.setTotal(total);
		venta.setNumero(appService.obtenerNumeroVenta() != null ? appService.obtenerNumeroVenta() + 1 : 1);
		idVenta = appService.crearVenta(venta);
		
//		Creo una factura
		Factura factura = new Factura();
		factura.setCabecera(configuracion.getCabeceraFactura() != null ? configuracion.getCabeceraFactura() : null);
		factura.setEmpleado(empleado.getNombre());
		factura.setFecha(fecha);
		factura.setIdVenta(idVenta);
//		factura.setIdVentaRealizada(idLVR);
		factura.setIVA(iva.getCodigo());
		factura.setPie(configuracion.getPieFactura() != null ? configuracion.getPieFactura() : null);
		factura.setBaseImponible(baseImponible);		
		factura.setTotal(total);
		factura.setFormaPago(venta.getFormaPago());
		appService.crearFactura(factura);
		// Marca el ticket como devuelto con un flag a true
		Ticket ticketDevuelto = appService.obtenerTicketById(devolucion.getNumTicket());
		ticketDevuelto.setDevuelto(true);
		gestionService.modificarTicket(ticketDevuelto);
		
		if(true){			
			new ImprimirDevolucion(venta,devolucion);	
		}
		
	}
}
