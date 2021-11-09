package clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Estado implements Serializable,Comparable<Estado>{
	private static final long serialVersionUID = 4082024573067522629L;
	private Integer id;
    private ArrayList<Transicion> transiciones = new ArrayList<Transicion>();

    public Estado(Integer id, ArrayList<Transicion> transiciones) {
        this.id = id;
        this.transiciones = transiciones;
    }

    public Estado(Integer identificador){
        this.id = identificador;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Transicion> getTransiciones() {
        return this.transiciones;
    }

    public void setTransiciones(Transicion tran) {
        this.transiciones.add(tran);
    }
    public void sustituirTransiciones(Transicion tran) {
        this.transiciones.clear();
        this.transiciones.add(tran);
    }
    public void eliminarTransicion(Transicion tran) {
    	int indexTransicion = transiciones.indexOf(tran);
        this.transiciones.remove(indexTransicion);
    }
    public void eliminarTransiciones() {
    	this.transiciones.clear();
    }
    @Override
    public String toString() {
        return this.id.toString();
    }

	@Override
	public int compareTo(Estado o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}

    
}
