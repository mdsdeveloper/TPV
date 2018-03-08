package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Ticket;
import util.HibernateUtil;
import util.UtilService;

/**
 * 
 * @author Two hands Technology
 *
 */
public class TicketDaoImpl implements TicketDao {

	private static final long serialVersionUID = -8692023272677350921L;
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
	public Long createTicket(Ticket ticket) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(ticket);
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
	public Ticket readTicket(Long idTicket) {
		Ticket dev = null;
		try{
			iniciaSession();
			dev = session.get(Ticket.class, idTicket);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateTicket(Ticket ticket) {
		try{
			iniciaSession();
			session.update(ticket);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		try{
			iniciaSession();
			session.delete(ticket);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
	@Override
	public List<Ticket> obtenerTickets(Date dia) {
		List<Ticket> dev = null;
		if(dia == null){
			try{
				iniciaSession();
				dev = session.createQuery("from Ticket").list();
			}finally {
				session.close();
			}
		}else{
			try{	
				String fecha = UtilService.obtenerFecha(dia);
				iniciaSession();
				String hql = "from Ticket where fecha =:fecha";
				Query query = session.createQuery(hql);
				query.setParameter("fecha", fecha);
				dev = query.list();
			}finally {
				session.close();
			}
		}
		
		return dev;
	}
	


}
