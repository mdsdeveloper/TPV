package modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Two hands Technology
 *
 */
@Entity
@Table(name="listaTicket")
public class ListaTicket {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idListaTicket;
	@Column(name="idTicket")
	private Long idTicket;
	@Column(name="nombreProducto")
	private String nombreProducto;
	@Column(name="catidadProducto")
	private String cantidadProducto;
	@Column(name="precioProducto")
	private String precioProducto;
	@Column(name="total")
	private String total;
	
	
	public Long getIdListaTicket() {
		return idListaTicket;
	}
	public void setIdListaTicket(Long idListaTicket) {
		this.idListaTicket = idListaTicket;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getCantidadProducto() {
		return cantidadProducto;
	}
	public void setCantidadProducto(String cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public String getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(String precioProducto) {
		this.precioProducto = precioProducto;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Long getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}
	
	
}
