package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Devolucion;
import modelo.Ticket;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class DevolucionDaoImpl implements DevolucionDao{

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
	public Long createDevolucion(Devolucion devolucion) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(devolucion);
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
	public Devolucion readDevolucion(Long idDevolucion) {
		Devolucion dev = null;
		try{
			iniciaSession();
			dev = session.get(Devolucion.class, idDevolucion);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateDevolucion(Devolucion devolucion) {
		try{
			iniciaSession();
			session.update(devolucion);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteDevolucion(Devolucion devolucion) {
		try{
			iniciaSession();
			session.delete(devolucion);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

}
