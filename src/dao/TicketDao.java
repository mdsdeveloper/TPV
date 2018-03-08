package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import modelo.ListaTicket;
import modelo.Ticket;
import modelo.Venta;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface TicketDao extends Serializable {

	public Long createTicket(Ticket ticket);
	public Ticket readTicket(Long idTicket);
	public void upDateTicket(Ticket ticket);
	public void deleteTicket(Ticket ticket);
	public List<Ticket> obtenerTickets(Date date);
}
