package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import PantallaVentas.CreaExcel;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Venta;
import util.ConstantesUtil;

public class ListadoVentas extends JDialog implements ActionListener{

	private final JPanel contentPanel, panelSalir;
	private double anchoVentana, altoVentana, ancho, alto;
	public JButton btnSalir;
	private JPanel panelCheck;
	private JCheckBox chckbxNmeroVenta,chckbxFecha,chckbxFormaDePago,chckbxIva,chckbxImporte,chckbxTotal,chckbxEmpleado,chckbxEstado;
	private JLabel lblSeleccioneLosDatos;
	private JButton btnMarcarTodos,btnDesmarcarTodos,btnListarDatosA;
	private JDateChooser dateInicio, dateFin;
	private GestionService service = new GestionServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoVentas dialog = new ListadoVentas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoVentas() {
		anchoVentana = 712;
		altoVentana =  572;
		ancho = 1440;
		alto = 900;
		getContentPane().setBackground(new Color(0, 102, 153));

		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, (int)anchoVentana, (int)altoVentana);
		getContentPane().setLayout(null);
		setModal(true);
		setUndecorated(true);
//		******************************paneles		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(6, 75, 699, 380);
		getContentPane().add(contentPanel);
		
		panelCheck = new JPanel();
		panelCheck.setBounds(9, 115, 200, 255);
		contentPanel.add(panelCheck);
		panelCheck.setLayout(null);
		
		chckbxNmeroVenta = new JCheckBox(ConstantesUtil.numeroVenta);
		chckbxNmeroVenta.setBounds(5, 190, 128, 23);
		panelCheck.add(chckbxNmeroVenta);
		
		chckbxFecha = new JCheckBox(ConstantesUtil.fecha);
		chckbxFecha.setBounds(5, 70, 128, 23);
		panelCheck.add(chckbxFecha);
		
		chckbxFormaDePago = new JCheckBox(ConstantesUtil.formaPago);
		chckbxFormaDePago.setBounds(5, 100, 128, 23);
		panelCheck.add(chckbxFormaDePago);
		
		chckbxIva = new JCheckBox(ConstantesUtil.iva);
		chckbxIva.setBounds(5, 130, 128, 23);
		panelCheck.add(chckbxIva);
		
		chckbxImporte = new JCheckBox(ConstantesUtil.importe);
		chckbxImporte.setBounds(5, 160, 128, 23);
		panelCheck.add(chckbxImporte);
		
		chckbxTotal = new JCheckBox(ConstantesUtil.totalch);
		chckbxTotal.setBounds(5, 220, 128, 23);
		panelCheck.add(chckbxTotal);
		
		chckbxEmpleado = new JCheckBox(ConstantesUtil.empleadoch);
		chckbxEmpleado.setBounds(6, 40, 128, 23);
		panelCheck.add(chckbxEmpleado);
		
		chckbxEstado = new JCheckBox(ConstantesUtil.estado);
		chckbxEstado.setBounds(5, 10, 128, 23);
		panelCheck.add(chckbxEstado);
		
		lblSeleccioneLosDatos = new JLabel("Seleccione los datos a listar");
		lblSeleccioneLosDatos.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeleccioneLosDatos.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblSeleccioneLosDatos.setBounds(9, 13, 322, 35);
		contentPanel.add(lblSeleccioneLosDatos);
		
		btnMarcarTodos = new JButton(ConstantesUtil.marcarTodos);
		btnMarcarTodos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnMarcarTodos.setBounds(221, 115, 173, 57);
		btnMarcarTodos.setIcon(new ImageIcon(ListadoVentas.class.getResource(ConstantesUtil.ok)));
		contentPanel.add(btnMarcarTodos);
		
		btnDesmarcarTodos = new JButton(ConstantesUtil.desmarcar);
		btnDesmarcarTodos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnDesmarcarTodos.setBounds(221, 175, 173, 57);
		btnDesmarcarTodos.setIcon(new ImageIcon(ListadoVentas.class.getResource(ConstantesUtil.cancel)));
		contentPanel.add(btnDesmarcarTodos);
		
		dateInicio = new JDateChooser();
		dateInicio.getCalendarButton().setText(ConstantesUtil.dateInicio);
		dateInicio.setDateFormatString("dd/MMM/yyyy");
//		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		dateInicio.setBounds(499, 238, 194, 35);
//		cambia a idioma español
		Locale locale = new Locale("es", "ES");
		dateInicio.setLocale(locale);
		contentPanel.add(dateInicio);
		
