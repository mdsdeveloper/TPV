package modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import PantallaVentas.TuplaDatos;


@Entity
@Table(name="ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTicket;
	@Column(name="fecha")
	private String fecha;
	@Column(name="hora")
	private String hora;
	@Column(name="empleado")
	private String empleado;
	@Column(name="nombreEmpresa")
	private String nombreEmpresa;
	@Column(name="direccionEmpresa")
	private String direccionEmpresa;
	@Column(name="cifNif")
	private String cifNif;
	@Column(name="telefonoEmpresa")
	private String telefonoEmpresa;
	@Column(name="numeroDigito")
	private String numeroDigito;
	@Column(name="formaPago")
	private Boolean formaPago;
	@Column(name="total")
	private BigDecimal total;
	@Column(name="devuelto")
	private Boolean devuelto;
	
	
	public Long getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
	public void setFormaPago(Boolean formaPago) {
		this.formaPago = formaPago;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}
	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}
	public String getCifNif() {
		return cifNif;
	}
	public void setCifNif(String cifNif) {
		this.cifNif = cifNif;
	}
	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}
	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}
	public String getNumeroDigito() {
		return numeroDigito;
	}
	public void setNumeroDigito(String numeroDigito) {
		this.numeroDigito = numeroDigito;
	}
	public Boolean getFormaPago() {
		return formaPago;
	}
	public Boolean getDevuelto() {
		return devuelto;
	}
	public void setDevuelto(Boolean devuelto) {
		this.devuelto = devuelto;
	}
	
	
	
}
