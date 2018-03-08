package PantallaVentas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import AplicacionGestionTPV.FormularioCategoria;
import AplicacionGestionTPV.FormularioDevolucion;
import AplicacionGestionTPV.FormularioTicket;
import AplicacionGestionTPV.PantallaPrincipal;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import modelo.Venta;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class OpcionesNuevo extends JDialog implements ActionListener{

	private JPanel contentPane;
	private double alto, ancho;
	private JButton btnFactura, btnExit, btnSalir, btnCierreCaja, btnBalance, btnDevolucion, btnTicket;
	private PantallaTPV PantallaTPV;
	private FacturaConCliente formularioFactura;
	private FormularioTicket formularioTicket;
	private FormularioDevolucion formularioDevolucion;
	private AppService appService = new AppServiceImpl();
	private JButton button;
	private JButton button_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpcionesNuevo opciones = new OpcionesNuevo(new PantallaTPV());
					opciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param PantallaTPV 
	 */
	public OpcionesNuevo(PantallaTPV PantallaTPV) {
		this.PantallaTPV = PantallaTPV;
		setModal(true);
		setForeground(UIManager.getColor("ColorChooser.background"));
		setBackground(UIManager.getColor("Desktop.background"));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		alto = 460;
		ancho = 404;
//		alto = ConstantesUtil.alto*0.4444;
//		ancho = ConstantesUtil.ancho*0.2805;
		setBounds((int)ConstantesUtil.ancho/2 - (int)ancho/2, (int)ConstantesUtil.alto/2 - (int)alto/2, (int)ancho, (int)alto);
//		setBounds((int) (1440/2 - ancho/2), (int) 900/2 - 400/2,(int) ancho,(int) alto);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new LineBorder(new Color(65, 105, 170), 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(UIManager.getColor("Button.background"));
//		panel.setBounds(10, 10, 384, 380);
		panel.setBounds((int)(ancho*0.0247), (int)(alto*0.025), (int)(ancho*0.954), (int)(alto*0.958));
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnSalir = new JButton(ConstantesUtil.cerrar);
		btnSalir.setBounds(235, 362, 142, 72);
//		btnSalir.setBounds((int) (ancho/2), (int)(ConstantesUtil.alto*0.3355), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnSalir.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathSalir)));
//		btnSalir.setFont(new Font("Lucida Grande", Font.BOLD, 13) );
		btnSalir.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnSalir);
		
		btnCierreCaja = new JButton(ConstantesUtil.cierre);
//		btnCierreCaja.setBounds(36, 91, 142, 72);
//		btnCierreCaja.setBounds((int) (ancho/4) - (int)(1440*0.12)/2, (int)(900*0.1011), (int)(1440*0.12), (int)(900*0.08));
		btnCierreCaja.setBounds((int) (ancho/4) - (int)(ConstantesUtil.ancho*0.12)/2, (int)(ConstantesUtil.alto*0.1011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
//		btnCierreCaja.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnCierreCaja.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnCierreCaja);
		
		btnBalance = new JButton(ConstantesUtil.balance);
		btnBalance.setBounds(198, 90, 142, 72);
//		btnBalance.setBounds((int) (ancho/2), (int)(900*0.1011), (int)(1440*0.12), (int)(900*0.08));
		btnBalance.setBounds((int) (ancho/2), (int)(ConstantesUtil.alto*0.1011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnBalance.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathBalance)));
//		btnBalance.setFont(new Font("Lucida Grande", Font.BOLD, 13) );
		btnBalance.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnBalance);
		
		JLabel lblOpciones = new JLabel("OPCIONES DEL TPV");
//		lblOpciones.setBounds(6, 18, 372, 42);
//		lblOpciones.setBounds((int)(1440*0.0041), (int)(900*0.02), (int)(1440*0.2583), (int)(900*0.0466));
		lblOpciones.setBounds((int)(ConstantesUtil.ancho*0.0041), (int)(ConstantesUtil.alto*0.02), (int)(ConstantesUtil.ancho*0.2583), (int)(ConstantesUtil.alto*0.0466));
//		lblOpciones.setFont(new Font("Lucida Grande", Font.BOLD, 17) );
		lblOpciones.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
		lblOpciones.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblOpciones);
		
		btnFactura = new JButton(ConstantesUtil.factura);
//		btnFactura.setBounds(36, 181, 142, 72);
//		btnFactura.setBounds((int) (ancho/4) - (int)(1440*0.12)/2, (int)(900*0.2011), (int)(1440*0.12), (int)(900*0.08));
		btnFactura.setBounds((int) (ancho/4) - (int)(ConstantesUtil.ancho*0.12)/2, (int)(ConstantesUtil.alto*0.2011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnFactura.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathPrint)));
//		btnFactura.setFont(new Font("Lucida Grande", Font.BOLD, 13) );
		btnFactura.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnFactura);
		
		btnExit = new JButton(ConstantesUtil.exit);
//		btnExit.setBounds(199, 181, 142, 72);
//		btnExit.setBounds((int) (ancho/2), (int)(900*0.2011), (int)(1440*0.12), (int)(900*0.08));
		btnExit.setBounds((int) (ancho/2), (int)(ConstantesUtil.alto*0.2011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnExit.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathExit)));
//		btnExit.setFont( new Font("Lucida Grande", Font.BOLD, 13) );
		btnExit.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnExit);
		
		btnDevolucion = new JButton(ConstantesUtil.devolucion);
//		btnDevolucion.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnDevolucion.setBounds(202, 264, 172, 72);
//		btnDevolucion.setEnabled(false);
		btnDevolucion.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnDevolucion);
		
		btnTicket = new JButton(ConstantesUtil.ticket);
//		btnTicket.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnTicket.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		btnTicket.setBounds(15, 264, 172, 72);
		panel.add(btnTicket);
		
		btnExit.addActionListener(this);
		btnFactura.addActionListener(this);
		btnBalance.addActionListener(this);
		btnCierreCaja.addActionListener(this);
		btnSalir.addActionListener(this);
		btnDevolucion.addActionListener(this);
		btnTicket.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.cerrar)){
			PantallaTPV.btnOpciones.setEnabled(true);
			dispose();		
		}else if(source.getText().equals(ConstantesUtil.exit)){
			PantallaPrincipal.btnEntrarTpv.setEnabled(true);
			PantallaTPV.cerrarTPV();
			dispose();	
			
		}else if(source.getText().equals(ConstantesUtil.cierre)){
			CierreCaja dialog = new CierreCaja();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);			
		}else if(source.getText().equals(ConstantesUtil.factura)){
			formularioFactura = new FacturaConCliente();
			formularioFactura.setVisible(true);
			formularioFactura.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
		}else if(source.getText().equals(ConstantesUtil.balance)){
			List<Venta> listaVenta;
			Date ahora = new Date();
			listaVenta = appService.hacerBalanceCaja(ahora);
			
			if(!listaVenta.isEmpty()){
				new CierreImpreso(listaVenta, ahora, "", ConstantesUtil.balance);
			}
		}else if(source.getText().equals(ConstantesUtil.ticket)){
			String nombre = this.getClass().getName();
			formularioTicket = new FormularioTicket(nombre);
			formularioTicket.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			formularioTicket.setVisible(true);
			btnTicket.setEnabled(!formularioTicket.isShowing());
		}else if(source.getText().equals(ConstantesUtil.devolucion)){
			formularioDevolucion = new FormularioDevolucion();
			formularioDevolucion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			formularioDevolucion.setVisible(true);
		}
		
	}
}