		dateFin = new JDateChooser();		
		dateFin.setLocale(locale);
		dateFin.getCalendarButton().setText(ConstantesUtil.dateFin);
		dateFin.setBounds(499, 285, 194, 35);
		contentPanel.add(dateFin);
		
		JLabel lblNewLabel = new JLabel("Fecha inicio");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(395, 238, 102, 35);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha fin");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(395, 285, 102, 35);
		contentPanel.add(lblNewLabel_1);
		
		btnListarDatosA = new JButton(ConstantesUtil.listarExcel);
		btnListarDatosA.setBounds(395, 332, 301, 38);
		contentPanel.add(btnListarDatosA);
		btnListarDatosA.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnListarDatosA.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.iconoExcel)));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(6, 6, 700, 47);
		getContentPane().add(panel);
		
		panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 496, 700, 70);
		getContentPane().add(panelSalir);
//		*******************************fin paneles
		
		JLabel lblListadoDeVentas = new JLabel("LISTADO DE VENTAS");
		lblListadoDeVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeVentas.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblListadoDeVentas.setBounds(202, 6, 294, 35);
		panel.add(lblListadoDeVentas);
		
		
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(ListadoVentas.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(551, 6, 143, 57);
		panelSalir.add(btnSalir);

		btnSalir.addActionListener(this);
		dateInicio.getCalendarButton().addActionListener(this);
		dateFin.getCalendarButton().addActionListener(this);
		btnMarcarTodos.addActionListener(this);
		btnDesmarcarTodos.addActionListener(this);
		btnListarDatosA.addActionListener(this);
	}
	
	public List<String> crearListaCheck(){
		List<String> listaCheck = new ArrayList<>();
		if(chckbxNmeroVenta.isSelected()){
			listaCheck.add(chckbxNmeroVenta.getText());
		}
		if(chckbxEmpleado.isSelected()){
			listaCheck.add(chckbxEmpleado.getText());
		}
		if(chckbxEstado.isSelected()){
			listaCheck.add(chckbxEstado.getText());
		}
		if(chckbxFecha.isSelected()){
			listaCheck.add(chckbxFecha.getText());
		}
		if(chckbxFormaDePago.isSelected()){
			listaCheck.add(chckbxFormaDePago.getText());
		}
		if(chckbxImporte.isSelected()){
			listaCheck.add(chckbxImporte.getText());
		}
		if(chckbxIva.isSelected()){
			listaCheck.add(chckbxIva.getText());
		}
		if(chckbxTotal.isSelected()){
			listaCheck.add(chckbxTotal.getText());
		}
		
		return listaCheck;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();
		}else if(source.getText().equals(ConstantesUtil.marcarTodos)){
			chckbxNmeroVenta.setSelected(true);
			chckbxFecha.setSelected(true);
			chckbxFormaDePago.setSelected(true);
			chckbxIva.setSelected(true);
			chckbxImporte.setSelected(true);
			chckbxTotal.setSelected(true);
			chckbxEmpleado.setSelected(true);
			chckbxEstado.setSelected(true);
		}else if(source.getText().equals(ConstantesUtil.desmarcar)){
			chckbxNmeroVenta.setSelected(false);
			chckbxFecha.setSelected(false);
			chckbxFormaDePago.setSelected(false);
			chckbxIva.setSelected(false);
			chckbxImporte.setSelected(false);
			chckbxTotal.setSelected(false);
			chckbxEmpleado.setSelected(false);
			chckbxEstado.setSelected(false);
		}else if(source.getText().equals(ConstantesUtil.listarExcel)){
			if(dateInicio.getDate() != null && dateFin.getDate() != null){
				String formato = dateInicio.getDateFormatString();
				Date date = dateInicio.getDate();
				SimpleDateFormat form = new SimpleDateFormat(dateInicio.getDateFormatString());
				List<String> listaCheck = crearListaCheck();
				List<Venta> listaVenta = service.crearListado(dateInicio.getDate(), dateFin.getDate(), listaCheck);
				CreaExcel excel = new CreaExcel(listaVenta, listaCheck);

			}else{
				JOptionPane.showMessageDialog(this, "Debes seleccionar la fecha de inicio y fin !!!");
			}
			
			
		}
	}
}
