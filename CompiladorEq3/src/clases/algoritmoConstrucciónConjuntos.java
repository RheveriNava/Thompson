package clases;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/* import java.util.HashMap; */
import java.util.TreeSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class algoritmoConstrucciónConjuntos {
    private Automata afd;
    private final Conjuntos conjuntos;

    public algoritmoConstrucciónConjuntos(){
        conjuntos = new Conjuntos();
        afd = new Automata();
    }

    /**
     * Metodo que convierte un AFN a AFD usando subconjuntos
     * @param afn
     */
    public void conversionAFN(Automata afn){
        //Creamos el automata vacio
        Automata automata = new Automata();
        //Utilizamos una cola para guardar los subconjuntos
        Queue<TreeSet<Estado>> cola = new LinkedList<>();

        //definimos un nuevo estado inicial y asignamos al automata vacio
        Estado inicial = new Estado(0);
        automata.setInicial(inicial);
        automata.addEstados(inicial);

        //Empieza algoritmo con la cerradura Epsion del estado inicial del AFN
        TreeSet<Estado> array_inicial = conjuntos.cerraduraE(afn.getInicial());
        TreeSet<Estado> mueve_result = array_inicial;
        ArrayList<TreeSet<Estado>> estadosNuevos = new ArrayList<>();
        estadosNuevos.add(array_inicial);
        //Si la cerradura Epsilon contiene estados de aceptacion tenemos que agregar
        for(Estado aceptacion:afn.getEstadosAceptacion()){
            if(array_inicial.contains(aceptacion)){
                automata.addEstadosAceptacion(inicial);
            }
        }
        //lo agregamos a la cola
        cola.add(array_inicial);
        //Temporal para guardar el resultado de los subconjuntos generados
        ArrayList<TreeSet<Estado>> temporal = new ArrayList<>();
        //Se crea un indice para saber la posicion del estado actual y siguiente
        int indexEstadoInicio = 0;
        int count = 0, count2 = 0;
        while(!cola.isEmpty()){
            //El subconjunto actual
        	TreeSet<Estado> actual = cola.poll();
            Iterator<Estado> iterador = actual.iterator();
            //Se recorre el subconjunto con cada letra del alfabeto
            for(int recorreEstado = 0;recorreEstado<actual.size();recorreEstado++){
            	count++;
            	for(Transicion t:(ArrayList<Transicion>)iterador.next().getTransiciones()){
            		count2++;
            		String simbolo = (String) t.getSimbolo();
            		if(simbolo != Automata.EPSILON) {
		                //Realizamos el mueve con el subconjunto
		                mueve_result = conjuntos.mueve(actual, simbolo);
		                TreeSet<Estado> resultado = new TreeSet<>();
		                
		                int conteoCerradura = 0;
		                //Cerradura Epsilon con todos los estados generados en el mueve
		                //los guardamos en un arreglo
		                for(Estado e: mueve_result){
		                    resultado.addAll(conjuntos.cerraduraE(e));
		                    conteoCerradura++;
		                }
		                if(!estadosNuevos.contains(resultado)){
		                	 //Si el subconjunto no existe, se crea un nuevo estado
		                    temporal.add(mueve_result);
			                cola.add(resultado);
		                    Estado nuevo = new Estado(estadosNuevos.size());
		                    int buscarEstado = 0,idEstadoBuscado = 0;
		                    idEstadoBuscado = estadosNuevos.indexOf(actual);
		                    Estado anterior = null;
		                    for(buscarEstado = 0; buscarEstado < estadosNuevos.size();buscarEstado++) {
		                    	if(automata.getEstados().get(buscarEstado).getId() == idEstadoBuscado)
		                    		 anterior = (Estado) automata.getEstados().get(buscarEstado);
		                    }
		                    anterior.setTransiciones(new Transicion(anterior, nuevo, simbolo));
		                    automata.addEstados(nuevo);
		                    
		                    //Verificamos que el estado sea de aceptacion y si es lo agregamos
		                    for(Estado aceptacion : afn.getEstadosAceptacion()){
		                        if(resultado.contains(aceptacion))
		                            automata.addEstadosAceptacion(nuevo);
		                    }
		                    estadosNuevos.add(resultado);
		                }
		                else{
		                	int buscarEstado = 0,idEstadoBuscado = 0;
		                	Estado anterior = null, actualE = null;
		                	idEstadoBuscado = estadosNuevos.indexOf(actual);
		                	for(buscarEstado = 0; buscarEstado < estadosNuevos.size();buscarEstado++) {
		                    	if(automata.getEstados().get(buscarEstado).getId() == idEstadoBuscado)
		                    		 anterior = (Estado) automata.getEstados().get(buscarEstado);
		                    }
		                	idEstadoBuscado = estadosNuevos.indexOf(resultado);
		                	for(buscarEstado = 0; buscarEstado < estadosNuevos.size();buscarEstado++) {
		                    	if(automata.getEstados().get(buscarEstado).getId() == idEstadoBuscado)
		                    		actualE = (Estado) automata.getEstados().get(buscarEstado);
		                    }
		                	idEstadoBuscado = 0;
		                	for(buscarEstado = 0; buscarEstado < anterior.getTransiciones().size();buscarEstado++) {
			                	if(anterior.getTransiciones().get(buscarEstado).getFin().equals(actualE) && anterior.getTransiciones().get(buscarEstado).getSimbolo().equals(simbolo))
			                		idEstadoBuscado = 1;
		                	}
		                	if(idEstadoBuscado == 0) {
		                		anterior.setTransiciones(new Transicion(anterior, actualE, simbolo));
		                	}
		                }
            		}
            	}
            	count2=0;
            }
            count=0;
            indexEstadoInicio++;
        }
        String letrasMenosAlfabeto = "";
        String digitosMenosAlfabeto = "";
        Estado auxuliarEstadoLetra = null;
        Estado auxuliarEstadoDigito = null;
        int transicionesLetra = 0;
        int transicionesDigito = 0;
        for(int recorreEstado = 0; recorreEstado < automata.getEstados().size(); recorreEstado++){
        	for(int recorreTransiciones = 0; recorreTransiciones < automata.getEstados().get(recorreEstado).getTransiciones().size(); recorreTransiciones++) {
        		if(automata.getEstados().get(recorreEstado).getTransiciones().get(recorreTransiciones).getSimbolo().equals("L")){
        			auxuliarEstadoLetra = automata.getEstados().get(recorreEstado);
        			transicionesLetra = recorreTransiciones;
        		}
        		if(automata.getEstados().get(recorreEstado).getTransiciones().get(recorreTransiciones).getSimbolo().equals("D")){
        			auxuliarEstadoDigito = automata.getEstados().get(recorreEstado);
        			transicionesDigito = recorreTransiciones;
        		}
        		byte[] asciiSimbolo = automata.getEstados().get(recorreEstado).getTransiciones().get(recorreTransiciones).getSimbolo().getBytes(StandardCharsets.US_ASCII);
        		if(asciiSimbolo[0] > 96 && asciiSimbolo[0] < 123) {
        			letrasMenosAlfabeto += "-" + automata.getEstados().get(recorreEstado).getTransiciones().get(recorreTransiciones).getSimbolo();
        		}
        		if(asciiSimbolo[0] > 47 && asciiSimbolo[0] < 58) {
        			digitosMenosAlfabeto += "-" + automata.getEstados().get(recorreEstado).getTransiciones().get(recorreTransiciones).getSimbolo();
        		}
        	}
        	if(auxuliarEstadoLetra != null) {
        		automata.getEstados().get(recorreEstado).getTransiciones().get(transicionesLetra).setSimbolo(auxuliarEstadoLetra.getTransiciones().get(transicionesLetra).getSimbolo()+letrasMenosAlfabeto);
        	}
        	if(auxuliarEstadoDigito != null) {
        		automata.getEstados().get(recorreEstado).getTransiciones().get(transicionesDigito).setSimbolo(auxuliarEstadoDigito.getTransiciones().get(transicionesDigito).getSimbolo()+digitosMenosAlfabeto);
        	}
        	letrasMenosAlfabeto = "";
        	digitosMenosAlfabeto = "";
        	auxuliarEstadoLetra = null;
        	auxuliarEstadoDigito = null;
        }
        this.afd = automata;
        definirAlfabeto(afn);
        this.afd.setTipo("AFD");
        afn.reverse();
        this.afd.setLenguajeR(afn.getLenguajeR());

    }
    
    //Definimos el alfabeto para el automata
    private void definirAlfabeto(Automata afn){
        this.afd.setAlfabeto(afn.getAlfabeto());
    }

    public Automata getAfd(){
        return afd;
    }
    

}
