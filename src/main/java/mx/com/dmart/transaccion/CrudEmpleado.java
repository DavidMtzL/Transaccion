package mx.com.dmart.transaccion;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.dmart.transaccion.model.Empleado;
import mx.com.dmart.transaccion.service.EmpleadoService;

@Component
public class CrudEmpleado {

	final static Logger logger= Logger.getLogger(CrudEmpleado.class);
	
	@Autowired
	private EmpleadoService empService;
	
	
	public void procesarCrud() {
		
		logger.info("Nuevo Empleado");
		Empleado empleado= new Empleado();
		
		empleado.setApellidos("Verastegui");
		empleado.setNombres("Norma");
		empleado.setCargo("Moza");
		empleado.setSalario(1500);
		
		if(empService.guardar(empleado)) {
			logger.info("Empleado guardado con exito");
			
		}else {
			logger.info("No se pudo guardar el empleado");
		}
		
		Empleado empById= empService.buscarPorId(5);
		logger.info("empleado: "+ empById);
		empById.setSalario(3000);
		
		if(empService.actualizar(empById)) {
			logger.info("Actualizar correctamente");
		}else {
			logger.info("No se pudo actualizar");
		}
		
		Empleado empeliminar= empService.buscarPorId(23);
		logger.info("Empleado a eliminar"+ empeliminar);
		
		if(empService.eliminar(empeliminar)) {
			logger.info("Eliminar correctamente");
		}else {
			logger.info("No se pudo eliminar");
		}
		
		List<Empleado> lsEmpleados = empService.listar();
		logger.info("Empleados"+ lsEmpleados.size());
		
		for(Empleado emp: lsEmpleados) {
			logger.info(emp.toString());
		}
		
		
	}
	
}
