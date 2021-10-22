package Thompson;

public class FDA {
	private String valorFlecha;
	private int expresionLlegada;
	private int expresionSalida;
	private FDA apuntadorSiguiente[];
	
	public FDA(String entradaDeAlfabeto) {
		this.valorFlecha = entradaDeAlfabeto;
		this.expresionLlegada = 0;
		this.expresionSalida = 1;
	}
	public FDA(String var,int llega) {
		this.valorFlecha = var;
		this.expresionLlegada = llega;
		this.expresionSalida = llega+1;
	}
	public void setNuevoValorFlecha(String var) {
		this.valorFlecha= var;
	}
	
	public String getValorFlecha() {
		return this.valorFlecha;
		
	}
	public void setLlegada(int llega) {
		this.expresionLlegada = llega;
		this.expresionSalida = llega + 1;
	}
	public int getLlegada() {
		return expresionLlegada;
	}
	public void setSalida(int salida) {
		expresionSalida = salida;
	}
	public int getSalida() {
		return expresionSalida;
	}
	public void setSiguienteFDA(FDA sig) {
		apuntadorSiguiente[apuntadorSiguiente.length]=sig;
	}
	public FDA getSigFDA() {
		return apuntadorSiguiente[apuntadorSiguiente.length];
	}
	public FDA getSigFDA(int posicion) {
		return apuntadorSiguiente[posicion];
	}
}
