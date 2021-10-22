package Thompson;

public class Estados {
	private int est;
	private String[][] varDireccion;

	public Estados(){
		est = 0;
		varDireccion[0][0] = "°";
		varDireccion[0][1] = "vacio";
	}
	public Estados(int a,String var,String direccion){
		est = a;
		varDireccion[0][0] = var;
		varDireccion[0][1] = direccion;
	}
	public void setEstado(int a){
		est = a;
	}
	public int getEstado(){
		return est;
	}
	public void setVariable(String var){
		varDireccion[0][0] = var;
	}
	public String getVariable() {
		return varDireccion[0][0];
	}
	public void setDireccion(String direccion) {
		varDireccion[0][1] = direccion;
	}
}