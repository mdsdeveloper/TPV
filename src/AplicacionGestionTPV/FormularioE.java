package AplicacionGestionTPV;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioE extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel centralPane = new JPanel();
	private JPanel buttonPane;
	public static JButton btnGestionProd,btnGestionCategoria,btnGestionEmpleado,btnGestionVentas,btnGestionFacturas,btnGestionSeguridad, btnGestionConf, btnGestionCajas;
	private double anchoVentana, altoVentana, ancho, alto;
	private JPanel panel;
	private JButton btnSalir;
	private GestionService service = new GestionServiceImpl();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioE frame = new FormularioE(1440, 900, 1440, 900);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the dialog.
	 */
	public FormularioE(double xw, double yw, double x, double y) {
		anchoVentana = x*0.570;
		altoVentana =  y*0.54;
		ancho = x;
		alto = y;
		setUndecorated(true);
		setLocationRelativeTo(null); 
		setResizable(false);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, 901, 616);
		setModal(true);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 901, 616);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		{
			centralPane.setBounds(6, 151, 889, 327);
			panel_1.add(centralPane);
			centralPane.setBackground(new Color(0, 102, 153));
			btnGestionProd = new JButton(ConstantesUtil.productos);
			btnGestionProd.setBounds(0, 0, 0, 0);
			btnGestionProd.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			
			btnGestionCategoria = new JButton(ConstantesUtil.categorias);
			btnGestionCategoria.setBounds(0, 0, 0, 0);
			btnGestionCategoria.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionEmpleado = new JButton(ConstantesUtil.empleados);
			btnGestionEmpleado.setBounds(0, 0, 0, 0);
			btnGestionEmpleado.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionVentas = new JButton(ConstantesUtil.ventas);
			btnGestionVentas.setBounds(0, 0, 0, 0);
			btnGestionVentas.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionSeguridad = new JButton(ConstantesUtil.seguridad);
			btnGestionSeguridad.setBounds(0, 0, 0, 0);
			btnGestionSeguridad.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionConf = new JButton(ConstantesUtil.configuracion);
			btnGestionConf.setBounds(0, 0, 0, 0);
			btnGestionConf.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionCajas = new JButton(ConstantesUtil.cajas);
			btnGestionCajas.setBounds(0, 0, 0, 0);
			btnGestionCajas.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			centralPane.setLayout(null);
			
			btnGestionFacturas= new JButton(ConstantesUtil.facturas);
			btnGestionFacturas.setBounds(0, 0, 0, 0);
			btnGestionFacturas.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			
			
			centralPane.add(btnGestionFacturas);
			centralPane.add(btnGestionCategoria);
			centralPane.add(btnGestionProd);
			centralPane.add(btnGestionEmpleado);
			centralPane.add(btnGestionVentas);
			centralPane.add(btnGestionSeguridad);
			centralPane.add(btnGestionCajas);
			centralPane.add(btnGestionConf);
			{
				buttonPane = new JPanel();
				buttonPane.setBackground(new Color(0, 102, 153));
				buttonPane.setBounds(0, 31, 396, -765);
				panel_1.add(buttonPane);
				buttonPane.setLayout(null);
				{
					
					btnSalir = new JButton(ConstantesUtil.salir);
					btnSalir.addActionListener(this);
					btnSalir.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathSalir)));
					btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
					btnSalir.setBounds((int)(anchoVentana*0.791), (int) (altoVentana*0.008), (int)(anchoVentana*0.174),(int) (altoVentana*0.125));
					buttonPane.add(btnSalir);
				}
			}
			
			btnGestionCajas.addActionListener(this);
			btnGestionCategoria.addActionListener(this);
			btnGestionConf.addActionListener(this);
			btnGestionEmpleado.addActionListener(this);
			btnGestionProd.addActionListener(this);
			btnGestionVentas.addActionListener(this);
			btnGestionFacturas.addActionListener(this);
			btnGestionSeguridad.addActionListener(this);
		}
		contentPanel.setBounds(0, 0, 901, 616);
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setForeground(new Color(0, 102, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 891, 47);
		getContentPane().add(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		
		{
			JLabel lblFormularioDeGestion = new JLabel(ConstantesUtil.formularioGestion);
			lblFormularioDeGestion.setBounds(303, 10, 294, 27);
			panel.add(lblFormularioDeGestion);
			lblFormularioDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
			
			lblFormularioDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
			lblFormularioDeGestion.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
		}
		setLocationRelativeTo(null); 
		setResizable(false);
		
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
		}
		
		
	}

	private void iniciarButtons() {
		PantallaPrincipal.btnConfiguracion.setEnabled(true);
	}
}


