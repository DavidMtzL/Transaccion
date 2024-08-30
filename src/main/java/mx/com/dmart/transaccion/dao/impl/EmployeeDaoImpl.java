package mx.com.dmart.transaccion.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.dmart.transaccion.dao.EmployeeDao;
import mx.com.dmart.transaccion.model.Empleado;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable guardar(Empleado empleado) {

		return getSession().save(empleado);
	}

	@Override
	public Empleado buscarPorId(int id) {

		Empleado empleado = null;
		try {
			Query query = getSession().getNamedNativeQuery("empleado.nativeBuscarPorId");
			                                                
			query.setParameter("pId", id);
			Object[] resultado = (Object[]) query.getSingleResult();

			empleado = new Empleado();

			empleado.setId(Integer.valueOf(String.valueOf(resultado[0])));
			empleado.setNombres(String.valueOf(resultado[1]));
			empleado.setApellidos(String.valueOf(resultado[2]));
			empleado.setCargo(String.valueOf(resultado[3]));
			empleado.setSalario(Integer.valueOf(String.valueOf(resultado[4])));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empleado;
	}

	@Override
	public boolean actualizar(Empleado empleado) {
		try {
			
			getSession().update(empleado);
			return true;
		}catch(Exception e) {
			return false;
		}

	}

	@Override
	public List<Empleado> listar() {
		
		List<Empleado> lstEmpleado = null;
		try {
			Query query= getSession().getNamedNativeQuery("empleado.nativeBuscarTodos");
			@SuppressWarnings("unchecked")
			List<Object[]> lstResultado= (List<Object[]>) query.getResultList();
			
			lstEmpleado= new ArrayList<Empleado>();
			
			for (Object[] resultado : lstResultado) {
				Empleado empleado= new Empleado();
				empleado.setId(Integer.valueOf(String.valueOf(resultado[0])));
				empleado.setNombres(String.valueOf(resultado[1]));
				empleado.setApellidos(String.valueOf(resultado[2]));
				empleado.setCargo(String.valueOf(resultado[3]));
				empleado.setSalario(Integer.valueOf(String.valueOf(resultado[4])));
				lstEmpleado.add(empleado);
			}
			
		}catch(Exception e) {
			lstEmpleado= new ArrayList<Empleado>();
		}
		
		return lstEmpleado;
	}

	@Override
	public boolean eliminar(Empleado empleado) {
		try {
			getSession().delete(empleado);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	
	
}
