package mx.com.dmart.transaccion.service;

import java.util.List;

import mx.com.dmart.transaccion.model.Empleado;

public interface EmpleadoService {

	public  boolean guardar(Empleado empleado);
	
	public Empleado buscarPorId(int id);
	
	public boolean actualizar(Empleado empleado);
	
	public List<Empleado> listar();
	
	public boolean eliminar(Empleado empleado);
}
