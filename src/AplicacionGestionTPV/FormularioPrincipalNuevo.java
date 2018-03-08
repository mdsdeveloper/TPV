package AplicacionGestionTPV;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import util.ConstantesUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FormularioPrincipalNuevo extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel PanelSalir = new JPanel();
	private JPanel TituloPanel = new JPanel();
	public static JButton btnGestionProd,btnGestionCategoria,btnTicket,btnGestionEmpleado,btnGestionVentas,btnGestionFacturas,btnGestionSeguridad, btnGestionConf, btnGestionCajas;
	private double anchoVentana, altoVentana, ancho, alto;
	private JPanel panel;
	private JButton btnSalir;
	private GestionService service = new GestionServiceImpl();
	private FormularioTicket formularioTicket;
	private FormularioListados formularioListados;
	private JButton btnListados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioPrincipalNuevo dialog = new FormularioPrincipalNuevo(1440, 900, 1440, 900);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioPrincipalNuevo(double xw, double yw, double x, double y) {
		anchoVentana = 905;
		altoVentana =  602;
		ancho = x;
		alto = y;
		getContentPane().setBackground(new Color(0, 102, 153));
//		setBounds(100, 100, 905, 602);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, (int)anchoVentana, (int)altoVentana);
		getContentPane().setLayout(null);
		setModal(true);
		setUndecorated(true);
		
		{
//			Panel con el titulo de gestion del formulario
			
			TituloPanel.setLayout(null);
			TituloPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			TituloPanel.setBounds(6, 6, 891, 47);
			getContentPane().add(TituloPanel);
			{
				JLabel lblFormularioDeGestion = new JLabel(ConstantesUtil.formularioGestion);
				lblFormularioDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
				lblFormularioDeGestion.setBounds(303, 10, 294, 27);				
				lblFormularioDeGestion.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
				
				TituloPanel.add(lblFormularioDeGestion);
			}
		}
			
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(98, 123, 699, 376);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
//			Panel con la botonera central
			
			btnGestionFacturas= new JButton(ConstantesUtil.facturas);
			btnGestionFacturas.setBounds(499, 102, 194, 81);
			btnGestionFacturas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			
			btnGestionCategoria = new JButton(ConstantesUtil.categorias);
			btnGestionCategoria.setBounds(6, 102, 192, 81);
			btnGestionCategoria.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
			btnGestionProd = new JButton(ConstantesUtil.productos);	
			btnGestionProd.setBounds((int)(anchoVentana*0.749), (int) (altoVentana*0.088), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionProd.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
		
			btnGestionProd.setBounds(242, 102, 212, 81);
			btnGestionProd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
			btnGestionEmpleado = new JButton(ConstantesUtil.empleados);
			btnGestionEmpleado.setBounds(495, 9, 198, 81);
			btnGestionEmpleado.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
			btnGestionVentas = new JButton(ConstantesUtil.ventas);
			btnGestionVentas.setBounds(242, 195, 212, 79);
			btnGestionVentas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
			btnGestionSeguridad = new JButton(ConstantesUtil.seguridad); 
			btnGestionSeguridad.setBounds(6, 9, 192, 81);
			btnGestionSeguridad.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
			btnGestionCajas = new JButton(ConstantesUtil.cajas);
			btnGestionCajas.setBounds(6, 193, 192, 81);
			btnGestionCajas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));		

			btnGestionConf = new JButton(ConstantesUtil.configuracion); 
			btnGestionConf.setBounds(242, 9, 212, 81);
			btnGestionConf.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			
			btnTicket = new JButton(ConstantesUtil.ticket);
			btnTicket.setBounds(499, 195, 194, 79);
			btnTicket.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			
			btnListados = new JButton(ConstantesUtil.listados);
			btnListados.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			btnListados.setBounds(242, 286, 212, 79);
			
		}
		
		{
//			Panel con el boton de salir
			
			PanelSalir.setBounds(6, 526, 891, 70);
			getContentPane().add(PanelSalir);
			PanelSalir.setLayout(null);
			{
				btnSalir = new JButton(ConstantesUtil.salir);
				btnSalir.setBounds(742, 6, 143, 57);				
				btnSalir.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathSalir)));
				btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
//				btnSalir.setBounds((int)(anchoVentana*0.791), (int) (altoVentana*0.008), (int)(anchoVentana*0.174),(int) (altoVentana*0.125));
				PanelSalir.add(btnSalir);
			}
		}
		
		contentPanel.add(btnTicket);
		contentPanel.add(btnGestionConf);
		contentPanel.add(btnGestionCajas);
		contentPanel.add(btnGestionSeguridad);
		contentPanel.add(btnGestionVentas);
		contentPanel.add(btnGestionEmpleado);
		contentPanel.add(btnGestionProd);
		contentPanel.add(btnGestionCategoria);
		contentPanel.add(btnGestionFacturas);
		contentPanel.add(btnListados);
		
		
		btnGestionCajas.addActionListener(this);
		btnGestionCategoria.addActionListener(this);
		btnGestionConf.addActionListener(this);
		btnGestionEmpleado.addActionListener(this);
		btnGestionProd.addActionListener(this);
		btnGestionVentas.addActionListener(this);
		btnGestionFacturas.addActionListener(this);
		btnGestionSeguridad.addActionListener(this);
		btnSalir.addActionListener(this);
		btnTicket.addActionListener(this);
		btnListados.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.productos)){
			FormularioProducto formularioProducto = new FormularioProducto();
//			formularioProducto.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			formularioProducto.setVisible(true);
//			btnGestionProd.setEnabled(false);
		}else if(source.getText().equals(ConstantesUtil.salir)){
			iniciarButtons();
			dispose();			
		}else if(source.getText().equals(ConstantesUtil.categorias)){
			FormularioCategoria formularioCategoria = new FormularioCategoria();
//			formularioCategoria.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			formularioCategoria.setVisible(true);
//			btnGestionCategoria.setEnabled(false);
		}else if(source.getText().equals(ConstantesUtil.empleados)){
			FormularioEmpleados formularioEmpleado = new FormularioEmpleados();
//			formularioEmpleado.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			formularioEmpleado.setVisible(true);
//			btnGestionEmpleado.setEnabled(false);
		}else if(source.getText().equals(ConstantesUtil.configuracion)){	
			FormularioConfiguracion formularioConfiguracion = new FormularioConfiguracion();			
			formularioConfiguracion.setVisible(true);
//			formularioConfiguracion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else if(source.getText().equals(ConstantesUtil.ventas)){
			FormularioVentas formularioVentas = new FormularioVentas();
			formularioVentas.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.cajas)){
			FormularioCajas formularioCajas = new FormularioCajas();
			formularioCajas.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.seguridad)){
			FormularioSeguridad formularioSeguridad = new FormularioSeguridad();
			formularioSeguridad.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.facturas)){
			FormularioFacturas formularioInformes = new FormularioFacturas();
			formularioInformes.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.ticket)){
			String nombre = this.getClass().getName();
			formularioTicket = new FormularioTicket(nombre);
			formularioTicket.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			formularioTicket.setVisible(true);
			btnTicket.setEnabled(!formularioTicket.isShowing());
		}else if(source.getText().equals(ConstantesUtil.listados)){
			formularioListados = new FormularioListados();
			formularioListados.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			formularioListados.setVisible(true);
		}
		
		
	}

	private void iniciarButtons() {
		PantallaPrincipal.btnConfiguracion.setEnabled(true);
	}
	
}
