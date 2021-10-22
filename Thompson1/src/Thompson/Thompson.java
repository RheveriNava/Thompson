package Thompson;

public class Thompson {
	
	
	public static FDA crearNuevoFDA(String valorFlecha) {
		FDA nuevoFDA = new FDA(valorFlecha);
		return nuevoFDA;
	}
	
	public static void concatenacion(FDA izquierda,FDA derecha,FDA ptr, boolean esIzquierdo) {
		izquierda.setSiguienteFDA(derecha);
		if(esIzquierdo) {
			derecha.setLlegada(izquierda.getSalida());
			return;
		}
		izquierda.setLlegada(derecha.getSalida());
	}
	public static void union(FDA izquierda,FDA derecha,FDA ptr, boolean esIzquierdo) {
		
	}
public static void cerraduraKleen(FDA izquierda,FDA derecha,FDA ptr, boolean esIzquierdo) {
		
	}
public static void cerradurPositiva(FDA izquierda,FDA derecha,FDA ptr, boolean esIzquierdo) {
	
}
public static void cerraduraOpcional(FDA izquierda,FDA derecha,FDA ptr, boolean esIzquierdo) {
	
}
}
