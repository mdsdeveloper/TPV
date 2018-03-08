package AplicacionGestionTPV;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.ConstantesUtil;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioListados extends JDialog implements ActionListener{
	private double anchoVentana, altoVentana, ancho, alto;
	public JButton btnProducto,btnCategoria,btnTicket,btnEmpleado,btnVentas,btnFacturas, btnCajas, btnDevoluciones, btnSalir;
	private JPanel contentPanel,TituloPanel,panelSalir;
	private ListadoVentas listadoVentas;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioListados dialog = new FormularioListados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioListados() {
		anchoVentana = 712;
		altoVentana =  572;
		ancho = 1440;
		alto = 900;
		getContentPane().setBackground(new Color(0, 102, 153));

		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, (int)anchoVentana, (int)altoVentana);
		getContentPane().setLayout(null);
		setModal(true);
		setUndecorated(true);
		
//		setBounds(100, 100, 712, 572);
		getContentPane().setLayout(null);
		
//		*************************************paneles
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(6, 75, 699, 376);
		getContentPane().add(contentPanel);
		
		TituloPanel = new JPanel();
		TituloPanel.setLayout(null);
		TituloPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		TituloPanel.setBounds(6, 6, 700, 47);
		getContentPane().add(TituloPanel);
		
		JLabel lblListados = new JLabel(ConstantesUtil.listados);
		lblListados.setHorizontalAlignment(SwingConstants.CENTER);
		lblListados.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblListados.setBounds(202, 6, 294, 35);
		TituloPanel.add(lblListados);
		
		panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 496, 700, 70);
		getContentPane().add(panelSalir);
//		*****************************************fin paneles
		
//		*****************************************botonera
		btnTicket = new JButton(ConstantesUtil.ticket);
		btnTicket.setEnabled(false);
		btnTicket.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnTicket.setBounds(6, 10, 194, 79);
		contentPanel.add(btnTicket);
		
		btnCajas = new JButton(ConstantesUtil.cajas);
		btnCajas.setEnabled(false);
		btnCajas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnCajas.setBounds(6, 193, 192, 81);
		contentPanel.add(btnCajas);
		
			
		btnVentas = new JButton(ConstantesUtil.ventas);
		btnVentas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnVentas.setBounds(242, 10, 212, 79);
		contentPanel.add(btnVentas);
		
		btnEmpleado= new JButton(ConstantesUtil.empleados);
		btnEmpleado.setEnabled(false);
		btnEmpleado.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnEmpleado.setBounds(499, 102, 198, 81);
		contentPanel.add(btnEmpleado);
		
		btnProducto = new JButton(ConstantesUtil.productos);
		btnProducto.setEnabled(false);
		btnProducto.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnProducto.setBounds(242, 102, 212, 81);
		contentPanel.add(btnProducto);
		
		btnCategoria = new JButton(ConstantesUtil.categorias);
		btnCategoria.setEnabled(false);
		btnCategoria.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnCategoria.setBounds(6, 102, 192, 81);
		contentPanel.add(btnCategoria);
		
		btnFacturas = new JButton(ConstantesUtil.facturas);
		btnFacturas.setEnabled(false);
		btnFacturas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnFacturas.setBounds(499, 9, 194, 81);
		contentPanel.add(btnFacturas);
		
		btnDevoluciones = new JButton(ConstantesUtil.devoluciones);
		btnDevoluciones.setEnabled(false);
		btnDevoluciones.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnDevoluciones.setBounds(242, 194, 212, 79);
		contentPanel.add(btnDevoluciones);
		
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(551, 6, 143, 57);
		panelSalir.add(btnSalir);
//	*****************************************Fin botonera
		
		
	
		btnCajas.addActionListener(this);
		btnCategoria.addActionListener(this);
		btnEmpleado.addActionListener(this);
		btnProducto.addActionListener(this);
		btnVentas.addActionListener(this);
		btnFacturas.addActionListener(this);
		btnSalir.addActionListener(this);
		btnTicket.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.productos)){
			
		}else if(source.getText().equals(ConstantesUtil.salir)){
//			iniciarButtons();
			dispose();			
		}else if(source.getText().equals(ConstantesUtil.categorias)){
	
		}else if(source.getText().equals(ConstantesUtil.empleados)){
	
		}else if(source.getText().equals(ConstantesUtil.ventas)){
			listadoVentas = new ListadoVentas();
			listadoVentas.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			listadoVentas.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.cajas)){
			
		}else if(source.getText().equals(ConstantesUtil.facturas)){
		
		}else if(source.getText().equals(ConstantesUtil.ticket)){
			
		}
	}

}
