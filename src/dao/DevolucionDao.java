package dao;

import java.io.Serializable;

import modelo.Devolucion;

/**
 * 
 * @author Two hands Technology
 *
 */
public interface DevolucionDao extends Serializable {

	public Long createDevolucion(Devolucion dev);
	public Devolucion readDevolucion(Long idDevolucion);
	public void upDateDevolucion(Devolucion devolucion);
	public void deleteDevolucion(Devolucion devolucion);

}
