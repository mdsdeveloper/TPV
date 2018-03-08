package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import PantallaVentas.ReImprimirTicket;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Empleado;
import modelo.Ticket;
import util.ConstantesUtil;
import util.UtilService;

public class FormularioTicket extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel panelTabla;
	private JScrollPane scrollPane;
	private JTable table,modeloTabla;
	private GestionService service = new GestionServiceImpl();
	private int filasSeleccionadas = -1;
	private boolean tablaSeleccionada;
	private AppService appService = new AppServiceImpl();
	private JButton btnEliminarTicket, btnSalir,btnImprimir;
	private String idTicket;
	private int cantidadTicket;
	private Ticket ticket = null;
	private boolean ticketDevuelto;
	private String nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioTicket dialog = new FormularioTicket("main");
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioTicket(String nombreLLamada) {
		nombre = nombreLLamada;
		setBounds(100, 100, 690, 617);
		getContentPane().setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null); 
		setResizable(false);
		setModal(true);
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(0, 0, 690, 617);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(6, 549, 678, 62);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		Font fontButton = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		{
			btnSalir = new JButton(ConstantesUtil.salir);
			btnSalir.setBounds(470, 5, 202, 51);
			btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
			btnSalir.setFont(fontButton);
			buttonPane.add(btnSalir);
			getRootPane().setDefaultButton(btnSalir);
		}
		
		btnImprimir = new JButton(ConstantesUtil.btnImprimirTicket);
		btnImprimir.setBounds(6, 5, 202, 51);
		buttonPane.add(btnImprimir);
		btnImprimir.setFont(fontButton);
		
		btnEliminarTicket = new JButton(ConstantesUtil.eliminarTicket);
		btnEliminarTicket.setBounds(245, 5, 202, 51);
		buttonPane.add(btnEliminarTicket);
		btnEliminarTicket.setFont(fontButton);
		
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(6, 6, 678, 55);
		contentPanel.add(panelTitulo);
		panelTitulo.setLayout(null);
		{
			JLabel lbltituloTicket = new JLabel("TICKETS");
			lbltituloTicket.setBounds(243, 6, 203, 44);
			panelTitulo.add(lbltituloTicket);
			lbltituloTicket.setHorizontalAlignment(SwingConstants.CENTER);
			lbltituloTicket.setFont(font);
		}
		

		btnSalir.addActionListener(this);
		btnEliminarTicket.addActionListener(this);
		btnImprimir.addActionListener(this);
		construirTablaTicket();
		estadoInicial();
	}
	private void estadoInicial(){
		filasSeleccionadas = -1;
		ticketDevuelto = false;	
		btnImprimir.setEnabled(cantidadTicket > 0  && filasSeleccionadas > 0 && ticket.getDevuelto() ? true : false);
		btnEliminarTicket.setEnabled(cantidadTicket > 0  && filasSeleccionadas > 0 && ticket.getDevuelto() ? true : false);
		ticket = null;
	}
	
	private void construirTablaTicket() {
		
		panelTabla = new JPanel();
//		panelTabla.setBounds(6, 126, 493, 205);
		panelTabla.setBounds(6, 73, 678, 464);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
//		scrollPane.setBounds(0, 0, 493, 205);
		scrollPane.setBounds(0, 0, 678, 464);
		
		
		String cabecera[] = {"Ticket","Fecha", "Hora","Empleado","Forma Pago", "Total", "devolucion"};	
		String datos[][] = service.rellenarTablaTicket(nombre);
		cantidadTicket = datos.length;
		DefaultTableModel model = new DefaultTableModel(datos, cabecera);
		Font fuente = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		table = new JTable(model);	
		table.setFont(fuente);
		table.setBounds(277, 60, 426, 138);
		JTableHeader th = table.getTableHeader();
		th.setBackground(Color.GRAY);

		th.setFont(fuente);
		table.setTableHeader(th);
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
	
		table.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		    	  filasSeleccionadas = table.getSelectedRow();
		    	  if(filasSeleccionadas != -1){
		    		  tablaSeleccionada = true;
		    		  btnEliminarTicket.setEnabled(true);
		    		  btnImprimir.setEnabled(true);
			    	  modeloTabla = table;	
			    	  idTicket = (String) modeloTabla.getValueAt(filasSeleccionadas, 0);
			    	  String devolucion = (String) modeloTabla.getValueAt(filasSeleccionadas, 6);
			    	  ticketDevuelto = devolucion == "false" ? true : false;
			    	  String emp = UtilService.getEmpleadoLogado().getCargo();
			    	  if(emp.equals(ConstantesUtil.administrador)){
			    		  btnImprimir.setEnabled(true);
				    	  btnEliminarTicket.setEnabled(true);
			    	  }else{
			    		  btnImprimir.setEnabled(ticketDevuelto);
				    	  btnEliminarTicket.setEnabled(false);
			    	  }
			    	  
		    	  }
		      }
		   });
		panelTabla.add(scrollPane);
		contentPanel.add(panelTabla);		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();
		}else if(source.getText().equals(ConstantesUtil.btnImprimirTicket) && filasSeleccionadas  != -1 && ticketDevuelto){
			ticket = null;
			if(idTicket != null){
				ticket = appService.obtenerTicketById(idTicket);
			}			
			if(ticket != null){
				new ReImprimirTicket(ticket);
			}else{
				JOptionPane.showMessageDialog(this, "Ha habido un error al cargar el ticket!!!");
			}
			
		}else if(source.getText().equals(ConstantesUtil.eliminarTicket) && filasSeleccionadas != -1){
			service.eliminarTicket(idTicket);
			
		
		}
		
		contentPanel.remove(panelTabla);
		construirTablaTicket();
		estadoInicial();
	}
}
