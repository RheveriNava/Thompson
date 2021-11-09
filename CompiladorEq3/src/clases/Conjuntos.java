package clases;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Stack;

public class Conjuntos {
    
    private String resultado;

    public Conjuntos(){

    }

    public Conjuntos(Automata afn_conjuntos, String expre){
        consConjunto(expre, afn_conjuntos);
    }

     // Metodo para crear la cerradura Epsilon
    public TreeSet<Estado> cerraduraE(Estado cerraduraEstado){
    	int count = 0, count2 = 0;
        Stack<Estado> pilaCerradura = new Stack<Estado>();
        Estado actual = cerraduraEstado;
        actual.getTransiciones();
        TreeSet<Estado> resultado = new TreeSet<Estado>();

        resultado.add(cerraduraEstado);
        pilaCerradura.push(actual);
        while(!pilaCerradura.isEmpty()){
            actual = pilaCerradura.pop();
            count2++;
            for(Transicion t: (ArrayList<Transicion>)actual.getTransiciones()){
            	count++;
                if(t.getSimbolo().equals(Automata.EPSILON)){
                    resultado.add(t.getFin());
                    pilaCerradura.push(t.getFin());
                }
            }
        }
        
        return resultado;
    }

    // Metodo para hacer el movimiento
    public TreeSet<Estado> mueve(TreeSet<Estado> estados, String simbolo){
        TreeSet<Estado> alcanzados = new TreeSet<Estado>();
        Iterator<Estado> iterador = estados.iterator();
        
        byte[] bytes = simbolo.getBytes(StandardCharsets.US_ASCII);
        int count = 0;
        while(iterador.hasNext()){
            for(Transicion t:(ArrayList<Transicion>)iterador.next().getTransiciones()){
                Estado siguiente = t.getFin();
                String simbol = (String) t.getSimbolo();
                if(simbol.equals(simbolo)){
                	count++;
                    alcanzados.add(t.getFin());
                }
                else if(bytes[0] > 96 && bytes[0] < 123) {
                	if(simbol.equals("L")){
                    	count++;
                        alcanzados.add(t.getFin());
                	}
                }
            }
        }
        return alcanzados;
    }

     //Metodo para hacer el movimiento
    public ArrayList<Estado> mueve(Estado estado, String simbolo){
        ArrayList<Estado> alcanzados = new ArrayList<Estado>();

        for(Transicion t: (ArrayList<Transicion>)estado.getTransiciones()){
            Estado siguiente = t.getFin();
            String simbol = (String) t.getSimbolo();

            if(simbol.equals(simbolo) && !alcanzados.contains(siguiente)){
                alcanzados.add(siguiente);
            }
        }

        return alcanzados;
    }

    public boolean consConjunto(String expre, Automata automata){
        Estado inicial = automata.getInicial();
        //ArrayList<Estado> estados = automata.getEstados();
        ArrayList<Estado> aceptacion = new ArrayList<Estado>(automata.getEstadosAceptacion());
        TreeSet<Estado> conjunto = cerraduraE(inicial);

        for(Character ch: expre.toCharArray()){
            conjunto = mueve(conjunto, ch.toString());
            TreeSet<Estado> temp = new TreeSet<Estado>();
            Iterator<Estado> iter = conjunto.iterator();

            while(iter.hasNext()){
                Estado siguiente = iter.next();
                temp.addAll(cerraduraE(siguiente));
            }
            conjunto = temp;
        }
        boolean res = false;

        for(Estado estado_aceptacion : aceptacion){
            
            if(conjunto.contains(estado_aceptacion)){
                res = true;
            }
        }
        return res;
    }

    public String getResultado(){
        return resultado;
    }
}
