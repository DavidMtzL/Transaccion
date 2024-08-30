package mx.com.dmart.transaccion.service.impl;

import java.util.List;

import javax.persistence.TransactionRequiredException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.dmart.transaccion.dao.EmployeeDao;
import mx.com.dmart.transaccion.model.Empleado;
import mx.com.dmart.transaccion.service.EmpleadoService;

@Service("employeeService")
@Transactional(readOnly = false)
public class EmpleadoServiceImpl  implements EmpleadoService{
	
	final static Logger logger= Logger.getLogger(EmpleadoServiceImpl.class);
	
	@Autowired
	EmployeeDao employeeDao;

	//@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = false, noRollbackFor = {NumberFormatException.class, ArithmeticException.class})
	@Override
	public boolean guardar(Empleado empleado) {
		 boolean respuesta= false;
		 Integer id= (Integer) employeeDao.guardar(empleado);
		 logger.debug("Id del nuevo empleado:"+ id);
		 
		 if(id>0) {
			 respuesta= true;
		 }else {
			 respuesta=false;
		 }
		 //this.actualizaEmpleadoTop();
		 return respuesta;
	}
	
	private void actualizaEmpleadoTop() {
		int numero= Integer.valueOf("10.gg");
		logger.info("numero "+ numero);
	}

	@Override
	public Empleado buscarPorId(int id) {
		 logger.debug("Buscar empleado con id "+ id);
		return employeeDao.buscarPorId(id);
	}

	@Override
	public boolean actualizar(Empleado empleado) {
		logger.debug("Actualizar empleado con id: "+ empleado.getId());
		return employeeDao.actualizar(empleado);
	}

	@Override
	public List<Empleado> listar() {
		logger.debug("Listar los empleados.");
		
		return employeeDao.listar();
	}

	@Override
	public boolean eliminar(Empleado empleado) {
		logger.debug("Eliminar empleado con id "+ empleado.getId());
		return employeeDao.eliminar(empleado);
	}

}
