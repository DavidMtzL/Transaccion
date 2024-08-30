package mx.com.dmart.transaccion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mx.com.dmart.transaccion.config.AplicacionConf;

public class PruebaAplicacion {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context= null;
		
		try {
			context= new AnnotationConfigApplicationContext(AplicacionConf.class);
			CrudEmpleado aplication= context.getBean(CrudEmpleado.class);
			aplication.procesarCrud();
		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			context.close();
		}
	}

}
