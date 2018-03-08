package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.ListaTicket;
import modelo.Ticket;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class ListaTicketDaoImpl implements ListaTicketDao{

	private Session session;
	private Transaction tx;
	
	private void iniciaSession() throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	private void manejaExcepcion(HibernateException he) throws HibernateException{
	    tx.rollback();
	    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}
	
	@Override
	public Long createListaTicket(ListaTicket Listaticket) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(Listaticket);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public ListaTicket readTicket(Long idListaTicket) {
		ListaTicket dev = null;
		try{
			iniciaSession();
			dev = session.get(ListaTicket.class, idListaTicket);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateListaTicket(ListaTicket listaTicket) {
		try{
			iniciaSession();
			session.update(listaTicket);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteListaTicket(ListaTicket listaTicket) {
		try{
			iniciaSession();
			session.delete(listaTicket);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@Override
	public List<ListaTicket> obtenerListaTicketsById(Long idTicket) {
		List<ListaTicket> dev = null;
		
		try{
			iniciaSession();
			String hql = "from ListaTicket where idTicket = :idTicket";
			Query query = session.createQuery(hql);
			query.setParameter("idTicket", idTicket);
			dev = query.list();
		}finally{
			session.close();
		}
		return dev;
	}

}
