package PantallaVentas;

import java.math.BigDecimal;

public class Devolucion {

	private String numTicket;
	private String nombreProducto;
	private Integer cantidad;
	private BigDecimal importe;
	private Boolean formaPago;
	
	public String getNumTicket() {
		return numTicket;
	}
	public void setNumTicket(String numTicket) {
		this.numTicket = numTicket;
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
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Boolean getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(Boolean formaPago) {
		this.formaPago = formaPago;
	}
	
	
}
