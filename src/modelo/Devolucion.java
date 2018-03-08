package modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="devolucion")
public class Devolucion {	

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long idDevolucion;
		@Column(name="fecha")
		private String fecha;
		@Column(name="empleado")
		private String empleado;
		@Column(name="numeroTicket")
		private String numeroTicket;
		@Column(name="nombreProducto")
		private String nombreProducto;
		@Column(name="cantidad")
		private Integer cantidad;
		@Column(name="formaPago")
		private Boolean formaPago;
		@Column(name="importe")
		private BigDecimal importe;
		
		public Long getIdDevolucion() {
			return idDevolucion;
		}
		public void setIdDevolucion(Long idDevolucion) {
			this.idDevolucion = idDevolucion;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getEmpleado() {
			return empleado;
		}
		public void setEmpleado(String empleado) {
			this.empleado = empleado;
		}
		public String getNumeroTicket() {
			return numeroTicket;
		}
		public void setNumeroTicket(String numeroTicket) {
			this.numeroTicket = numeroTicket;
		}
		public String getNombreProducto() {
			return nombreProducto;
		}
		public void setNombreProducto(String nombreProducto) {
			this.nombreProducto = nombreProducto;
		}
		public Integer getCantidad() {
			return cantidad;
		}
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
		public Boolean getFormaPago() {
			return formaPago;
		}
		public void setFormaPago(Boolean formaPago) {
			this.formaPago = formaPago;
		}
		public BigDecimal getImporte() {
			return importe;
		}
		public void setImporte(BigDecimal importe) {
			this.importe = importe;
		}
		
		
	

}
