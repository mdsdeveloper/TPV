package PantallaVentas;

import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import dao.LineaVentaDao;
import dao.LineaVentaDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import dao.VentaDao;
import dao.VentaDaoImpl;
import modelo.Categoria;
import modelo.Producto;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class Main {

	public static void main(String[] args) {
		
		CategoriaDao categoriaDao = new CategoriaDaoImpl();
		ProductoDao productoDao = new ProductoDaoImpl();
//		****************************************************categorias****************************
		Categoria c0 = new Categoria();
		c0.setNombre(ConstantesUtil.varios);
		c0.setDescripcion("Genérica");		
		categoriaDao.createCategoria(c0);
//		****************************************************productos********************************		
		Producto p0 = new Producto();
		
		p0.setNombre(ConstantesUtil.varios);
		p0.setDescripcion("Genérico");
		p0.setMarca("Genérica");
		p0.setCategoria(c0);		
		productoDao.createProducto(p0);
	}

}
