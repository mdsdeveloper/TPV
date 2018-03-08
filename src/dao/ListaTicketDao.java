package dao;

import java.io.Serializable;
import java.util.List;

import modelo.ListaTicket;
import modelo.Ticket;

/**
 * 
 * @author Two hands Technology
 *
 */
public interface ListaTicketDao extends Serializable{

	public Long createListaTicket(ListaTicket Listaticket);
	public ListaTicket readTicket(Long idListaTicket);
	public void upDateListaTicket(ListaTicket listaTicket);
	public void deleteListaTicket(ListaTicket listaTicket);
	public List<ListaTicket> obtenerListaTicketsById(Long idListaTicket);
}
