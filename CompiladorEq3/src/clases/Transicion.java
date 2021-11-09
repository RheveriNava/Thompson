package clases;

import java.io.Serializable;

public class Transicion implements Serializable,Comparable<Transicion>{
	private static final long serialVersionUID = -3322564965051968126L;
	private Estado inicio;
    private Estado fin;
    private String simbolo;

    public Transicion(Estado inicio, Estado fin, String simbolo) {
        this.inicio = inicio;
        this.fin = fin;
        this.simbolo = simbolo;
    }

    public Estado getInicio() {
        return inicio;
    }

    public void setInicio(Estado inicio) {
        this.inicio = inicio;
    }

    public Estado getFin() {
        return fin;
    }

    public void setFin(Estado fin) {
        this.fin = fin;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    //Devuelve una transicion (a-b)
    public String toString() {
        return "(" + inicio.getId() + "-" + fin.getId() + ")";
    }
    
    public String DOT_String(){
        return (this.inicio+" -> "+this.fin+" [label=\""+this.simbolo+"\"];");
    }

	@Override
	public int compareTo(Transicion o) {
		if(this.inicio.compareTo(o.getInicio()) == 0 && this.fin.compareTo(o.getFin()) == 0 && this.simbolo.compareTo(o.getSimbolo()) == 0) {
			return 0;
		}
		return 1;
	}

    
}
